package co.com.certification.orangetest.userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardPage extends PageObject {

    public static final Target DASHBOARD_TITLE = Target.the("dashboard heading title")
            .located(By.xpath("//h6[text()='Dashboard']"));

    public static final Target MENU_PIM = Target.the("PIM menu item in sidebar")
            .located(By.xpath("//span[normalize-space()='PIM']"));
}
