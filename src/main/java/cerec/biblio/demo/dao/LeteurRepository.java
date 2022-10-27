package cerec.biblio.demo.dao;

import cerec.biblio.demo.model.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeteurRepository extends JpaRepository<Lecteur,Long> {
}
