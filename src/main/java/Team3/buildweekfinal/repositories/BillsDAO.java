package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillsDAO extends JpaRepository<Bill,UUID>
{

    Optional<Bill> findByDate(LocalDate date);
    @Query("SELECT i FROM Bill i WHERE YEAR(i.date)= :year")
    Optional<Bill> filterByYear(@Param("year") int year);
    @Query("SELECT b FROM Bill b WHERE b.total BETWEEN :minAmount AND :maxAmount")
    Optional<Bill> filterBytotal(@Param("minAmount")double minAmount,@Param("maxAmount")double maxAmount);
    Page<Bill> findByClientPiva(Pageable pageable, UUID clientId);
}
