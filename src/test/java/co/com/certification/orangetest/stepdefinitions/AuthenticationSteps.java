package co.com.certification.orangetest.stepdefinitions;

import co.com.certification.orangetest.tasks.Login;
import co.com.certification.orangetest.tasks.OpenUp;
import co.com.certification.orangetest.userinterface.DashboardPage;
import co.com.certification.orangetest.userinterface.OrangeLoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.Text;

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
        OnStage.theActorCalled("Admin").wasAbleTo(
                OpenUp.thePage(),
                Login.onThePage("Admin", "admin123")
        );
    }

    /**
     * Paso de prueba para escenarios donde el login mismo es lo que se valida.
     * Recibe credenciales como parámetros para cubrir casos válidos e inválidos
     * sin duplicar el paso.
     * Usado en: login.feature (smoke + scenario outline de credenciales inválidas)
     */
    @Given("el usuario intenta iniciar sesión con usuario {string} y contraseña {string}")
    public void elUsuarioIntentaIniciarSesion(String usuario, String contrasena) {
        OnStage.theActorCalled("Usuario").wasAbleTo(
                OpenUp.thePage(),
                Login.onThePage(usuario, contrasena)
        );
    }

    @Then("el usuario visualiza el Dashboard de OrangeHRM")
    public void elUsuarioVisualizaElDashboard() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(Text.of(DashboardPage.DASHBOARD_TITLE), equalTo("Dashboard"))
        );
    }

    @Then("el sistema muestra el mensaje de error {string}")
    public void elSistemaMuestraElMensajeDeError(String mensajeEsperado) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(Text.of(OrangeLoginPage.ALERT_MESSAGE), containsString(mensajeEsperado))
        );
    }
}
