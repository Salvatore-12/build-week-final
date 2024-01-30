package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface AreasDAO extends JpaRepository<Area,UUID> {
}
