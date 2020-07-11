package activities.test_data_helper;

import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;

public class CompareJsonDataProvider {
    @DataProvider(name = "compareJson")
    public static Object[][] compareJson() {
        Object[][] jsonCompared = {
                {"Y", "Positive - Verifying with passing User_Id 1 : ", "1", HttpStatus.SC_OK, null},
                {"Y", "Positive - Verifying with passing User_Id 2 : ", "2", HttpStatus.SC_OK, null},
                {"Y", "Positive - Verifying with passing User_Id 3 : ", "3", HttpStatus.SC_OK, null}
        };
        return jsonCompared;
    }
}