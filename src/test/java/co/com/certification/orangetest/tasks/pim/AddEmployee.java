package co.com.certification.orangetest.tasks.pim;

import co.com.certification.orangetest.userinterface.pim.AddEmployeePage;
import co.com.certification.orangetest.userinterface.pim.EmployeeListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddEmployee implements Task {

    private final String firstName;
    private final String lastName;

    public AddEmployee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static AddEmployee withName(String firstName, String lastName) {
        return Tasks.instrumented(AddEmployee.class, firstName, lastName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(EmployeeListPage.BUTTON_ADD),
                WaitUntil.the(AddEmployeePage.INPUT_FIRST_NAME, isVisible()).forNoMoreThan(10).seconds(),
                Clear.field(AddEmployeePage.INPUT_FIRST_NAME),
                Enter.theValue(firstName).into(AddEmployeePage.INPUT_FIRST_NAME),
                Enter.theValue(lastName).into(AddEmployeePage.INPUT_LAST_NAME),
                Click.on(AddEmployeePage.BUTTON_SAVE),
                WaitUntil.the(AddEmployeePage.PERSONAL_DETAILS_HEADER, isVisible()).forNoMoreThan(20).seconds()
        );
    }
}
