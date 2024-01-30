package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillsDAO extends JpaRepository<Bill,UUID>
{
    Optional<Bill>findByClient(String email);
    Optional<Bill>findByCtype(CTYPE ctype);
    Optional<Bill>findByDate(LocalDate date);
    Optional<Bill>findByYear(int year);
    Optional<Bill>findByTotalIsLessThanMinAndIsGreaterThanMax(int min ,int max,double total);
}
