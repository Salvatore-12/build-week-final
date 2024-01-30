package Team3.buildweekfinal.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO(@NotEmpty(message = "Inserisci l'email per accedere")
                           String email,
                           @NotEmpty(message = "Inserisci la password per accedere")
                           String password) {
}
