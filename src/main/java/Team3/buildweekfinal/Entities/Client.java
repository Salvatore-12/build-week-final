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
@AllArgsConstructor
@NoArgsConstructor
public class Client
{
    @Id
    @GeneratedValue
    private UUID piva;
    private String email;
    private LocalDate insertDate;
    private LocalDate lastCall;
    private double annualTurnOver;
    private String pec;
    private Long number;
    @Enumerated(EnumType.STRING)
    private CTYPE ctype;
    @OneToMany(mappedBy = "client")
    private  List <User> user=new ArrayList<>();
    @OneToOne
    private Address address;

}