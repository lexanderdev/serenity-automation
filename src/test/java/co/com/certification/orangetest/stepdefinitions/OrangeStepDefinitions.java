package co.com.certification.orangetest.stepdefinitions;

import co.com.certification.orangetest.model.OrangeData;

import co.com.certification.orangetest.questions.Answer;
import co.com.certification.orangetest.tasks.Login;
import co.com.certification.orangetest.tasks.OpenUp;
import co.com.certification.orangetest.tasks.Search;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;


public class OrangeStepDefinitions {

    @Before
    public void setStage (){ OnStage.setTheStage(new OnlineCast());}


    @Given("^El usuario 'Admin' está autenticado en la plataforma Orange$")
    public void usuarioAdminAutenticadoEnOrange(List<OrangeData> orangeData) {
        if (orangeData == null || orangeData.isEmpty()) {
            throw new IllegalArgumentException("No haya datos de prueba para el paso de inicio de sesión");
        }
        OrangeData data = orangeData.getFirst();
        OnStage.theActorCalled("Admin").wasAbleTo(OpenUp.thePage(), Login.onThePage(
                data.getStrUser(), data.getStrPassword()));
    }

    @When("^El usuario selecciona la opción 'My Info' en el menú principal$")
    public void usuarioSeleccionaMyInfoEnMenu() {
        OnStage.theActorInTheSpotlight().attemptsTo(Search.the());
    }

    @Then("^El usuario visualiza la sección 'Contact Details' en los datos personales$")
    public void usuarioVisualizaContactDetails(List<OrangeData> orangeData) {
        if (orangeData == null || orangeData.isEmpty()) {
            throw new IllegalArgumentException("No test data provided for assertion step");
        }
        OrangeData data = orangeData.getFirst();
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(Answer.toThe(data.getStrSearch())));
    }

}
