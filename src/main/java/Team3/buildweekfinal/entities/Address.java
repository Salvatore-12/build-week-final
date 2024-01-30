package Team3.buildweekfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address
{
    @Id
    @GeneratedValue
    private UUID idAddress;
    private String address;
    private int cv;
    private String city;
    private String cap;

    @ManyToOne
    private Area area;

    @OneToMany(mappedBy = "address")
    private Client client;
}
