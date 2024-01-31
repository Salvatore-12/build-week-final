package Team3.buildweekfinal.payloads;

import Team3.buildweekfinal.entities.Client;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BillDTO(
        @NotNull(message = "inserire una data")
        LocalDate date,
        @NotNull(message = "inserire un importo totale")
        double total,
        @NotNull(message = "inserire il numero della fattura")
        int billNumber,
        @NotNull(message = "inserire il cliente abbinato")
        Client client)
{
}
