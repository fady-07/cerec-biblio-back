package cerec.biblio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieResponse {
    private Long idCategorie;
    private String designationCategorie;
}
