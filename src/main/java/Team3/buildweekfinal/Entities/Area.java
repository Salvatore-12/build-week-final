package Team3.buildweekfinal.Entities;

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
public class Area
{
    @Id
    @GeneratedValue
    private UUID idArea;
    private String area;
    private int provinceCode;
    private int provinceProgressive;
    @ManyToOne
    private Address address;
    @OneToMany
    private Province province;
}
