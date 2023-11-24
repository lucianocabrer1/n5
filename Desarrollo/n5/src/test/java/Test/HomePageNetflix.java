package Test;

import DriverManager.BaseTest;
import Pages.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageNetflix extends BaseTest {

    @Test
    public void addToCart() throws IOException, InvalidFormatException {
        THREAD_LOCAL_EXTENT_TEST = THREAD_LOCAL_EXTENT_REPORTS.createTest("Navagar netflix",
                "Se desea navegar en la pantalla principal de netflix");
        
        HomePage homePage = new HomePage(getDriver(), getWait(), getExtentTest());

        homePage.printTitle();

        homePage.printUrl();
    }
}
