package cerec.biblio.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/*@Entity*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoleAuteur;
    private String designationRoleAuteur;
    @OneToMany(mappedBy = "roleAuteur")
    private Collection<AuteurLivre> auteurLivres;

}
