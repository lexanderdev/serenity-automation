package co.com.certification.orangetest.stepdefinitions;

import co.com.certification.orangetest.config.FirebaseClient;
import co.com.certification.orangetest.model.EmployeeData;
import co.com.certification.orangetest.questions.TheEmployeeListSize;
import co.com.certification.orangetest.questions.TheEmployeeSearchResults;
import co.com.certification.orangetest.questions.ThePersonalDetails;
import co.com.certification.orangetest.tasks.pim.AddEmployee;
import co.com.certification.orangetest.tasks.pim.NavigateToPim;
import co.com.certification.orangetest.tasks.pim.SearchEmployee;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class PimSteps {

    private String searchedEmployeeName;

    @When("el usuario navega al módulo PIM")
    public void elUsuarioNavegaAlModuloPim() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateToPim.toPimModule());
    }

    @Then("el sistema muestra la lista de empleados")
    public void elSistemaMuestraLaListaDeEmpleados() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(TheEmployeeListSize.displayed(), greaterThan(0))
        );
    }

    @When("el usuario agrega un empleado con datos de Firebase")
    public void elUsuarioAgregaUnEmpleadoDesdeFirebase() {
        EmployeeData empleado = FirebaseClient.getInstance().getEmployeeAddData();
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddEmployee.withName(empleado.getFirstName(), empleado.getLastName())
        );
    }

    @Then("el sistema muestra los detalles del empleado recién creado")
    public void elSistemaMuestraLosDetallesDelEmpleado() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ThePersonalDetails.header(), containsString("Personal Details"))
        );
    }

    @When("el usuario busca el empleado registrado en Firebase")
    public void elUsuarioBuscaElEmpleadoDesdeFirebase() {
        searchedEmployeeName = FirebaseClient.getInstance().getEmployeeSearchName();
        OnStage.theActorInTheSpotlight().attemptsTo(SearchEmployee.byName(searchedEmployeeName));
    }

    @Then("el sistema muestra resultados para el empleado buscado")
    public void elSistemaMuestraResultadosParaElEmpleadoBuscado() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(TheEmployeeSearchResults.containing(searchedEmployeeName), is(true))
        );
    }
}
