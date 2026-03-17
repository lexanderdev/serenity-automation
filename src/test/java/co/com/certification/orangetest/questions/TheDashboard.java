package co.com.certification.orangetest.questions;

import co.com.certification.orangetest.userinterface.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheDashboard implements Question<String> {

    public static TheDashboard title() {
        return new TheDashboard();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(DashboardPage.DASHBOARD_TITLE).answeredBy(actor);
    }
}
