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
public class Editeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEditeur;
    private String nomEditeur;
    private String adresseEditeur;
    @OneToMany(mappedBy = "editeur")
    private Collection<Livre> livres;
}
