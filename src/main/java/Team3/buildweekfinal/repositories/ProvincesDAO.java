package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.Entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvincesDAO extends JpaRepository<Province,UUID> {
}
