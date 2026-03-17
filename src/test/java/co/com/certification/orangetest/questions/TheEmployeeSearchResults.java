package co.com.certification.orangetest.questions;

import co.com.certification.orangetest.userinterface.pim.EmployeeListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheEmployeeSearchResults implements Question<Boolean> {

    private final String employeeName;

    public TheEmployeeSearchResults(String employeeName) {
        this.employeeName = employeeName;
    }

    public static TheEmployeeSearchResults containing(String employeeName) {
        return new TheEmployeeSearchResults(employeeName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Text.of(EmployeeListPage.TABLE_BODY)
                   .answeredBy(actor)
                   .toLowerCase()
                   .contains(employeeName.toLowerCase());
    }
}
