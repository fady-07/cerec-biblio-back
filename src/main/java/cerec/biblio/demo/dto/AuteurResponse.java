package cerec.biblio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuteurResponse {
    private Long idAuteur;
    private String nomAuteur;
    private String nationaliteAuteur;
}
