package pe.miempresa.booker.step;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import pe.miempresa.booker.question.ResponseCodeResponse;
import pe.miempresa.booker.task.RequestCreate;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateData {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("^(.*) que quiere crear un registro$")
    public void unUsuarioQueQuiereCrearUnRegistro(String actor) {
        theActorCalled(actor).whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }

    @Cuando("^envia los datos necesarios para crear un registro con (.*) (.*) (.*) (.*) (.*) y (.*)$")
    public void enviaLosDatosNecesariosParaCrearUnRegistro(String firstName, String lastName, String amount, String dateIn, String dateOut,
                                                           String info) {
        theActorInTheSpotlight().attemptsTo(RequestCreate.forThis(firstName, lastName, amount, dateIn, dateOut, info));
    }

    @Entonces("^Se crea un registro correctamente$")
    public void seCreaUnRegistroCorrectamente() {
        theActorInTheSpotlight().should(seeThat("El código de respuesta es: ", ResponseCodeResponse.getResponseCode(), equalTo("200")));
    }
}
