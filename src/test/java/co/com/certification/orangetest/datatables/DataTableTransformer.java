package co.com.certification.orangetest.datatables;

import co.com.certification.orangetest.model.EmployeeData;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {

    @DataTableType
    public EmployeeData employeeData(Map<String, String> entry) {
        EmployeeData data = new EmployeeData();
        data.setFirstName(entry.getOrDefault("firstName", "").trim());
        data.setLastName(entry.getOrDefault("lastName", "").trim());
        return data;
    }
}
