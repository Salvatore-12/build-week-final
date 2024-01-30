package Team3.buildweekfinal.beansConfiguration;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailGunSender {
    private String mailGunKey;
    private String mailGunDomain;
    private String myEmail;
    public MailGunSender(@Value("${mailgun.apikey}") String mailGunKey,
                         @Value("${mailgun.domainname}") String mailGunDomain,
                         @Value("${myEmail}") String myEmail) {
        this.myEmail = myEmail;
        this.mailGunKey = mailGunKey;
        this.mailGunDomain = mailGunDomain;
    }

    public void sendMail(String recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.mailGunDomain + "/messages")
                .basicAuth("api", this.mailGunKey)
                .queryString("from", "Company Epicode" + this.myEmail)
                .queryString("to", recipient)
                .queryString("subject", "ANCORA DA INSERIRE")
                .queryString("text", "MANCANTE")
                .asJson();
    }
    //mailGunSender.sendMail(user.getEmail()); da inserire nel controller
}
