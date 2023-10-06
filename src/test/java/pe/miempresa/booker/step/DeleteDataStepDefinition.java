package pe.miempresa.booker.step;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;
import pe.miempresa.booker.question.ResponseCodeResponse;
import pe.miempresa.booker.task.RequestDeleteById;
import pe.miempresa.booker.task.RequestToken;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteDataStepDefinition {
    @Steps
    CreateData createData;

    @Cuando("se trata de eliminar un registro")
    public void seTrataDeEliminarUnRegistro() {
        theActorInTheSpotlight().attemptsTo(RequestToken.forThis());
        createData.enviaLosDatosNecesariosParaCrearUnRegistro("Chicho", "Bruce", "100", "2023-10-10", "2023-10-16", "vacaciones");
        theActorInTheSpotlight().attemptsTo(RequestDeleteById.forThis());
    }

    @Entonces("Se elimina el registro exitosamente")
    public void seEliminaElRegistroExitosamente() {
        theActorInTheSpotlight().should(seeThat("El código de respuesta es: ", ResponseCodeResponse.getResponseCode(), equalTo("201")));
    }
}
