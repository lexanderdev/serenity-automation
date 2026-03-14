package co.com.certification.orangetest.datatables;

import co.com.certification.orangetest.model.OrangeData;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTableTransformer {

    @DataTableType
    public OrangeData orangeData(Map<String, String> entry) {

        OrangeData data = new OrangeData();

        String strUser = entry.get("strUser");
        String strPassword = entry.get("strPassword");
        String strSearch = entry.get("strSearch");

        data.setStrUser(strUser != null ? strUser.trim() : null);
        data.setStrPassword(strPassword != null ? strPassword.trim() : null);
        data.setStrSearch(strSearch != null ? strSearch.trim() : null);


        return data;
    }
}


