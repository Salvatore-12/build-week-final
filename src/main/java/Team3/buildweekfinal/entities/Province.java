package Team3.buildweekfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    @Id
    private String initials;
    private String provinceName;
    private String region;

    @OneToMany(mappedBy = "province")
    private List<Area> areaList = new ArrayList<>();

    public void addArea(Area area) {
        this.areaList.add(area);
    }
}
