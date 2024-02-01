package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.CTYPE;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.repositories.ClientsDAO;
import Team3.buildweekfinal.repositories.UsersDAO;
import Team3.buildweekfinal.services.ClientService;
import Team3.buildweekfinal.services.UsersService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
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

    Faker faker=new Faker(new Locale("it"));
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
    public User getRandomUserDB(){
        List<User> userList=usersDAO.findAll();
        Random rmd=new Random();
        int rmdIndex=rmd.nextInt(userList.size());
       return userList.get(rmdIndex);

    }
    public double getRadomNumberDouble(){
        Random rmd=new Random();
        double rmdDate=rmd.nextDouble(20000,300000);
        return rmdDate;
    }
    public long getRandomNumberLong(){
        Random rmd=new Random();
        long rmdLongNumber=rmd.nextLong(10000);
        return rmdLongNumber;
    }
    public LocalDate getRandomDate(){
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

        for (int i = 0; i < 10; i++) {
            String email = faker.internet().emailAddress();
            LocalDate insertDate = LocalDate.of(2016,11,10);
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
            int lenght= CTYPE.values().length;
            Random rmd=new Random();
            CTYPE ctype=CTYPE.values()[rmd.nextInt(lenght)];
            client.setCtype(ctype);
            client.setUser(getRandomUserDB());
            clientsDAO.save(client);



        }
    }
}
