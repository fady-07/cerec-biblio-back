package cerec.biblio.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuteurResponse {
    private Long idRoleAuteur;
    private String designationRoleAuteur;
}
