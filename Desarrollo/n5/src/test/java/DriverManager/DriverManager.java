package DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {

    public static WebDriver setUp(String browser){
        WebDriver driver = null;

        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-infobars");
                //options.addArguments("--headless");
                options.addArguments("--disable-extensions");

                driver = new ChromeDriver(options);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();

                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                driver = new FirefoxDriver();
                break;
            case "explorer":
                WebDriverManager.iedriver().setup();

                driver = new InternetExplorerDriver();
                break;
        }

        return driver;
    }
}
