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
@NoArgsConstructor
@AllArgsConstructor
public class Province
{
    @Id
    @GeneratedValue
    private UUID idRegistryOffice;
    private String province;
    private String provinceName;

    @OneToMany(mappedBy = "province")
    private List<Area> areaList;
}