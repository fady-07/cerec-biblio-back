package cerec.biblio.demo.dao;

import cerec.biblio.demo.model.Auteur;
import cerec.biblio.demo.model.Livre;
import cerec.biblio.demo.model.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre,Long> {
    List<Livre> findByTitreLivreContainingIgnoreCaseOrderByIdLivreDesc(String mc);
    List<Livre> findByAuteurOrderByIdLivreDesc(Auteur auteur);
    List<Livre> findBySousCategorieOrderByIdLivreDesc(SousCategorie sousCategorie);
    Livre findByIdLivre(Long id);
}
