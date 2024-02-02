package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.*;
import Team3.buildweekfinal.repositories.*;
import Team3.buildweekfinal.services.ClientService;
import Team3.buildweekfinal.services.UsersService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Long.parseLong;

@Component
@Order(3)
public class ClientRunner implements CommandLineRunner {
    @Autowired
    private ClientsDAO clientsDAO;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private BillsDAO billsDAO;
    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    AreasDAO areasDAO;

    Faker faker = new Faker(new Locale("it"));

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean errors = false;
        do {
            System.out.println("Vuoi Procedere Con la Creazione Dei Clienti / Indirizzi (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y" -> {
                    createClients();
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

    public User getRandomUserDB() {
        List<User> userList = usersDAO.findAll();
        Random rmd = new Random();
        int rmdIndex = rmd.nextInt(userList.size());
        return userList.get(rmdIndex);

    }


    public double getRadomNumberDouble() {
        Random rmd = new Random();
        double rmdDate = rmd.nextDouble(20000, 300000);
        return rmdDate;
    }



    public LocalDate getRandomDate() {
        Random random = new Random();
        int annoCasuale = random.nextInt(2024 - 2000 + 1) + 2000;
        int meseCasuale = random.nextInt(12) + 1;
        int giornoCasuale = random.nextInt(giorniInUnMese(annoCasuale, meseCasuale)) + 1;
        LocalDate dataCasuale = LocalDate.of(annoCasuale, meseCasuale, giornoCasuale);
        System.out.println("Data casuale generata: " + dataCasuale);
        return dataCasuale;
    }

    private static int giorniInUnMese(int anno, int mese) {
        return LocalDate.of(anno, mese, 1).lengthOfMonth();
    }

    public void createClients() {
        String defaultPassword = bcrypt.encode("1234");
        User alex = new User("Alex", "Larionov", "allar11914", "alex.larionov@mail.com", defaultPassword);
        alex.setRole(ROLE.ADMIN);
        usersDAO.save(alex);
        for (int i = 0; i < 10; i++) {
            Random billRnd = new Random();
            Client alexClient = new Client();
            alexClient.setEmail(faker.internet().emailAddress());
            alexClient.setInsertDate(LocalDate.of(faker.number().numberBetween(2010, 2023), 2 + i, 12 + i));
            alexClient.setLastCall(LocalDate.now().minusDays(30));
            alexClient.setAnnualTurnOver(faker.number().numberBetween(1000000, 10000000));
            alexClient.setPec(alex.getEmail());
            alexClient.setNumber(faker.phoneNumber().phoneNumber().substring(1));
            alexClient.setCtype(CTYPE.SPA);
            alexClient.setUser(alex);
            alexClient.setName(faker.company().name());
            clientsDAO.save(alexClient);
            for (int j = 0; j < billRnd.nextInt(4, 10); j++) {
                Bill fatturaAlex = new Bill(LocalDate.of(faker.number().numberBetween(2010, 2023), 2 + i, 13 + i), faker.number().numberBetween(50000, 700000), faker.number().numberBetween(1000, 7000), alexClient);
                billsDAO.save(fatturaAlex);
            }
        }


        for (int i = 0; i < 10; i++) {
            String email = faker.internet().emailAddress();
            LocalDate insertDate = LocalDate.of(2016, 11, 10);
            LocalDate lastCall = LocalDate.now().minusDays(30);
            double annualTurnOver = 300000;
            String pec = faker.internet().emailAddress();
            String number = faker.phoneNumber().phoneNumber();
            Client client = new Client();
            client.setEmail(email);
            client.setInsertDate(insertDate);
            client.setLastCall(lastCall);
            client.setAnnualTurnOver(annualTurnOver);
            client.setPec(pec);
            client.setNumber(number);
            client.setName(faker.company().name());
            int lenght = CTYPE.values().length;
            Random rmd = new Random();
            CTYPE ctype = CTYPE.values()[rmd.nextInt(lenght)];
            client.setCtype(ctype);
            client.setUser(getRandomUserDB());
            clientsDAO.save(client);

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
                    .client(getRandomClientFromDb())
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

    private Client getRandomClientFromDb() {
        List<Client> clients = clientsDAO.findAll();
        if (clients.isEmpty()) {
            throw new IllegalStateException(
                    "Aziende Non Trovate");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(clients.size());
        return clients.get(randomIndex);
    }
}
