package cerec.biblio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivreRequest {
    private Long idLivre;
    private String isbnLivre;
    private String titreLivre;
    private int editionLivre;
    private int nbPageLivre;
    private Date datePublicationLivre;
    private Long idAuteur;
    private Long idEditeur;
    private Long idSousCategorie;
}
