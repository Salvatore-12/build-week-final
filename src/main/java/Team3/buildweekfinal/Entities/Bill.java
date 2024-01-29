package Team3.buildweekfinal.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

}
