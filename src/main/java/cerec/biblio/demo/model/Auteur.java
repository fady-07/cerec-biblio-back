package cerec.biblio.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuteur;
    private String nomAuteur;

    @OneToMany(mappedBy = "auteur")
    private Collection<Livre> livres;

    /*private String nationalite_auteur;*/
    /*@OneToMany(mappedBy = "auteur")
    private Collection<AuteurLivre> auteurLivres;*/
}
