package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class RequestDeleteById implements Task {
    public static Performable forThis() {
        return Tasks.instrumented(RequestDeleteById.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Delete.from("/booking/{ID}").with(
                request -> request
                        .contentType(ContentType.JSON)
                        .header("Cookie", "token=" + actor.recall("TOKEN"))
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .pathParams("ID", actor.recall("TRANSACTION_ID"))
        ));
    }
}
