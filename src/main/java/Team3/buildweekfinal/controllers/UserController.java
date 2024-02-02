package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Address;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.payloads.UpdateExistingUserDTO;
import Team3.buildweekfinal.payloads.UserDTO;
import Team3.buildweekfinal.payloads.UsersResponseDTO;
import Team3.buildweekfinal.services.AddressService;
import Team3.buildweekfinal.services.AuthService;
import Team3.buildweekfinal.services.ClientService;
import Team3.buildweekfinal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

    @PutMapping("/me/updateProfile")
    public User updateUser(@AuthenticationPrincipal User currentUser, @RequestBody @Validated UpdateExistingUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return authService.updateUser(currentUser, body);
        }
    }

    @PostMapping("/me/upload")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @AuthenticationPrincipal User currentUser) throws IOException {
        return usersService.uploadPicture(file, currentUser.getIdUser());
    }

    @GetMapping("/me/clients")
    public Page<Client> getPersonalClients(@AuthenticationPrincipal User currentUser,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "piva") String orderBy) {
        return clientService.findPersonalClients(page, size, orderBy, currentUser);
    }
    @GetMapping("/me/clients/addresses")
    public Page<Address> getPersonalAddresses(@AuthenticationPrincipal User currentUser,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "idAddress") String orderBy){
        return addressService.findPersonalAddresses(page, size, orderBy, currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "idUser") String orderBy) {
        return usersService.getUsers(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponseDTO saveUser(@RequestBody @Validated UserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            User newUser = authService.save(body);
            return new UsersResponseDTO(newUser.getIdUser());
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable UUID id) {
        return usersService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable UUID id) {
        usersService.findByIdAndDelete(id);
    }

    @PostMapping("/{userId}/upload")
    public String uploadEventImage(@RequestParam("avatar") MultipartFile file, @PathVariable(required = true) UUID userId) throws IOException {
        return usersService.uploadPicture(file,userId);
    }

}
