package cerec.biblio.demo.dao;

import cerec.biblio.demo.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession,Long> {
}
