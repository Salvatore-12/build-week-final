package Team3.buildweekfinal.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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

    @OneToMany(mappedBy = "address")
    private List<Area> areas;

}