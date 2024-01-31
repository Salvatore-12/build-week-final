package Team3.buildweekfinal.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
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
    @ManyToOne
    private Client client;
}
