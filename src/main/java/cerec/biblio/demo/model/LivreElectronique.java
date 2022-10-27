package cerec.biblio.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivreElectronique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivreElectronique;
    private String sourceLivreElectronique;
    @OneToOne(mappedBy = "livreElectronique")
    private Livre livre;

}
