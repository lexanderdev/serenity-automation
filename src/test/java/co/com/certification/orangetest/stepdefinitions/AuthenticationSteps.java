package co.com.certification.orangetest.stepdefinitions;

import co.com.certification.orangetest.config.FirebaseClient;
import co.com.certification.orangetest.model.LoginData;
import co.com.certification.orangetest.questions.TheAlertMessage;
import co.com.certification.orangetest.questions.TheDashboard;
import co.com.certification.orangetest.tasks.Login;
import co.com.certification.orangetest.tasks.OpenUp;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationSteps {

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    /**
     * Precondición reutilizable para features que requieren sesión activa (ej. PIM).
     * Las credenciales están hardcodeadas porque este paso NO es lo que se prueba,
     * es solo el estado inicial necesario para ejecutar el escenario.
     * Usado en: Background de employee_management.feature
     */
    @Given("el usuario Admin está autenticado en OrangeHRM")
    public void elUsuarioAdminEstaAutenticado() {
        LoginData credentials = FirebaseClient.getInstance().getValidLoginCredentials();
        OnStage.theActorCalled("Admin").wasAbleTo(
                OpenUp.thePage(),
                Login.onThePage(credentials.getUsername(), credentials.getPassword())
        );
    }

    @Given("el usuario intenta iniciar sesión con credenciales válidas desde Firebase")
    public void elUsuarioIntentaIniciarSesionConCredencialesValidasDesdeFirebase() {
        LoginData credentials = FirebaseClient.getInstance().getValidLoginCredentials();
        OnStage.theActorCalled("Usuario").wasAbleTo(
                OpenUp.thePage(),
                Login.onThePage(credentials.getUsername(), credentials.getPassword())
        );
    }

    @Then("el usuario visualiza el Dashboard de OrangeHRM")
    public void elUsuarioVisualizaElDashboard() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(TheDashboard.title(), equalTo("Dashboard"))
        );
    }

    private LoginData invalidCredential;

    @Given("el usuario intenta iniciar sesión con credenciales inválidas desde Firebase")
    public void elUsuarioIntentaIniciarSesionConCredencialesInvalidasDesdeFirebase() {
        List<LoginData> invalidCredentials = FirebaseClient.getInstance().getInvalidLoginCredentials();
        invalidCredential = invalidCredentials.get(0);
        OnStage.theActorCalled("Usuario").wasAbleTo(
                OpenUp.thePage(),
                Login.onThePage(invalidCredential.getUsername(), invalidCredential.getPassword())
        );
    }

    @Then("el sistema muestra el mensaje de error correspondiente")
    public void elSistemaMuestraElMensajeDeErrorCorrespondiente() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(TheAlertMessage.displayed(), containsString(invalidCredential.getMessage()))
        );
    }
}
