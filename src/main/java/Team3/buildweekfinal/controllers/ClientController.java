package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping("/{piva}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Client getClient(@PathVariable UUID id)
    {
        return clientService.findByID(id);
    }
    @PutMapping("/{piva}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Client getClientByIdAndUpdate(@PathVariable UUID id, Team3.buildweekfinal.Payloads.ClientsDTO modifyClientPayload)
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
        return clientService.findByID(currentClient.getPiva());
    }
    @PutMapping("/me")
    public Client modifyClient(@AuthenticationPrincipal Client currentClient, Team3.buildweekfinal.Payloads.ClientsDTO modifyClientPayload)
    {
        return clientService.findByIdAndUpdate(currentClient.getPiva(),modifyClientPayload);
    }
    @DeleteMapping("/me")
    public void deleteClient(@AuthenticationPrincipal Client currentClient)
    {
        clientService.findByIdAndDelete(currentClient.getPiva());
    }
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

/*
    @GetMapping("/findByClient")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Bill findByClient(@RequestParam String email){
        return clientService.findByClient(email);
    }
    @GetMapping("/findByCtype")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Bill findByCtype(@RequestParam CTYPE ctype){
        return clientService.findByCtype(ctype);
    }

    @GetMapping("/findByDate")
    public Bill findByDate(@RequestParam LocalDate date){
        return clientService.findByDate(date);
    }

    @GetMapping("/findByYear")
    public Bill findByYear(@RequestParam int year){
        return clientService.findByYear(year);
    }

    @GetMapping("/RangeOfAmmounts")
    public Bill findByTotalIsLessThanMinAndIsGreaterThanMax(@RequestParam int min,int max,double total){
        return clientService.findByRange(min,max,total);
    }
*/


}
