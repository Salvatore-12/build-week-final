package Team3.buildweekfinal.repositories;

import Team3.buildweekfinal.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientsDAO extends JpaRepository<Client,UUID>
{
    Optional<Client>findByannualTurnOver(double annualTurnOver);
}
