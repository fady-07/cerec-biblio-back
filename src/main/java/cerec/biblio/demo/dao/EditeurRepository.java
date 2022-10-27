package cerec.biblio.demo.dao;


import cerec.biblio.demo.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeurRepository extends JpaRepository<Editeur,Long> {
    Editeur findByNomEditeur(String nom);
}
