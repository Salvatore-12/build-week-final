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
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
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
        do {
            System.out.println("Vuoi caricare i file 'province-italiane.csv' e 'comuni-italiani.csv'? (y/n)");
            String choice = scanner.nextLine();
            switch (choice.toLowerCase()) {
                case "y" -> {
                    saveAllProvinces();
                    saveAllAreas();
                    errors = false;
                }
                case "n" -> errors = false;
                default -> {
                    System.out.println("Input non valido. Riprova.");
                    errors = true;
                }
            }
        } while (errors);
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
            province.setInitials(csvProvinceDTO.getSigla());
            province.setProvinceName(csvProvinceDTO.getProvincia());
            province.setRegion(csvProvinceDTO.getRegione());

            provincesDAO.save(province);
        });
    }

    public void saveAllAreas() throws Exception {
        FileReader fileReader = new FileReader("src/main/resources/comuni-italiani.csv");
        List<CsvAreaDTO> csvAreaDTOS = new CsvToBeanBuilder<CsvAreaDTO>(fileReader)
                .withType(CsvAreaDTO.class)
                .withSeparator(';')
                .build()
                .parse();

        int i = 0;

        for (CsvAreaDTO csvAreaDTO : csvAreaDTOS) {

            Area area = new Area();
            area.setItalianName(csvAreaDTO.getDenominazioneInItaliano());
            area.setProgressiveArea(csvAreaDTO.getProgressivoComune());
            area.setProvinceCode(csvAreaDTO.getCodiceProvincia());

            if (!(csvAreaDTO.getProvinceName() == null)) {
                String[] nameSplit = csvAreaDTO.getProvinceName().split(" ");
                String newName = csvAreaDTO.getProvinceName();

                if (nameSplit.length > 0) {
                    newName = String.join("-", nameSplit);
                }
                Province province = provincesDAO.findByProvinceName(newName);
                //questo if è solo per debug
                if (province == null) {
                    i++;
                } else {
                    province.addArea(area);
                    area.setProvince(province);
                    areasDAO.save(area);
                    provincesDAO.save(province);
                }
            }
        }
        System.out.println(i);
    }
}
