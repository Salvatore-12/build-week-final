package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.payloads.UserLoginDTO;
import Team3.buildweekfinal.payloads.UserLoginResponseDTO;
import Team3.buildweekfinal.payloads.UsersDTO;
import Team3.buildweekfinal.payloads.UsersResponseDTO;
import Team3.buildweekfinal.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponseDTO createUser(@RequestBody @Validated UsersDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            User newUser = authService.save(newUserPayload);

            return new UsersResponseDTO(newUser.getIdUser());
        }
    }
}