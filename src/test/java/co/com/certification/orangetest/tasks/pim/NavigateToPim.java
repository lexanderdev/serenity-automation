package co.com.certification.orangetest.tasks.pim;

import co.com.certification.orangetest.userinterface.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class NavigateToPim implements Task {

    public static NavigateToPim toPimModule() {
        return Tasks.instrumented(NavigateToPim.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DashboardPage.MENU_PIM)
        );
    }
}
