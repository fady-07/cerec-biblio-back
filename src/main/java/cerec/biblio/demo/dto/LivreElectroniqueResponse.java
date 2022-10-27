package cerec.biblio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivreElectroniqueResponse {
    private Long idLivreElectronique;
    private String sourceLivreElectronique;
    private Long idLivre;
}
