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
public class SousCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousCategorie;
    private String designationSousCategorie;
    @ManyToOne
    private CategorieLivre categorie;
    @OneToMany(mappedBy = "sousCategorie")
    private Collection<Livre> livres;

}
