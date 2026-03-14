package co.com.certification.orangetest.tasks;

import co.com.certification.orangetest.userinterface.SearchMenuPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class Search implements Task {



    private final String strSearch;

    public Search(String strSearch) {
        this.strSearch = strSearch;

    }


    public static Search the(){
        return Tasks.instrumented(Search.class,"");
    }


    @Override
    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(
                //Click.on(SearchCoursePage.INPUT_SEARCH),
               // Enter.theValue(strSearch).into(SearchCoursePage.INPUT_SEARCH),
                Click.on(SearchMenuPage.BUTTON_GO),
                Click.on(SearchMenuPage.NAME_LABEL)
        );
    }
}
