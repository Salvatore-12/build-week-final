package Team3.buildweekfinal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@AllArgsConstructor
@NoArgsConstructor
public class Client
{
    @Id
    @GeneratedValue
    private UUID piva;
    private String name;
    private String email;
    private LocalDate insertDate;
    private LocalDate lastCall;
    private double annualTurnOver;
    private String pec;
    private String number;
    @Enumerated(EnumType.STRING)
    private CTYPE ctype;
    @ManyToOne
    private User user;
    @JsonBackReference
    @OneToMany(mappedBy = "client")
    private List<Address> address=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Bill> bills =new ArrayList<>();

    public void addBillToClient (Bill bill){
        this.bills.add(bill);
    }

}
