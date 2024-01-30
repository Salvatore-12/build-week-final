package Team3.buildweekfinal.services;

import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.exceptions.UnauthorizedException;
import Team3.buildweekfinal.payloads.UserLoginDTO;
import Team3.buildweekfinal.payloads.UsersDTO;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.repositories.UsersDAO;
import Team3.buildweekfinal.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public User save(UsersDTO body)
    {
        usersDAO.findByEmail(body.email()).ifPresent(user ->
        {
            throw new BadRequestException("l' email " + user.getEmail() +" è già in uso");
        });
        User newUser=new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        return usersDAO.save(newUser);
    }

    public String authenticateUser(UserLoginDTO body) {
        User user = usersService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}