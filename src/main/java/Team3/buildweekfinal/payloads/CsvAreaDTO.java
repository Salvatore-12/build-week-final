package Team3.buildweekfinal.payloads;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsvAreaDTO {
    @CsvBindByPosition(position = 0)
    private String codiceProvincia;
    @CsvBindByPosition(position = 1)
    private String progressivoComune;
    @CsvBindByPosition(position = 2)
    private String denominazioneInItaliano;
    @CsvBindByPosition(position = 3)
    private String provinceName;
}
