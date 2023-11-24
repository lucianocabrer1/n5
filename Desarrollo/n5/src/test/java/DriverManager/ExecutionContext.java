package DriverManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class ExecutionContext {

    protected static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    protected static final ThreadLocal<WebDriverWait> WAIT = new ThreadLocal<>();

    public static ExtentSparkReporter THREAD_LOCAL_SPARK_REPORTER;
    public static ExtentReports THREAD_LOCAL_EXTENT_REPORTS;

    public static ITestResult THREAD_LOCAL_TEST_RESULT;
    public static Throwable THREAD_LOCAL_TEST_EXCEPTION;
    public static ExtentTest THREAD_LOCAL_EXTENT_TEST;

    public static final String date = new Utils().getDate();
}
