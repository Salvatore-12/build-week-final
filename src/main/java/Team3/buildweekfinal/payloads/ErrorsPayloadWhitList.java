package Team3.buildweekfinal.payloads;

import java.util.List;

public record ErrorsPayloadWhitList(String message, String date, List<String> errorsList) {
}
