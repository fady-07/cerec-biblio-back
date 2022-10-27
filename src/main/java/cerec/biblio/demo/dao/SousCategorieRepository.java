package cerec.biblio.demo.dao;


import cerec.biblio.demo.model.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SousCategorieRepository extends JpaRepository<SousCategorie,Long> {
    SousCategorie findByDesignationSousCategorie(String designation);
}
