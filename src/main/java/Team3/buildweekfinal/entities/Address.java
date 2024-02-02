package Team3.buildweekfinal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @ManyToOne
    private Client client;
}
