package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.Bill;
import Team3.buildweekfinal.entities.Client;
import Team3.buildweekfinal.entities.User;
import Team3.buildweekfinal.repositories.BillsDAO;
import Team3.buildweekfinal.repositories.ClientsDAO;
import Team3.buildweekfinal.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
@Order(4)
public class BillRunner implements CommandLineRunner {
    @Autowired
    private BillService billService;
    @Autowired
    private BillsDAO billsDAO;
    @Autowired
    private ClientsDAO clientsDAO;
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean errors = false;
        do {
            System.out.println("Vuoi Procedere Con la Creazione Delle fatture (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y" -> {
                    createBills();
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
    public Client getClients(){
        List<Client> clientList=clientsDAO.findAll();
        Random rmd=new Random();
        int rmdIndex=rmd.nextInt(clientList.size());
        return clientList.get(rmdIndex);
    }
    public void createBills(){
        Bill fattura1=new Bill(LocalDate.of(2023,12,12),300.00,1,getClients());
        Bill fattura2=new Bill(LocalDate.of(2022,6,4),400,2,getClients());
        Bill fattura3=new Bill(LocalDate.of(2022,8,15),250,3,getClients());
        Bill fattura4=new Bill(LocalDate.of(2024,1,15),350,4,getClients());
        Bill fattura5=new Bill(LocalDate.of(2021,5,26),500,5,getClients());
        billsDAO.save(fattura1);
        billsDAO.save(fattura2);
        billsDAO.save(fattura3);
        billsDAO.save(fattura4);
        billsDAO.save(fattura5);


    }
}
