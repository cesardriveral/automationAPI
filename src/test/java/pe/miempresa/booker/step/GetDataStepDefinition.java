package pe.miempresa.booker.step;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;
import pe.miempresa.booker.question.ResponseCodeResponse;
import pe.miempresa.booker.task.RequestGetAll;
import pe.miempresa.booker.task.RequestGetByDate;
import pe.miempresa.booker.task.RequestGetById;
import pe.miempresa.booker.task.RequestGetByName;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetDataStepDefinition {
    @Steps
    CreateData createData;

    @Cuando("se consulta un registro usando un id")
    public void seConsultaUnRegistroUsandoUnId() {
        createData.enviaLosDatosNecesariosParaCrearUnRegistro("Chicho", "Bruce", "100", "2023-10-10", "2023-10-16", "vacaciones");
        theActorInTheSpotlight().attemptsTo(RequestGetById.forThis());
    }

    @Entonces("Se visualiza el registro")
    public void seVisualizaElRegistro() {
        theActorInTheSpotlight().should(seeThat("El código de respuesta es: ", ResponseCodeResponse.getResponseCode(), equalTo("200")));
    }

    @Cuando("se consulta un registro usando nombres")
    public void seConsultaUnRegistroUsandoNombres() {
        createData.enviaLosDatosNecesariosParaCrearUnRegistro("Chicho", "Bruce", "100", "2023-10-10", "2023-10-16", "vacaciones");
        theActorInTheSpotlight().attemptsTo(RequestGetByName.forThis("Chicho", "Bruce"));
    }

    @Cuando("se consulta un registro usando fechas")
    public void seConsultaUnRegistroUsandoFechas() {
        createData.enviaLosDatosNecesariosParaCrearUnRegistro("Chicho", "Bruce", "100", "2023-10-10", "2023-10-16", "vacaciones");
        theActorInTheSpotlight().attemptsTo(RequestGetByDate.forThis("2023-10-10", "2023-10-16"));
    }

    @Cuando("se consulta todos los registros")
    public void seConsultaTodosLosRegistros() {
        createData.enviaLosDatosNecesariosParaCrearUnRegistro("Chicho", "Bruce", "100", "2023-10-10", "2023-10-16", "vacaciones");
        theActorInTheSpotlight().attemptsTo(RequestGetAll.forThis());
    }
}
