package Team3.buildweekfinal.payloads;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvProvinceDTO {
    @CsvBindByPosition(position = 0)
    private String sigla;
    @CsvBindByPosition(position = 1)
    private String provincia;
    @CsvBindByPosition(position = 2)
    private String regione;
}
