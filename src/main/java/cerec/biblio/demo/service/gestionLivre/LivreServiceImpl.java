package cerec.biblio.demo.service.gestionLivre;


import cerec.biblio.demo.common.Consts;
import cerec.biblio.demo.dao.*;
import cerec.biblio.demo.dto.*;
import cerec.biblio.demo.model.*;
import cerec.biblio.demo.service.FileManager;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LivreServiceImpl implements LivreService {
    private LivreRepository livreRepository;
    private AuteurRepository auteurRepository;
    private EditeurRepository editeurRepository;
    private CategorieLivreRepository categorieRepository;
    private SousCategorieRepository sousCategorieRepository;
    private LivreElectroniqueRepository livreElectroniqueRepository;
    private FileManager fileManager;

    public LivreServiceImpl(LivreRepository livreRepository,
                            AuteurRepository auteurRepository,
                            EditeurRepository editeurRepository,
                            CategorieLivreRepository categorieRepository, SousCategorieRepository sousCategorieRepository,
                            LivreElectroniqueRepository livreElectroniqueRepository, FileManager fileManager) {

        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
        this.editeurRepository = editeurRepository;
        this.categorieRepository = categorieRepository;
        this.sousCategorieRepository = sousCategorieRepository;
        this.livreElectroniqueRepository = livreElectroniqueRepository;
        this.fileManager = fileManager;
    }


    /*add*/
    @Override
    public LivreResponse addLivre(LivreRequest livreRequest,String urlImage, String urlPdf) {
        Livre livre = livreRequestToLivre(livreRequest);
        livre.setCoverLivre(urlImage);


        LivreElectronique livreElectronique=new LivreElectronique();
        livreElectronique.setSourceLivreElectronique(urlPdf);
        LivreElectronique livreElectroniqueSaved=livreElectroniqueRepository.save(livreElectronique);

        livre.setLivreElectronique(livreElectroniqueSaved);
        Livre livreSaved=livreRepository.save(livre);

        return livreToLivreResponse(livreSaved);
    }



    @Override
    public List<LivreResponse> updateLivre(LivreRequest livreRequest, String urlImage, String urlPdf) {
        Livre livre=livreRepository.findById(livreRequest.getIdLivre()).get();
        livre.setIsbnLivre(livreRequest.getIsbnLivre());
        livre.setTitreLivre(livreRequest.getTitreLivre());
        livre.setEditionLivre(livreRequest.getEditionLivre());
        livre.setNbPageLivre(livreRequest.getNbPageLivre());

        Auteur auteur=auteurRepository.findById(livreRequest.getIdAuteur()).get();
        Editeur editeur=editeurRepository.findById(livreRequest.getIdEditeur()).get();
        SousCategorie sousCategorie=sousCategorieRepository.findById(livreRequest.getIdSousCategorie()).get();

        livre.setAuteur(auteur);
        livre.setEditeur(editeur);
        livre.setSousCategorie(sousCategorie);

        if(!urlImage.isEmpty()) livre.setCoverLivre(urlImage);

        if(!urlPdf.isEmpty()) {
            LivreElectronique livreElectronique=livreElectroniqueRepository.findById(livre.getLivreElectronique().getIdLivreElectronique()).get();
            livreElectronique.setSourceLivreElectronique(urlPdf);
            livreElectroniqueRepository.save(livreElectronique);
        }

        livreRepository.save(livre);

        List<Livre> livres=livreRepository.findAll();
        List<LivreResponse> livreResponses=new ArrayList<>();

        livres.forEach(livre1 -> {
            LivreResponse livreResponse=livreToLivreResponse(livre1);
            livreResponses.add(livreResponse);
        });

        return livreResponses;
    }



    @Override
    public List<CategorieResponse> addCategorie(CategorieRquest categorieRquest) {

        CategorieLivre categorie = categorieRepository.findByDesignation(categorieRquest.getDesignationCategorie());
        if (categorie != null) throw new RuntimeException("Cette categorie existe");

        categorie = new CategorieLivre();
        categorie.setDesignation(categorieRquest.getDesignationCategorie());
        categorieRepository.save(categorie);

        System.out.println(categorieRquest.toString());


        return getCategories();
    }

    @Override
    public List<SousCategorueResponse> addSousCategorie(SousCategorieRequest sousCategorieRequest) {
        SousCategorie souscategorie = sousCategorieRepository.findByDesignationSousCategorie(sousCategorieRequest.getDesignationSousCategorie());
        if (souscategorie != null) throw new RuntimeException("Sous-Categorie existant");

        souscategorie = new SousCategorie();
        CategorieLivre categorie = categorieRepository.findById(sousCategorieRequest.getIdCategorie()).get();

        souscategorie.setDesignationSousCategorie(sousCategorieRequest.getDesignationSousCategorie());
        souscategorie.setCategorie(categorie);
        sousCategorieRepository.save(souscategorie);

        return getSousCategories();
    }


    @Override
    public List<EditeurResponse> addEditeur(EditeurRequest editeurRequest) {
        Editeur editeur = editeurRepository.findByNomEditeur(editeurRequest.getNomEditeur());
        if (editeur != null) throw new RuntimeException("Editeur existant");

        editeur = new Editeur();
        editeur.setNomEditeur(editeurRequest.getNomEditeur());
        editeur.setAdresseEditeur(editeurRequest.getAdresseEditeur());

        editeurRepository.save(editeur);


        return getEditeurs();
    }

    @Override
    public List<AuteurResponse> addAuteur(AuteurRequest auteurRequest) {
        Auteur auteur = auteurRepository.findByNomAuteur(auteurRequest.getNomAuteur());
        if (auteur != null) throw new RuntimeException("Auteur existant");

        auteur = new Auteur();
        auteur.setNomAuteur(auteurRequest.getNomAuteur());

        auteurRepository.save(auteur);

        return getAuteurs();
    }

    @Override
    public List<LivreResponse> getLivres() {
        List<Livre> livres=livreRepository.findAll(Sort.by(Sort.Direction.DESC, "idLivre"));
        List<LivreResponse> livreResponses=new ArrayList<>();

        livres.forEach(livre -> {
            LivreResponse livreResponse=livreToLivreResponse(livre);
            livreResponses.add(livreResponse);
        });

        return livreResponses;
    }

    @Override
    public LivreResponse getLivreById(Long id) {
        Livre livre=livreRepository.findById(id).get();
        LivreResponse livreResponse=livreToLivreResponse(livre);
        return livreResponse;
    }

    @Override
    public byte[] getDocumentPdf(Long id) throws IOException {
        Livre livre=livreRepository.findByIdLivre(id);
        String folder= Consts.FILE_STORAGE_ROOT+"/documents"+"/"+livre.getLivreElectronique().getSourceLivreElectronique();
        return Files.readAllBytes(Paths.get(folder));
    }





    /*get*/

    @Override
    public List<CategorieResponse> getCategories() {
        List<CategorieLivre> categories = categorieRepository.findAll(Sort.by(Sort.Direction.DESC, "idCategorie"));
        List<CategorieResponse> categorieResponses = new ArrayList<>();

        categories.forEach(categorie1 -> {
            CategorieResponse categorieResponse = new CategorieResponse();

            categorieResponse.setIdCategorie(categorie1.getIdCategorie());
            categorieResponse.setDesignationCategorie(categorie1.getDesignation());

            categorieResponses.add(categorieResponse);
        });

        return categorieResponses;


    }

    @Override
    public CategorieResponse getCategorieById(Long id) {
        CategorieLivre categorieLivre=categorieRepository.findById(id).get();
        CategorieResponse categorieResponse=new CategorieResponse();
        categorieResponse.setIdCategorie(categorieLivre.getIdCategorie());
        categorieResponse.setDesignationCategorie(categorieLivre.getDesignation());

        return categorieResponse;
    }

    @Override
    public List<SousCategorueResponse> getSousCategories() {
        List<SousCategorie> categories = sousCategorieRepository.findAll();
        List<SousCategorueResponse> categorieResponses = new ArrayList<>();

        categories.forEach(categorie1 -> {
            SousCategorueResponse categorieResponse = new SousCategorueResponse();

            categorieResponse.setIdSousCategorie(categorie1.getIdSousCategorie());
            categorieResponse.setDesignationSousCategorie(categorie1.getDesignationSousCategorie());
            categorieResponse.setIdCategorie(categorie1.getCategorie().getIdCategorie());

            categorieResponses.add(categorieResponse);
        });

        return categorieResponses;
    }

    @Override
    public SousCategorueResponse getSousCategorieById(Long id) {
        SousCategorie sousCategorie=sousCategorieRepository.findById(id).get();
        SousCategorueResponse sousCategorueResponse=new SousCategorueResponse();

        sousCategorueResponse.setIdSousCategorie(sousCategorie.getIdSousCategorie());
        sousCategorueResponse.setDesignationSousCategorie(sousCategorie.getDesignationSousCategorie());
        sousCategorueResponse.setIdCategorie(sousCategorie.getCategorie().getIdCategorie());

        return sousCategorueResponse;
    }

    @Override
    public List<EditeurResponse> getEditeurs() {
        List<Editeur> editeurs = editeurRepository.findAll();
        List<EditeurResponse> editeurResponses = new ArrayList<>();

        editeurs.forEach(editeur1 -> {
            EditeurResponse editeurResponse = new EditeurResponse();

            editeurResponse.setIdEditeur(editeur1.getIdEditeur());
            editeurResponse.setNomEditeur(editeur1.getNomEditeur());
            editeurResponse.setAdresseEditeur(editeur1.getAdresseEditeur());

            editeurResponses.add(editeurResponse);
        });

        return editeurResponses;
    }

    @Override
    public EditeurResponse getEditeurById(Long id) {
        Editeur editeur=editeurRepository.findById(id).get();

        EditeurResponse editeurResponse=new EditeurResponse();

        editeurResponse.setIdEditeur(editeur.getIdEditeur());
        editeurResponse.setNomEditeur(editeur.getNomEditeur());
        editeurResponse.setAdresseEditeur(editeur.getAdresseEditeur());

        return editeurResponse;
    }

    @Override
    public List<AuteurResponse> getAuteurs() {
        List<Auteur> auteurs = auteurRepository.findAll();
        List<AuteurResponse> auteurResponses = new ArrayList<>();

        auteurs.forEach(auteur1 -> {
            AuteurResponse auteurResponse = new AuteurResponse();

            auteurResponse.setIdAuteur(auteur1.getIdAuteur());
            auteurResponse.setNomAuteur(auteur1.getNomAuteur());

            auteurResponses.add(auteurResponse);
        });

        return auteurResponses;
    }

    @Override
    public AuteurResponse getAuteurById(Long id) {
        Auteur auteur=auteurRepository.findById(id).get();

        AuteurResponse auteurResponse=new AuteurResponse();
        auteurResponse.setIdAuteur(auteur.getIdAuteur());
        auteurResponse.setNomAuteur(auteur.getNomAuteur());
        return auteurResponse;
    }


    /*Delete*/
    @Override
    public List<LivreResponse> deleteLivre(Long idLivre) {
        Livre livre=livreRepository.findById(idLivre).get();
        LivreElectronique livreElectronique=livreElectroniqueRepository.findById(livre.getLivreElectronique().getIdLivreElectronique()).get();

        fileManager.removeImage(livre.getCoverLivre());
        fileManager.removeDocument(livre.getLivreElectronique().getSourceLivreElectronique());

        livreElectroniqueRepository.delete(livreElectronique);
        livreRepository.delete(livre);

        List<Livre> livres=livreRepository.findAll();
        List<LivreResponse> livreResponses=new ArrayList<>();

        livres.forEach(livre1 -> {
            LivreResponse livreResponse=livreToLivreResponse(livre1);
            livreResponses.add(livreResponse);
        });

        return livreResponses;
    }

    @Override
    public List<CategorieResponse> deleteCategorie(Long idCategorie) {
        return null;
    }

    @Override
    public List<CategorieResponse> deleteAuteur(Long idAuteur) {
        return null;
    }

    @Override
    public List<CategorieResponse> deleteEditeur(Long idEditeur) {
        return null;
    }

    @Override
    public List<CategorieResponse> deleteSousCategorie(Long idSousCategorie) {
        return null;
    }

    @Override
    public List<LivreResponse> searchLivreByTitre(String titre) {
        List<Livre> livres=livreRepository.findByTitreLivreContainingIgnoreCaseOrderByIdLivreDesc(titre);

        List<LivreResponse> livreResponses=new ArrayList<>();

        livres.forEach(livre -> {
            LivreResponse livreResponse=livreToLivreResponse(livre);
            livreResponses.add(livreResponse);
        });

        return livreResponses;
    }

    @Override
    public List<LivreResponse> getLivreBySousCategorie(Long idSousCategorie) {
        SousCategorie sousCategorie=sousCategorieRepository.findById(idSousCategorie).get();
        List<Livre> livres=livreRepository.findBySousCategorieOrderByIdLivreDesc(sousCategorie);

        List<LivreResponse> livreResponses=new ArrayList<>();

        livres.forEach(livre -> {
            LivreResponse livreResponse=livreToLivreResponse(livre);
            livreResponses.add(livreResponse);
        });

        return livreResponses;
    }

    @Override
    public List<LivreResponse> getLivreByAuteur(Long idAuteur) {
        Auteur auteur=auteurRepository.findById(idAuteur).get();
        List<Livre> livres=livreRepository.findByAuteurOrderByIdLivreDesc(auteur);

        List<LivreResponse> livreResponses=new ArrayList<>();

        livres.forEach(livre -> {
            LivreResponse livreResponse=livreToLivreResponse(livre);
            livreResponses.add(livreResponse);
        });

        return livreResponses;
    }

    @Override
    public Integer compterLivres() {
        List<Livre> livres=livreRepository.findAll();
        Integer nombreLivre=0;
        nombreLivre=livres.size();
        return nombreLivre;
    }

    @Override
    public Integer compterCategories() {
        List<CategorieLivre> categorieLivres=categorieRepository.findAll();
        Integer nombreCategorie=0;
        nombreCategorie=categorieLivres.size();
        return nombreCategorie;
    }

    @Override
    public List<Integer> dashboard() {
        return null;
    }


    /*Methode utilitaire*/
    private Livre livreRequestToLivre(LivreRequest livreRequest){
        Livre livre=new Livre();
        livre.setIsbnLivre(livreRequest.getIsbnLivre());
        livre.setTitreLivre(livreRequest.getTitreLivre());
        livre.setDatePublicationLivre(livreRequest.getDatePublicationLivre());
        livre.setEditionLivre(livreRequest.getEditionLivre());
        livre.setNbPageLivre(livreRequest.getNbPageLivre());


        Auteur auteur = auteurRepository.findById(livreRequest.getIdAuteur()).get();
        Editeur editeur = editeurRepository.findById(livreRequest.getIdEditeur()).get();
        SousCategorie sousCategorie = sousCategorieRepository.findById(livreRequest.getIdSousCategorie()).get();

        livre.setAuteur(auteur);
        livre.setEditeur(editeur);
        livre.setSousCategorie(sousCategorie);

        return livre;
    }
    private LivreResponse livreToLivreResponse(Livre livre){
        LivreResponse livreResponse=new LivreResponse();
        livreResponse.setIdLivre(livre.getIdLivre());
        livreResponse.setIsbnLivre(livre.getIsbnLivre());
        livreResponse.setTitreLivre(livre.getTitreLivre());
        livreResponse.setDatePublicationLivre(livre.getDatePublicationLivre());
        livreResponse.setEditionLivre(livre.getEditionLivre());
        livreResponse.setNbPageLivre(livre.getNbPageLivre());
        livreResponse.setCoverLivre(livre.getCoverLivre());
        livreResponse.setDocumentSource(livre.getLivreElectronique().getSourceLivreElectronique());

        livreResponse.setIdAuteur(livre.getAuteur().getIdAuteur());
        livreResponse.setIdEditeur(livre.getEditeur().getIdEditeur());
        livreResponse.setIdSousCategorie(livre.getSousCategorie().getIdSousCategorie());

        livreResponse.setNomAuteur(livre.getAuteur().getNomAuteur());
        livreResponse.setNomEditeur(livre.getEditeur().getNomEditeur());
        livreResponse.setDesignationSousCategorie(livre.getSousCategorie().getDesignationSousCategorie());

        return livreResponse;
    }


}
