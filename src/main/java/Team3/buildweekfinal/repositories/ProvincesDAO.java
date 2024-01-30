package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvincesDAO extends JpaRepository<Province,String> {
    @Query("SELECT p FROM Province p LEFT JOIN FETCH p.areaList WHERE p.provinceName = :name")
    public Province findByProvinceName(@Param("name") String name);
}
