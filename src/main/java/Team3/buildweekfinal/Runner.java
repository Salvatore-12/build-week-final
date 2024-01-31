package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.ROLE;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.repositories.UsersDAO;
import Team3.buildweekfinal.services.AuthService;
import Team3.buildweekfinal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Scanner;

@Component
@Order(2)
public class Runner implements CommandLineRunner {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthService authService;
    @Autowired
    private UsersDAO usersDAO;
    Faker faker = new Faker(new Locale("it"));

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean errors = false;
        do {
            System.out.println("Vuoi Procedere Con la Creazione Degli Utenti (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y" -> {
                    createAdmins();
                    createUsers();
                    errors = false;
                }
                case "n" -> errors = false;
                default -> {
                    System.out.println("Input non valido. Riprova.");
                    errors = true;
                }
            }
        } while (errors);
    }

    public void createAdmins() {
        User alex = new User("Alex", "Larionov", "allar11914", "alex.larionov@mail.com", "1234");
        User fabio = new User("Fabio", "Simonelli", "simo11214", "fabio.simonelli@mail.com", "1234");
        User bruno = new User("Bruno", "Capobianco", "bru13914", "bruno.capobianco@mail.com", "1234");
        User salvatore = new User("Salvatore", "Assennato", "sal11914", "salvatore.assennato@mail.com", "1234");
        User giorgio = new User("Giorgio", "Migliaccio", "gio11914", "giorgio.migliaccio@mail.com", "1234");
        alex.setRole(ROLE.ADMIN);
        fabio.setRole(ROLE.ADMIN);
        bruno.setRole(ROLE.ADMIN);
        salvatore.setRole(ROLE.ADMIN);
        giorgio.setRole(ROLE.ADMIN);
        usersDAO.save(alex);
        usersDAO.save(fabio);
        usersDAO.save(bruno);
        usersDAO.save(salvatore);
        usersDAO.save(giorgio);
    }

    public void createUsers() {
        for (int i = 0; i < 30; i++) {
        String name = faker.funnyName().name();
        String surname = faker.name().lastName();
        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = bcrypt.encode(faker.internet().password());
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(ROLE.USER);
        user.setAvatar("htpps://ui-avatars.com/api/?name=" + name + "+" + surname);
        user.setPassword(password);
        usersDAO.save(user);
        }
    }


}
