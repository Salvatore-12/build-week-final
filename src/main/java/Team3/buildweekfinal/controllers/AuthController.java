package Team3.buildweekfinal.controllers;

import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.payloads.UserLoginDTO;
import Team3.buildweekfinal.payloads.UserLoginResponseDTO;
import Team3.buildweekfinal.payloads.UserDTO;
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
    public UserLoginResponseDTO login(@RequestBody @Validated UserLoginDTO body, BindingResult validation) {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            String accessToken = authService.authenticateUser(body);
            return new UserLoginResponseDTO(accessToken);
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponseDTO createUser(@RequestBody @Validated UserDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            User newUser = authService.save(newUserPayload);

            return new UsersResponseDTO(newUser.getIdUser());
        }
    }
}
