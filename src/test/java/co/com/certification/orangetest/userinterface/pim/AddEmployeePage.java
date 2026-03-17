package co.com.certification.orangetest.userinterface.pim;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class AddEmployeePage extends PageObject {

    // OrangeHRM pre-fills first name with a generated value — clear before entering
    public static final Target INPUT_FIRST_NAME = Target.the("first name input field")
            .located(By.cssSelector("input[name='firstName']"));

    public static final Target INPUT_LAST_NAME = Target.the("last name input field")
            .located(By.cssSelector("input[name='lastName']"));

    public static final Target BUTTON_SAVE = Target.the("Save button on Add Employee form")
            .located(By.xpath("//button[normalize-space()='Save']"));

    public static final Target PERSONAL_DETAILS_HEADER = Target.the("Personal Details section header")
            .located(By.xpath("//h6[normalize-space()='Personal Details']"));
}
