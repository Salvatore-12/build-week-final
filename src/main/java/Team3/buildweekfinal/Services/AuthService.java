package Team3.buildweekfinal.Services;

import Team3.buildweekfinal.Entities.User;
import Team3.buildweekfinal.Payloads.UsersDTO;
import Team3.buildweekfinal.exceptions.BadRequestException;
import Team3.buildweekfinal.repositories.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    @Autowired
    private UsersDAO usersDAO;

    public User save(UsersDTO body)
    {
        usersDAO.findByEmail(body.email()).ifPresent(user ->
        {
            throw new BadRequestException("l' email "+ user.getEmail() +" è già in uso");
        });
        User newuser=new User();
        newuser.setName(body.name());
        newuser.setSurname(body.surname());
        newuser.setEmail(body.email());
        newuser.setPassword(body.password());//inserire bcrypt
        return usersDAO.save(newuser);
    }
}
