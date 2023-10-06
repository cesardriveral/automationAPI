package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.rest.interactions.Post;
import pe.miempresa.booker.util.Utils;

public class RequestToken implements Task {

    public static Performable forThis() {
        return Tasks.instrumented(RequestToken.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/auth").with(
                request -> request
                        .contentType(ContentType.JSON)
                        .body(Utils.getTemplate("/templates/token.json"))
        ));
        if (SerenityRest.lastResponse().getStatusCode() == 200) {
            actor.attemptsTo(RememberThat.theValueOf("TOKEN").is(SerenityRest.lastResponse().path("token").toString()));
        }
    }
}
