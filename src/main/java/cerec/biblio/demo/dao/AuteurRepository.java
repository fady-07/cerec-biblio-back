package cerec.biblio.demo.dao;


import cerec.biblio.demo.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    Auteur findByNomAuteur(String nom);
}
