package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.payloads.UpdateExistingUserDTO;
import Team3.buildweekfinal.payloads.UsersDTO;
import Team3.buildweekfinal.payloads.UsersResponseDTO;
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
import java.util.List;
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
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

    @PostMapping("/me")
    @PreAuthorize("hasAuthority('USER')")
    public User updateUser(@AuthenticationPrincipal User currentUser, @RequestBody UpdateExistingUserDTO body) {
        return authService.updateUser(currentUser, body);
    }
    @GetMapping("/me/{bills}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<Bill> getAllBills(@AuthenticationPrincipal User currentUser){

        return currentUser;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy) {
        return usersService.getUsers(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponseDTO saveUser(@RequestBody @Validated UsersDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            User newUser = authService.save(body);
            return new UsersResponseDTO(newUser.getIdUser());
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public User findById(@PathVariable UUID id) {
        return usersService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void findByIdAndDelete(@PathVariable UUID id) {
        usersService.findByIdAndDelete(id);
    }

    @PostMapping("/{userId}/upload")
    @PreAuthorize("hasAuthority('USER')")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID userId) throws IOException {
        return usersService.uploadPicture(file);
    }
}
