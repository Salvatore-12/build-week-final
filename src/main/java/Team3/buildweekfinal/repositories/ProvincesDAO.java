package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvincesDAO extends JpaRepository<Province,UUID> {

}
