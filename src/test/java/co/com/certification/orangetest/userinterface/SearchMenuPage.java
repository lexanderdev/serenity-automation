package co.com.certification.orangetest.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SearchMenuPage extends PageObject {

    public static final Target BUTTON_GO = Target.the("Da click para buscar MY info")
            .located(By.xpath("//span[text()='My Info']"));

    public static final Target NAME_LABEL = Target.the("Da click para buscar details")
            .located(By.xpath("//a[contains(@class, 'orangehrm-tabs-item') and text()='Contact Details']"));

    public static final Target NAME_DETAIL = Target.the("Toma la etiqueta de details")
            .located(By.xpath("//h6[text()='Contact Details']"));

}
