package Team3.buildweekfinal;

import Team3.buildweekfinal.entities.Area;
import Team3.buildweekfinal.entities.Province;
import Team3.buildweekfinal.payloads.CsvAreaDTO;
import Team3.buildweekfinal.payloads.CsvProvinceDTO;
import Team3.buildweekfinal.repositories.AreasDAO;
import Team3.buildweekfinal.repositories.ProvincesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

@Component
@Order(1)
public class CsvLoader implements CommandLineRunner {
    @Autowired
    private ProvincesDAO provincesDAO;

    @Autowired
    private AreasDAO areasDAO;

    private static final Map<String, String> exceptionsMap = createExceptionsMap();

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
                .withSkipLines(1)
                .build()
                .parse();

        csvProvinceDTOS.forEach(csvProvinceDTO -> {
            if (csvProvinceDTO.getSigla().length() > 2) System.out.println(csvProvinceDTO.getSigla());
            Province province = new Province();
            if (csvProvinceDTO.getSigla().equals("Roma")) {
                province.setInitials("RM");
            } else {
                province.setInitials(csvProvinceDTO.getSigla());
            }
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

            if (csvAreaDTO.getProvinceName() != null) {
                String normalizedName = normalizeProvinceName(csvAreaDTO.getProvinceName());

                Province province = provincesDAO.findByProvinceName(normalizedName);

                // questo if è solo per debug
                if (province == null) {
                    i++;
                    System.out.println(normalizedName);
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

    private static Map<String, String> createExceptionsMap() {
        //CHIAVE: nome nel file comuni-italiani
        //VALORE: corrispettivo nel file province-italiane
        Map<String, String> map = new HashMap<>();
        map.put("valle d'aosta/vallée d'aoste", "Aosta");
        map.put("sud sardegna", "Cagliari");
        map.put("monza e della brianza", "Monza-Brianza");
        map.put("verbano-cusio-ossola", "Verbania");
        map.put("reggio nell'emilia", "Reggio-Emilia");
        map.put("forlì-cesena", "Forli-Cesena");
        map.put("pesaro e urbino", "Pesaro-Urbino");
        return map;
    }

    public static String normalizeProvinceName(String name) {
        // controlla prima nel mapping se c'è una chiave corrispondente al nome della provincia nel file comuni-italiani.csv
        String lowerCaseName = name.toLowerCase();
        if (exceptionsMap.containsKey(lowerCaseName)) {
            return exceptionsMap.get(lowerCaseName);
        }

        // se non era presente la chiave corrispondente, normalizza la stringa seguendo le regole del file province....csv
        String normalized = lowerCaseName.split("/")[0];
        normalized = normalized.replace(" ", "-");


        normalized = capitalizeWords(normalized);

        return normalized;
    }

    //riconverte il maiuscole le iniziali di ogni parola in una stringa
    private static String capitalizeWords(String input) {
        //array di char (ogni carattere della stringa è separato, cosi posso ciclarlo)
        char[] chars = input.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '-' || chars[i] == '\'') {
                found = false;
                //se trovo uno spazio, un '-' o uno '/', vuol dire che sta per iniziare una nuova parola,
                //quindi imposto 'found' su false, cosi che al prossimo ciclo imposti la lettera successiva in maiuscolo.
            }
        }
        return String.valueOf(chars);
    }
}
