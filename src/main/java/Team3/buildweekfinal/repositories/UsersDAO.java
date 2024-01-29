package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional <User> findByName(String name);
}
