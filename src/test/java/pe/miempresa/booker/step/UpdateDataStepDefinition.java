package pe.miempresa.booker.step;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;
import pe.miempresa.booker.question.ResponseCodeResponse;
import pe.miempresa.booker.task.RequestDeleteById;
import pe.miempresa.booker.task.RequestToken;
import pe.miempresa.booker.task.RequestUpdate;
import pe.miempresa.booker.task.RequestUpdatePartialInfo;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdateDataStepDefinition {
    @Steps
    CreateData createData;

    @Cuando("^se requiere actualizar un registro con (.*), (.*), (.*), (.*), (.*) y (.*)$")
    public void seRequiereActualizarUnRegistro(String firstName, String lastName, String amount, String dateIn, String dateOut,
                                               String info) {
        theActorInTheSpotlight().attemptsTo(RequestToken.forThis());
        createData.enviaLosDatosNecesariosParaCrearUnRegistro("Chicho", "Bruce", "100", "2023-10-10", "2023-10-16", "vacaciones");
        theActorInTheSpotlight().attemptsTo(RequestUpdate.forThis(firstName, lastName, amount, dateIn, dateOut, info));
    }

    @Entonces("actualiza correctamente")
    public void actualizaCorrectamente() {
        theActorInTheSpotlight().should(seeThat("El código de respuesta es: ", ResponseCodeResponse.getResponseCode(), equalTo("200")));
    }

    @Cuando("^envia algunos datos para actualizar como (.*) y (.*)$")
    public void enviaAlgunosDatosParaActualizarComoY(String firstName, String lastName) {
        theActorInTheSpotlight().attemptsTo(RequestToken.forThis());
        createData.enviaLosDatosNecesariosParaCrearUnRegistro("Chicho", "Bruce", "100", "2023-10-10", "2023-10-16", "vacaciones");
        theActorInTheSpotlight().attemptsTo(RequestUpdatePartialInfo.forThis(firstName, lastName));
    }
}
