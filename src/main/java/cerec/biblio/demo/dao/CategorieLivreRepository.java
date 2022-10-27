package cerec.biblio.demo.dao;

import cerec.biblio.demo.model.CategorieLivre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieLivreRepository extends JpaRepository<CategorieLivre,Long> {
    CategorieLivre findByDesignation(String designation);
}
