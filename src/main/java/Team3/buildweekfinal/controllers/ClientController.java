package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.payloads.ClientsDTO;
import Team3.buildweekfinal.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/Client")
public class ClientController
{
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Page<Client> getClient(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10")int size,
                                  @RequestParam(defaultValue = "email")String orderBy)
    {
        return clientService.getClients(page,size,orderBy);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestParam UUID idBill,@RequestParam UUID idUser,@RequestParam UUID idAddress,@RequestBody ClientsDTO body,BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return clientService.save(idBill,idUser,idAddress,body);
        }
    }

    @GetMapping("/{piva}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Client getClient(@PathVariable UUID id)
    {
        return clientService.findById(id);
    }

    @PutMapping("/{piva}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Client getClientByIdAndUpdate(@PathVariable UUID id, ClientsDTO modifyClientPayload)
    {
        return clientService.findByIdAndUpdate(id,modifyClientPayload);
    }
    @DeleteMapping("/{piva}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id)
    {
        clientService.findByIdAndDelete(id);
    }
    @GetMapping("/me")
    public Client getCurrentClient(@AuthenticationPrincipal Client currentClient)
    {
        return clientService.findById(currentClient.getPiva());
    }

    @PutMapping("/me")
    public Client modifyClient(@AuthenticationPrincipal Client currentClient, ClientsDTO modifyClientPayload)
    {
        return clientService.findByIdAndUpdate(currentClient.getPiva(),modifyClientPayload);
    }
    @DeleteMapping("/me")
    public void deleteClient(@AuthenticationPrincipal Client currentClient)
    {
        clientService.findByIdAndDelete(currentClient.getPiva());
    }
    //**********************************QUERY BLOCCO 1************************************************
    @GetMapping("/findByAnnualTurnOver")
    public Client findByAnnualTurnOver(@RequestParam double annualTurnOver)
    {
        return clientService.findByAnnualTurnOver(annualTurnOver);
    }
    @GetMapping("/findByInsertDate")
    public Client findByInsertDate(@RequestParam LocalDate insertDate)
    {
        return clientService.findByInsertDate(insertDate);
    }
    @GetMapping("/findBylastCall")
    public  Client findBylastCall(@RequestParam LocalDate lastCall)
    {
        return clientService.findBylastCall(lastCall);
    }
    @GetMapping("/findByPartName")
    public User findByPartName(@RequestParam  String name)
    {
        return clientService.findByPartName(name);
    }
    //************************************************QUERY PARTE 2************************************************
    @GetMapping("/findBillByEmail")
    public Bill findByEmail(@RequestParam String email)
    {
        return clientService.findByEmail(email);
    }
    @GetMapping("/findByCtype")
    public Bill findByCtype(@RequestParam CTYPE ctype)
    {
        return clientService.findByCtype(ctype);
    }
    @GetMapping("/findBydate")
    public Bill findBydate(@RequestParam LocalDate date)
    {
        return clientService.findBydate(date);
    }
    @GetMapping("/findByYear")
    public Bill findByYear(@RequestParam int year)
    {
        return clientService.findByYear(year);
    }
    @GetMapping("/filterBytotal")
    public Bill filterBytotal(@RequestParam double minAmount,@RequestParam double maxAmount)
    {
        return clientService.filterBytotal(minAmount,maxAmount);
    }


}
