package Team3.buildweekfinal.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UsersDTO(
        @NotEmpty(message="iserisci il tuo nome")
        String name,
        @NotEmpty(message = "inserisci il tuo surname")
        String surname,
        @NotEmpty(message = "inserisci il tuo username")
        String username,
        @Email(message = "campo email obbligatorio")
        String email,
        @NotEmpty(message = "campo password obbligatorio")
        @Size(min=4,max=30)
        String password) {
}
