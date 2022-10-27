package cerec.biblio.demo.dao;



import cerec.biblio.demo.model.RefreshToken;
import cerec.biblio.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);
  RefreshToken findByUser(User user);
  @Modifying
  int deleteByUser(User user);
}
