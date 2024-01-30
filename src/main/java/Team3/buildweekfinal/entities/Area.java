package Team3.buildweekfinal.entities;

import jakarta.persistence.*;
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
public class Area
{
    @Id
    @GeneratedValue
    private UUID idArea;
    private String area;
    private String provinceCode;
    private String provinceProgressive;
    private String provinceName;

    @OneToMany(mappedBy = "area")
    private List<Address> addressList;

//    @ManyToOne
//    private Province province;
}
