package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.rest.interactions.Post;
import pe.miempresa.booker.util.Utils;

public class RequestCreate implements Task {
    private final String firstName;
    private final String lastName;
    private final String amount;
    private final String dateIn;
    private final String dateOut;
    private final String info;

    public RequestCreate(String firstName, String lastName, String amount, String dateIn, String dateOut,
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
        return Tasks.instrumented(RequestCreate.class, firstName, lastName, amount, dateIn, dateOut, info);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/booking").with(
                request -> request
                        .contentType(ContentType.JSON)
                        .body(Utils.getTemplate("/templates/createData.json")
                                .replace("{{firstname}}", firstName.replace("\"", ""))
                                .replace("{{lastname}}", lastName.replace("\"", ""))
                                .replace("\"{{amount}}\"", amount.replace("\"", ""))
                                .replace("{{dateIn}}", dateIn.replace("\"", ""))
                                .replace("{{dateOut}}", dateOut.replace("\"", ""))
                                .replace("{{info}}", info.replace("\"", "")))
        ));
        if (SerenityRest.lastResponse().getStatusCode() == 200) {
            actor.attemptsTo(RememberThat.theValueOf("TRANSACTION_ID").is(SerenityRest.lastResponse().path("bookingid").toString()));
        }
    }
}
