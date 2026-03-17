package co.com.certification.orangetest.questions;

import co.com.certification.orangetest.userinterface.OrangeLoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheAlertMessage implements Question<String> {

    public static TheAlertMessage displayed() {
        return new TheAlertMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(OrangeLoginPage.ALERT_MESSAGE).answeredBy(actor);
    }
}
