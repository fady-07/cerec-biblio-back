package cerec.biblio.demo.dao;


import cerec.biblio.demo.model.LivreElectronique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreElectroniqueRepository extends JpaRepository<LivreElectronique,Long> {
}
