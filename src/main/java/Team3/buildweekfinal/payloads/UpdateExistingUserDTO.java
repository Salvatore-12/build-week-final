package Team3.buildweekfinal.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateExistingUserDTO(

        String name,

        String surname,

        String username,

        String email,

        String password) {
}
