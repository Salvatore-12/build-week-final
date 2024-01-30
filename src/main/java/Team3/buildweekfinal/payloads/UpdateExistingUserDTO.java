package Team3.buildweekfinal.payloads;


public record UpdateExistingUserDTO(String name,
                                    String surname,
                                    String username,
                                    String email,
                                    String password)  {
}
