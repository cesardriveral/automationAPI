package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class RequestGetByName implements Task {
    private final String firstName;
    private final String lastName;

    public RequestGetByName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Performable forThis(String firstName, String lastName) {
        return Tasks.instrumented(RequestGetByName.class, firstName, lastName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/booking?firstname={FNAME}&lastname={LNAME}").with(
                request -> request
                        .contentType(ContentType.JSON)
                        .pathParams("FNAME", firstName)
                        .pathParams("LNAME", lastName)
        ));
    }
}
