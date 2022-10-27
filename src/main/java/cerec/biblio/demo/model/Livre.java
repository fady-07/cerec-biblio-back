package cerec.biblio.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivre;
    private String isbnLivre;
    private String titreLivre;
    private String coverLivre;
    private int editionLivre;
    private int nbPageLivre;
    private Date datePublicationLivre;
    @ManyToOne
    private Editeur editeur;
    @ManyToOne
    private SousCategorie sousCategorie;

    @ManyToOne
    private Auteur auteur;

    @OneToOne
    private LivreElectronique livreElectronique;

    /*@OneToMany(mappedBy = "livre")
    private Collection<AuteurLivre> auteurLivres;*/

}
