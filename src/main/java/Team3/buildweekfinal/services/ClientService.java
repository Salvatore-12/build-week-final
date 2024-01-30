package Team3.buildweekfinal.services;

import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.payloads.ClientsDTO;
import Team3.buildweekfinal.exceptions.NotFoundException;
import Team3.buildweekfinal.repositories.ClientsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService
{
    @Autowired
    private ClientsDAO clientsDAO;

    public Page<Client> getClients(int page, int size, String orderBy)
    {
        if(size<=100)size=100;
        Pageable pageable= PageRequest.of(page,size, Sort.by(orderBy));
        return clientsDAO.findAll(pageable);
    }
    public Client findByID(UUID id)
    {

        return clientsDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete(UUID id)
    {
        Client found=this.findByID(id);
        clientsDAO.delete(found);
    }
    public Client findByIdAndUpdate(UUID id, ClientsDTO body)
    {
        Client found=this.findByID(id);
        found.setEmail(body.email());
        found.setInsertDate(body.insertDate());
        found.setLastCall(body.lastCall());
        found.setAnnualTurnOver(body.annualTurnOver());
        found.setPec(body.pec());
        found.setNumber(body.number());
        found.setCtype(CTYPE.valueOf(body.ctype()));
        return clientsDAO.save(found);
    }
    public Client findByannualTurnOver(double annualTurnOver)
    {
        return clientsDAO.findByannualTurnOver(annualTurnOver).orElseThrow(()->new NotFoundException(String.valueOf(annualTurnOver)));
    }
}
