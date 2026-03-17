package co.com.certification.orangetest.tasks;

import co.com.certification.orangetest.userinterface.OrangeLoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Login implements Task {

    private final String strUser;
    private final String strPassword;
    public Login(String strUser, String strPassword) {
        this.strUser = strUser;
        this.strPassword = strPassword;
    }

    public static Login onThePage(String strUser, String strPassword){
        return Tasks.instrumented(Login.class,strUser,strPassword);
    }

    public <T extends Actor> void performAs(T actor){
          actor.attemptsTo(
                Enter.theValue(strUser).into(OrangeLoginPage.INPUT_USER),
                Enter.theValue(strPassword).into(OrangeLoginPage.INPUT_PASSWORD),
                Click.on(OrangeLoginPage.SUBMIT_BUTTON)
        );
    }
}