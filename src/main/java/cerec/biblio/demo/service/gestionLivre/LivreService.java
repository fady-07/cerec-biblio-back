package cerec.biblio.demo.service.gestionLivre;


import cerec.biblio.demo.dto.*;

import java.io.IOException;
import java.util.List;

public interface LivreService {

    /*add*/
    LivreResponse addLivre(LivreRequest livreRequest,String urlImage, String urlPdf);
    List<LivreResponse> updateLivre(LivreRequest livreRequest, String urlImage, String urlPdf);

    List<CategorieResponse> addCategorie(CategorieRquest categorieRquest);
    List<SousCategorueResponse> addSousCategorie(SousCategorieRequest sousCategorieRequest);
    List<EditeurResponse> addEditeur(EditeurRequest editeurRequest);
    List<AuteurResponse> addAuteur(AuteurRequest auteurRequest);


    /*get*/
    List<LivreResponse> getLivres();
    LivreResponse getLivreById(Long id);
    byte[] getDocumentPdf(Long id) throws IOException;




    List<CategorieResponse> getCategories();
    CategorieResponse getCategorieById(Long id);

    List<SousCategorueResponse> getSousCategories();
    SousCategorueResponse getSousCategorieById(Long id);

    List<EditeurResponse> getEditeurs();
    EditeurResponse getEditeurById(Long id);

    List<AuteurResponse> getAuteurs();
    AuteurResponse getAuteurById(Long id);


    /*delete*/
    List<LivreResponse> deleteLivre(Long idLivre);
    List<CategorieResponse> deleteCategorie(Long idCategorie);
    List<CategorieResponse> deleteAuteur(Long idAuteur);
    List<CategorieResponse> deleteEditeur(Long idEditeur);
    List<CategorieResponse> deleteSousCategorie(Long idSousCategorie);

    /*Search*/
    List<LivreResponse> searchLivreByTitre(String titre);
    List<LivreResponse> getLivreBySousCategorie(Long idSousCategorie);
    List<LivreResponse> getLivreByAuteur(Long idAuteur);

    Integer compterLivres();

    Integer compterCategories();


    List<Integer> dashboard();
}
