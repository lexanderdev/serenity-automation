package co.com.certification.orangetest.userinterface.pim;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class EmployeeListPage extends PageObject {

    public static final Target BUTTON_ADD = Target.the("Add Employee button")
            .located(By.xpath("//button[normalize-space()='Add']"));

    public static final Target BUTTON_SEARCH = Target.the("Search button on employee list")
            .located(By.xpath("//button[normalize-space()='Search']"));

    // Employee Name autocomplete input (first match in the search form)
    public static final Target INPUT_SEARCH_NAME = Target.the("employee name autocomplete search input")
            .located(By.xpath("(//div[contains(@class,'oxd-autocomplete-text-input')]//input)[1]"));

    public static final Target TABLE_ROWS = Target.the("employee table rows")
            .located(By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));

    // Cuerpo completo de la tabla — permite verificar si cualquier nombre aparece en los resultados
    public static final Target TABLE_BODY = Target.the("employee table body")
            .located(By.xpath("//div[@class='oxd-table-body']"));
}
