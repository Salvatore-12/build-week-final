package Team3.buildweekfinal.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddressDTO(
        @NotEmpty(message = "l'indirizzo non può essere vuoto")
        String address,
        @NotNull(message ="devi inserire il numero civico")
        Integer cv,
        @NotEmpty(message="devi inserire la città")
        String city,
        @NotEmpty(message="devi inserire il cap")
        String cap) {
}
