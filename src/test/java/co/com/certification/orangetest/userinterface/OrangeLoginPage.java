package co.com.certification.orangetest.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class OrangeLoginPage extends PageObject {
    public static final Target INPUT_USER = Target.the("where do we write the user")
            .located(By.cssSelector("input[placeholder='Username']"));
    public static final Target INPUT_PASSWORD = Target.the("where do we write the password")
            .located(By.cssSelector("input[placeholder='Password']"));
    public static final Target SUBMIT_BUTTON = Target.the("button that shows us the academy page")
            .located(By.cssSelector("button[type='submit']"));


}