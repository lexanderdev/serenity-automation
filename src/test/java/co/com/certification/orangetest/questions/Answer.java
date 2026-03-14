package co.com.certification.orangetest.questions;

import co.com.certification.orangetest.userinterface.SearchMenuPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class Answer implements Question<Boolean> {

    private final String  question;
    public Answer(String question){
        this.question = question;
    }

    public static Answer toThe(String question) {

        return new Answer(question);
    }



    @Override
    public Boolean answeredBy(Actor actor) {
        String nameLabel = Text.of(SearchMenuPage.NAME_DETAIL).answeredBy(actor);
        return java.util.Objects.equals(question, nameLabel);
    }
}
