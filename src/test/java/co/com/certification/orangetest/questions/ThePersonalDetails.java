package co.com.certification.orangetest.questions;

import co.com.certification.orangetest.userinterface.pim.AddEmployeePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ThePersonalDetails implements Question<String> {

    public static ThePersonalDetails header() {
        return new ThePersonalDetails();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(AddEmployeePage.PERSONAL_DETAILS_HEADER).answeredBy(actor);
    }
}
