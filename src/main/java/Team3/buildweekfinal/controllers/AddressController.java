package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Address;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.payloads.AddressDTO;
import Team3.buildweekfinal.payloads.AddressRensponeDTO;
import Team3.buildweekfinal.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Address> getAllAddresses(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String orderBy) {
        return addressService.getAllAdresses(page, size, orderBy);
    }

    @GetMapping("/{idAddress}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Address getAddressById(@PathVariable UUID id){
        return addressService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public AddressRensponeDTO createNewAddress(@RequestBody AddressDTO body, BindingResult validation){
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            Address newAddress = addressService.createNewAddress(body);
            return new AddressRensponeDTO(newAddress.getIdAddress());
        }

    }

    @DeleteMapping("/{idAddress}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAddress(@PathVariable UUID idAddress){
        addressService.deleteAddress(idAddress);
    }


}
