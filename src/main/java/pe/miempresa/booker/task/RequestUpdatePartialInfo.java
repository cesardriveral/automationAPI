package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Post;
import pe.miempresa.booker.util.Utils;

public class RequestUpdatePartialInfo implements Task {
    private final String firstName;
    private final String lastName;

    public RequestUpdatePartialInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Performable forThis(String firstName, String lastName) {
        return Tasks.instrumented(RequestUpdatePartialInfo.class, firstName, lastName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Patch.to("/booking/" + actor.recall("TRANSACTION_ID")).with(
                request -> request
                        .contentType(ContentType.JSON)
                        .header("Accept", "application/json")
                        .header("Cookie", "token=" + actor.recall("TOKEN"))
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .body(Utils.getTemplate("/templates/updatePartialData.json")
                                .replace("{{firstname}}", firstName.replace("\"", ""))
                                .replace("{{lastname}}", lastName.replace("\"", "")))
        ));
    }
}
