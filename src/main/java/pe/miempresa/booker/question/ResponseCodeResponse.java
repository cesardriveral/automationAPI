package pe.miempresa.booker.question;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class ResponseCodeResponse {
    public static Question<String> getResponseCode() {
        return actor -> String.valueOf(SerenityRest.lastResponse().getStatusCode());
    }
}
