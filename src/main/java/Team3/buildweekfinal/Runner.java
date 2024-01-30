package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.ROLE;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.services.AuthService;
import Team3.buildweekfinal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Runner implements CommandLineRunner {
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        User alex = new User("Alex","Larionov","allar11914","alex.larionov@mail.com","1234");
        User fabio = new User("Fabio","Simonelli","simo11214","fabio.simonelli@mail.com","1234");
        User bruno = new User("Bruno","Capobianco","bru13914","bruno.capobianco@mail.com","1234");
        User salvatore = new User("Salvatore","Assennato","sal11914","salvatore.assennato@mail.com","1234");
        User giorgio = new User("Giorgio","Migliaccio","gio11914","giorgio.migliaccio@mail.com","1234");
        alex.setRole(ROLE.ADMIN);
        fabio.setRole(ROLE.ADMIN);
        bruno.setRole(ROLE.ADMIN);
        salvatore.setRole(ROLE.ADMIN);
        giorgio.setRole(ROLE.ADMIN);
    }
}
