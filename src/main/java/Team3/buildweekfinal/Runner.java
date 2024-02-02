package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.*;
import Team3.buildweekfinal.repositories.AddressDAO;
import Team3.buildweekfinal.repositories.AreasDAO;
import Team3.buildweekfinal.repositories.ClientsDAO;
import Team3.buildweekfinal.repositories.UsersDAO;
import Team3.buildweekfinal.services.AddressService;
import Team3.buildweekfinal.services.AuthService;
import Team3.buildweekfinal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

@Component
@Order(2)
public class Runner implements CommandLineRunner {
    @Autowired
    private AreasDAO areasDAO;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthService authService;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    ClientsDAO clientsDAO;
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
        String defaultPassword = bcrypt.encode("1234");
        User fabio = new User("Fabio", "Simonelli", "simo11214", "fabio.simonelli@mail.com", defaultPassword);
        User bruno = new User("Bruno", "Capobianco", "bru13914", "bruno.capobianco@mail.com", defaultPassword);
        User salvatore = new User("Salvatore", "Assennato", "sal11914", "salvatore.assennato@mail.com", defaultPassword);
        User giorgio = new User("Giorgio", "Migliaccio", "gio11914", "giorgio.migliaccio@mail.com", defaultPassword);

        fabio.setRole(ROLE.ADMIN);
        bruno.setRole(ROLE.ADMIN);
        salvatore.setRole(ROLE.ADMIN);
        giorgio.setRole(ROLE.ADMIN);

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
            user.setAvatar("https://ui-avatars.com/api/?name=" + name + "+" + surname + "&background=27262c&color=fff");
            user.setPassword(password);
            usersDAO.save(user);
        }
    }

}
