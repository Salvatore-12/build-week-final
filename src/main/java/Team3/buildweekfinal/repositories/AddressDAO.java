package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressDAO extends JpaRepository<Address, UUID> {
    Page<Address> findByClientPiva(Pageable pageable,UUID piva);

}
