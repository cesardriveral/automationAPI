package pe.miempresa.booker.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class RequestGetByDate implements Task {
    private final String dateIn;
    private final String dateOut;

    public RequestGetByDate(String dateIn, String dateOut) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public static Performable forThis(String dateIn, String dateOut) {
        return Tasks.instrumented(RequestGetByDate.class, dateIn, dateOut);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/booking?checkin={IDATE}&checkout={EDATE}").with(
                request -> request
                        .contentType(ContentType.JSON)
                        .pathParams("IDATE", dateIn)
                        .pathParams("EDATE", dateOut)
        ));
    }
}
