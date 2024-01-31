package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.Address;
import Team3.buildweekfinal.entities.Area;
import Team3.buildweekfinal.entities.ROLE;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.repositories.AddressDAO;
import Team3.buildweekfinal.repositories.AreasDAO;
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
                    createAddresses();
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
        User alex = new User("Alex", "Larionov", "allar11914", "alex.larionov@mail.com", defaultPassword);
        User fabio = new User("Fabio", "Simonelli", "simo11214", "fabio.simonelli@mail.com", defaultPassword);
        User bruno = new User("Bruno", "Capobianco", "bru13914", "bruno.capobianco@mail.com", defaultPassword);
        User salvatore = new User("Salvatore", "Assennato", "sal11914", "salvatore.assennato@mail.com", defaultPassword);
        User giorgio = new User("Giorgio", "Migliaccio", "gio11914", "giorgio.migliaccio@mail.com", defaultPassword);
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

    public void createAddresses() {
        for (int i = 0; i < 30; i++) {
            String street = faker.address().streetName();
            Integer cv = Integer.parseInt(faker.address().buildingNumber());
            Area area = getRandomArea();
            String cap = area.getProvinceCode();
            addressDAO.save(Address.builder()
                    .address(street)
                    .cv(cv)
                    .cap(cap)
                    .city(area.getProvince().getProvinceName())
                    .area(area).build());
        }
    }

    private Area getRandomArea() {
        List<Area> areas = areasDAO.findAll();
        if (areas.isEmpty()) {
            throw new IllegalStateException(
                    "Comuni non trovati.");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(areas.size());
        return areas.get(randomIndex);
    }

}
