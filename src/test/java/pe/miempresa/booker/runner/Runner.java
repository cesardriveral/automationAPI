package pe.miempresa.booker.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty"},
        stepNotifications = true,
        glue = "pe.miempresa.booker",
        tags = "@TEST01-01")

public class Runner {
}
