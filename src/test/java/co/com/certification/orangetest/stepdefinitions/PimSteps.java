package co.com.certification.orangetest.stepdefinitions;

import co.com.certification.orangetest.model.EmployeeData;
import co.com.certification.orangetest.questions.TheEmployeeListSize;
import co.com.certification.orangetest.tasks.pim.AddEmployee;
import co.com.certification.orangetest.tasks.pim.NavigateToPim;
import co.com.certification.orangetest.tasks.pim.SearchEmployee;
import co.com.certification.orangetest.userinterface.pim.AddEmployeePage;
import co.com.certification.orangetest.userinterface.pim.EmployeeListPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Text;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

public class PimSteps {

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

    @When("el usuario agrega un empleado con los siguientes datos")
    public void elUsuarioAgregaUnEmpleado(List<EmployeeData> empleados) {
        EmployeeData empleado = empleados.get(0);
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddEmployee.withName(empleado.getFirstName(), empleado.getLastName())
        );
    }

    @Then("el sistema muestra los detalles del empleado recién creado")
    public void elSistemaMuestraLosDetallesDelEmpleado() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(Text.of(AddEmployeePage.PERSONAL_DETAILS_HEADER), containsString("Personal Details"))
        );
    }

    @When("el usuario busca el empleado {string}")
    public void elUsuarioBuscaElEmpleado(String nombre) {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchEmployee.byName(nombre));
    }

    @Then("el sistema muestra al menos un resultado con {string}")
    public void elSistemaMuestraAlMenosUnResultado(String nombre) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(Text.of(EmployeeListPage.EMPLOYEE_NAMES), containsString(nombre))
        );
    }
}
