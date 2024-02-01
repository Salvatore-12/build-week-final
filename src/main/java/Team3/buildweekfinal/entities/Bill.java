package Team3.buildweekfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill
{
    @Id
    @GeneratedValue
    private UUID idBill;
    private LocalDate date;
    private double total;
    private int billNumber;
    @ManyToOne
    private Client client;

    public Bill(LocalDate date, double total, int billNumber, Client client) {
        this.date = date;
        this.total = total;
        this.billNumber = billNumber;
        this.client = client;
    }
}
