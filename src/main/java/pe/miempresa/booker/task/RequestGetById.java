package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class RequestGetById implements Task {
    public static Performable forThis() {
        return Tasks.instrumented(RequestGetById.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/booking/{ID}").with(
                request -> request
                        .contentType(ContentType.JSON)
                        .header("Accept", "application/json")
                        .pathParams("ID", actor.recall("TRANSACTION_ID"))
        ));
    }
}
