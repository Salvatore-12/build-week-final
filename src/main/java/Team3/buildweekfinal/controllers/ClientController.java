package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

}
