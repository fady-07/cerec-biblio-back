package cerec.biblio.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*@Entity*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuteurLivre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuteurLivre;
    @ManyToOne
    private Auteur auteur;
    @ManyToOne
    private Livre livre;
    @ManyToOne
    private RoleAuteur roleAuteur;
}
