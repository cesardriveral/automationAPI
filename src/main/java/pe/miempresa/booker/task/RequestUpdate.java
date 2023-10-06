package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import pe.miempresa.booker.util.Utils;

public class RequestUpdate implements Task {
    private final String firstName;
    private final String lastName;
    private final String info;
    private final String amount;
    private final String dateIn;
    private final String dateOut;

    public RequestUpdate(String firstName, String lastName, String amount, String dateIn, String dateOut,
                         String info) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.info = info;
    }

    public static Performable forThis(String firstName, String lastName, String amount, String dateIn, String dateOut,
                                      String info) {
        return Tasks.instrumented(RequestUpdate.class, firstName, lastName, amount, dateIn, dateOut, info);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to("/booking/" + actor.recall("TRANSACTION_ID")).with(
                request -> request
                        .contentType(ContentType.JSON)
                        .header("Accept", "application/json")
                        .header("Cookie", "token=" + actor.recall("TOKEN"))
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .body(Utils.getTemplate("/templates/updateData.json")
                                .replace("{{firstname}}", firstName.replace("\"", ""))
                                .replace("{{lastname}}", lastName.replace("\"", ""))
                                .replace("\"{{amount}}\"", amount.replace("\"", ""))
                                .replace("{{dateIn}}", dateIn.replace("\"", ""))
                                .replace("{{dateOut}}", dateOut.replace("\"", ""))
                                .replace("{{info}}", info.replace("\"", "")))
        ));
    }
}
