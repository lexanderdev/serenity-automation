package co.com.certification.orangetest.tasks.pim;

import co.com.certification.orangetest.userinterface.pim.EmployeeListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class SearchEmployee implements Task {

    private final String employeeName;

    public SearchEmployee(String employeeName) {
        this.employeeName = employeeName;
    }

    public static SearchEmployee byName(String employeeName) {
        return Tasks.instrumented(SearchEmployee.class, employeeName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(employeeName).into(EmployeeListPage.INPUT_SEARCH_NAME),
                Click.on(EmployeeListPage.BUTTON_SEARCH)
        );
    }
}
