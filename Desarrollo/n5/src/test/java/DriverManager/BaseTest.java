package DriverManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest extends ExecutionContext{


    @BeforeTest
    public void SetUp(){
        THREAD_LOCAL_SPARK_REPORTER = new ExtentSparkReporter(System.getProperty("user.dir") +
                "\\src\\test\\resources\\reportes\\testReport"+ date + ".html");
        THREAD_LOCAL_EXTENT_REPORTS = new ExtentReports();

        THREAD_LOCAL_EXTENT_REPORTS.attachReporter(THREAD_LOCAL_SPARK_REPORTER);

        THREAD_LOCAL_SPARK_REPORTER.config().setOfflineMode(true);
        THREAD_LOCAL_SPARK_REPORTER.config().setDocumentTitle("Regresion Automation - N5");
        THREAD_LOCAL_SPARK_REPORTER.config().setReportName("Test Report");
        THREAD_LOCAL_SPARK_REPORTER.config().setTheme(Theme.DARK);
        THREAD_LOCAL_SPARK_REPORTER.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        THREAD_LOCAL_SPARK_REPORTER.config().setEncoding("UTF-8");

        WebDriver driver = DriverManager.setUp("chrome");
        DRIVER.set(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WAIT.set(wait);

        getDriver().get("https://www.netflix.com/");
    }

    public static WebDriver getDriver(){
        return DRIVER.get();
    }

    public static WebDriverWait getWait(){
        return WAIT.get();
    }

    public static ExtentTest getExtentTest(){ return THREAD_LOCAL_EXTENT_TEST; }

    @AfterTest
    public void TearDown(){
        if(getDriver() != null)
            getDriver().quit();
        THREAD_LOCAL_EXTENT_REPORTS.flush();
    }
}
