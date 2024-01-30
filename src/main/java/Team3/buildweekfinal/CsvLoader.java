package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.Area;
import Team3.buildweekfinal.entities.Province;
import Team3.buildweekfinal.payloads.CsvAreaDTO;
import Team3.buildweekfinal.payloads.CsvProvinceDTO;
import Team3.buildweekfinal.repositories.AreasDAO;
import Team3.buildweekfinal.repositories.ProvincesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

@Component
public class CsvLoader implements CommandLineRunner {
    @Autowired
    private ProvincesDAO provincesDAO;

    @Autowired
    private AreasDAO areasDAO;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean errors = false;
        do{
            System.out.println("Vuoi caricare i file 'province-italiane.csv' e 'comuni-italiani.csv'? (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y" -> {
                    saveAllAreas();
                    saveAllProvinces();
                    errors = false;
                }
                case "n" -> errors = false;
                default -> {
                    System.out.println("Input non valido. Riprova.");
                    errors = true;
                }
            }
        }while(errors);
    }

    public void saveAllProvinces() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/main/resources/province-italiane.csv");
        List<CsvProvinceDTO> csvProvinceDTOS = new CsvToBeanBuilder<CsvProvinceDTO>(fileReader)
                .withType(CsvProvinceDTO.class)
                .withSeparator(';')
                .build()
                .parse();

        csvProvinceDTOS.forEach(csvProvinceDTO -> {
            Province province = new Province();
            province.setProvince(csvProvinceDTO.getSigla());
            province.setProvinceName(csvProvinceDTO.getProvincia());
            province.setRegion(csvProvinceDTO.getRegione());

            provincesDAO.save(province);
        });

    }
    public void saveAllAreas() throws FileNotFoundException{
        FileReader fileReader = new FileReader("src/main/resources/comuni-italiani.csv");
        List<CsvAreaDTO> csvAreaDTOS = new CsvToBeanBuilder<CsvAreaDTO>(fileReader)
                .withType(CsvAreaDTO.class)
                .withSeparator(';')
                .build()
                .parse();

        csvAreaDTOS.forEach(csvAreaDTO -> {
            Area area = new Area();
            area.setArea(csvAreaDTO.getDenominazioneInItaliano());
            area.setProvinceName(csvAreaDTO.getProvince());
            area.setProvinceCode(csvAreaDTO.getCodiceProvincia());
            area.setProvinceProgressive(csvAreaDTO.getProgressivoComune());

            areasDAO.save(area);
        });
    }
}
