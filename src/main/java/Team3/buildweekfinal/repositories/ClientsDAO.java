package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientsDAO extends JpaRepository<Client,UUID>
{
    Optional<Client>findByAnnualTurnOver(double annualTurnOver);
    Optional<Client> findByInsertDate(LocalDate insertDate);
    Optional<Client> findByLastCall(LocalDate lastCall);
    Optional<User> findByNameContainingIgnoreCase(String name);
    Optional<Bill>findByClient(String email);
    Optional<Bill>findByCtype(CTYPE ctype);
    Optional<Bill>findByDate(LocalDate date);
    Optional<Bill>findByYear(int year);
    Optional<Bill>findByTotalIsLessThanMinAndIsGreaterThanMax(int min ,int max,double total);

}
