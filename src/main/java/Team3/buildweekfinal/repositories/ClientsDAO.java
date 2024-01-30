package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientsDAO extends JpaRepository<Client,UUID>
{
    Optional<Client>findByannualTurnOver(double annualTurnOver);
    Optional<Client>orderByInsertDate(LocalDate insertDate);
    Optional<Client>orderByLastCall(LocalDate lastCall);
    Optional<Client>findByBill(Bill bill);
    Optional<Province>orderByProvince(String province);
}
