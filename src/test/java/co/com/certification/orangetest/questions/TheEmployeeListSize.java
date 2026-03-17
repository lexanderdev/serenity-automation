package co.com.certification.orangetest.questions;

import co.com.certification.orangetest.userinterface.pim.EmployeeListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheEmployeeListSize implements Question<Integer> {

    public static TheEmployeeListSize displayed() {
        return new TheEmployeeListSize();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return EmployeeListPage.TABLE_ROWS.resolveAllFor(actor).size();
    }
}
