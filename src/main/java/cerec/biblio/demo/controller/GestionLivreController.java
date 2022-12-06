package cerec.biblio.demo.controller;



import cerec.biblio.demo.config.PDFToImageConvert;
import cerec.biblio.demo.dao.LivreRepository;
import cerec.biblio.demo.dto.*;
import cerec.biblio.demo.model.Livre;
import cerec.biblio.demo.service.AppConstant;
import cerec.biblio.demo.service.FileManager;
import cerec.biblio.demo.service.gestionLivre.LivreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/livres")
public class GestionLivreController {

    private LivreRepository livreRepository;
    private final FileManager fileManager;
    private LivreService livreService;

    public GestionLivreController(LivreRepository livreRepository, FileManager fileManager, LivreService livreService) {
        this.livreRepository = livreRepository;
        this.fileManager = fileManager;
        this.livreService = livreService;
    }


    @PostMapping("/add-livre")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public LivreResponse addLivre(@RequestParam("livreRequest") String livre,
                                  @RequestParam("image") CommonsMultipartFile image,
                                  @RequestParam("pdf") CommonsMultipartFile file) throws IOException {

        LivreRequest livreRequest = new ObjectMapper().readValue(livre, LivreRequest.class);
        String urlImage = AppConstant.SUFFIX + fileManager.uploadImage(image);
        String urlPdf = fileManager.uploadDocument(file);

        System.out.println(livreRequest.toString());

        return livreService.addLivre(livreRequest,urlImage,urlPdf);
    }


    @PostMapping("/update-livre")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<LivreResponse> updateLivre(@RequestParam(required = false, name = "image") MultipartFile image,
                                           @RequestParam(required = false, name = "pdf") MultipartFile file,
                                           @RequestParam("livreRequest") String livre) throws IOException {

        LivreRequest livreRequest = new ObjectMapper().readValue(livre, LivreRequest.class);

        String urlImage="";
        String urlPdf="";

        if(image!=null) {
            urlImage = AppConstant.SUFFIX + fileManager.updateImage(image);
        }
        if(file!=null) {
            urlPdf = fileManager.updateDocument(file);
        }

        return livreService.updateLivre(livreRequest,urlImage,urlPdf);

    }


    @GetMapping("/pdf/{idLivre}")
    public byte[] getDocumentPdf(@PathVariable("idLivre") Long idLivre) throws IOException {
       return livreService.getDocumentPdf(idLivre);
    }


    @PostMapping("/add-categorie")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<CategorieResponse> addCategorie(@RequestBody CategorieRquest categorieRquest){
        return livreService.addCategorie(categorieRquest);
    }

    @PostMapping("/add-sous-categorie")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<SousCategorueResponse> addSousCategorie(@RequestBody SousCategorieRequest sousCategorieRequest){
        return livreService.addSousCategorie(sousCategorieRequest);
    }

    @PostMapping("/add-editeur")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<EditeurResponse> addEditeur(@RequestBody EditeurRequest editeurRequest){
        return livreService.addEditeur(editeurRequest);
    }

    @PostMapping("/add-auteur")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<AuteurResponse> addAuteur(@RequestBody AuteurRequest auteurRequest){
        return livreService.addAuteur(auteurRequest);
    }

    /*Get*/
    @GetMapping("/get-livres")
    public List<LivreResponse> getLivres(){
        return livreService.getLivres();
    }

    @GetMapping("/get-livre-by-id/{idLivre}")
    public LivreResponse getLivreById(@PathVariable("idLivre") Long idLivre){
        return livreService.getLivreById(idLivre);
    }

    @GetMapping("/get-categories")
    public List<CategorieResponse> getCategories(){
        return livreService.getCategories();
    }

    @GetMapping("/get-categorie-by-id/{idCategorie}")
    public CategorieResponse getCategorieById(@PathVariable("idCategorie") Long idCategorie){
        return livreService.getCategorieById(idCategorie);
    }

    @GetMapping("/get-sous-categories")
    public List<SousCategorueResponse> getSousCategories(){
        return livreService.getSousCategories();
    }

    @GetMapping("/get-sous-categorie-by-id/{idSousCategorie}")
    public SousCategorueResponse getSousCategorieById(@PathVariable("idSousCategorie") Long idSCategorie){
        return livreService.getSousCategorieById(idSCategorie);
    }

    @GetMapping("/get-editeurs")
    public List<EditeurResponse> getEditeurs(){
        return livreService.getEditeurs();
    }

    @GetMapping("/get-editeur-by-id/{idEditeur}")
    public EditeurResponse getEditeurById(@PathVariable("idEditeur") Long idEditeur){
        return livreService.getEditeurById(idEditeur);
    }

    @GetMapping("/get-auteurs")
    public List<AuteurResponse> getAuteurs(){
        return livreService.getAuteurs();
    }

    @GetMapping("/get-auteur-by-id/{idAuteur}")
    public AuteurResponse getAuteurById(@PathVariable("idAuteur") Long idAuteur){
        return livreService.getAuteurById(idAuteur);
    }


    /*delete*/
    @DeleteMapping("/delete-livre/{id}")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<LivreResponse> deleteLivre(@PathVariable("id") Long idLivre) {
        return livreService.deleteLivre(idLivre);
    }

    @DeleteMapping("/delete-categorie/{id}")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<CategorieResponse> deleteCategorie(@PathVariable("id") Long idCategorie) {
        return livreService.deleteCategorie(idCategorie);
    }

    @DeleteMapping("/delete-sous-categorie/{id}")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<CategorieResponse> deleteSousCategorie(@PathVariable("id") Long idSousCategorie) {
        return livreService.deleteSousCategorie(idSousCategorie);
    }

    @DeleteMapping("/delete-auteur/{id}")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<CategorieResponse> deleteAuteur(@PathVariable("id") Long idAuteur) {
        return livreService.deleteAuteur(idAuteur);
    }

    @DeleteMapping("/delete-editeur/{id}")
    @PreAuthorize("hasAnyAuthority('user:create')")
    public List<CategorieResponse> deleteEditeur(@PathVariable("id") Long idEditeur) {
        return livreService.deleteEditeur(idEditeur);
    }

    /*Search*/
    @GetMapping("/get-livres-by-mc")
    public List<LivreResponse> getLivreByMc(@RequestParam("mc") String mc) {
        return livreService.searchLivreByTitre(mc);
    }

    @GetMapping("/get-livres-by-auteur/{idAuteur}")
    public List<LivreResponse> getLivreByAuteur(@PathVariable("idAuteur") Long idAuteur) {
        return livreService.getLivreByAuteur(idAuteur);
    }

    @GetMapping("/get-livres-by-sousCategorie/{idSousCategorie}")
    public List<LivreResponse> getLivreBySousCategorie(@PathVariable("idSousCategorie") Long idSousCategorie) {
        return livreService.getLivreBySousCategorie(idSousCategorie);
    }

    @GetMapping("/livres-counter")
    public Integer compteurLivres(){
        return livreService.compterLivres();
    }

    @GetMapping("/categories-counter")
    public Integer compteurCategories(){
        return livreService.compterCategories();
    }

    @GetMapping("/get-dash")
    public List<Integer> getDash() {
        return livreService.dashboard();
    }

}
