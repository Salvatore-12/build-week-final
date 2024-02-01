package Team3.buildweekfinal.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ClientsDTO(
        @Email(message = "campo email obbligatorio")
        String email,
        @NotNull(message = "inserire la data di inserimento")
        LocalDate insertDate,
        @NotNull(message = "inserire la data del ultimo controllo")
        LocalDate lastCall,
        @NotNull(message="inserisci il fatturato annuale")
        double annualTurnOver,
        @NotEmpty(message = "inserisci la tua pec")
        String pec,
        @NotNull(message = "metti il numero di telefono")
        Long number,
        @Pattern(regexp = "SPA|SRL|PA|SAS", message = "il tipo del  client non è valido")
        String ctype) {
}
