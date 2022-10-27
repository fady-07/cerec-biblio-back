package cerec.biblio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SousCategorueResponse {
    private Long idSousCategorie;
    private String designationSousCategorie;
    private Long idCategorie;
}
