package Team3.buildweekfinal.payloads;


import jakarta.validation.constraints.NotEmpty;

public record UpdateExistingUserDTO(
        @NotEmpty
        String name,
        @NotEmpty
        String surname,
        @NotEmpty
        String username,
        @NotEmpty
        String email,
        @NotEmpty
        String password) {
}
