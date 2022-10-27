package cerec.biblio.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLecteur;
    private String nomLecteur;
    private String prenomLecteur;
    private String emailLecteur;
    private String passLecteur;
    private boolean actived;
    @ManyToOne
    private Profession profession;
}
