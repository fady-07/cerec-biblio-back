package cerec.biblio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivreResponse {
    private Long idLivre;
    private String isbnLivre;
    private String titreLivre;
    private String coverLivre;
    private int editionLivre;
    private int nbPageLivre;
    private Date datePublicationLivre;
    private String documentSource;

    private Long idAuteur;
    private Long idEditeur;
    private Long idSousCategorie;

    private String nomEditeur;
    private String nomAuteur;
    private String designationSousCategorie;
}
