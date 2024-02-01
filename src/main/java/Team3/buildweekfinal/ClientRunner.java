package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.*;
import Team3.buildweekfinal.repositories.BillsDAO;
import Team3.buildweekfinal.repositories.ClientsDAO;
import Team3.buildweekfinal.repositories.UsersDAO;
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

    Faker faker = new Faker(new Locale("it"));

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean errors = false;
        do {
            System.out.println("Vuoi Procedere Con la Creazione Dei Clienti (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y" -> {
                    createClients();
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

    public long getRandomNumberLong() {
        Random rmd = new Random();
        long rmdLongNumber = rmd.nextLong(10000);
        return rmdLongNumber;
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
        Client alexClient = new Client();
        alexClient.setEmail(faker.internet().emailAddress());
        alexClient.setInsertDate(LocalDate.of(2022, 3, 12));
        alexClient.setLastCall(LocalDate.now().minusDays(30));
        alexClient.setAnnualTurnOver(5610000);
        alexClient.setPec(alex.getEmail());
        alexClient.setNumber(1L);
        alexClient.setCtype(CTYPE.SPA);
        alexClient.setUser(alex);
        clientsDAO.save(alexClient);
        Bill fatturaAlex = new Bill(LocalDate.of(2023,5,26),500000,5,alexClient);
        billsDAO.save(fatturaAlex);

        for (int i = 0; i < 10; i++) {
            String email = faker.internet().emailAddress();
            LocalDate insertDate = LocalDate.of(2016, 11, 10);
            LocalDate lastCall = LocalDate.now().minusDays(30);
            double annualTurnOver = 300000;
            String pec = faker.internet().emailAddress();
            Long number = getRandomNumberLong();
            Client client = new Client();
            client.setEmail(email);
            client.setInsertDate(insertDate);
            client.setLastCall(lastCall);
            client.setAnnualTurnOver(annualTurnOver);
            client.setPec(pec);
            client.setNumber(number);
            int lenght = CTYPE.values().length;
            Random rmd = new Random();
            CTYPE ctype = CTYPE.values()[rmd.nextInt(lenght)];
            client.setCtype(ctype);
            client.setUser(getRandomUserDB());
            clientsDAO.save(client);

        }
    }
}
