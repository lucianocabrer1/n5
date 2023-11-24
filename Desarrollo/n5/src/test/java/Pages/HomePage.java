package Pages;

import DriverManager.ExecutionContext;
import DriverManager.Utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import DriverManager.ExecutionContext.*;

import java.io.IOException;

public class HomePage {

    private WebDriver driver;
    private ExtentTest test;
    private WebDriverWait wait;
    private Utils utils;

    private String date = ExecutionContext.date;
    @FindBy(xpath = "//h1[@class=\"default-ltr-cache-jpuyb8 e9eyrqp8\"]")
    WebElement title;

    @FindBy(id = "login2")
    WebElement login;

    public HomePage(WebDriver driver, WebDriverWait wait, ExtentTest test){
        this.driver = driver;
        this.wait = wait;
        this.test = test;
        utils = new Utils(this.driver);
        PageFactory.initElements(this.driver, this);
    }

    public void printTitle(){
        test.log(Status.INFO,"Se Obtiene el titulo del DOM: " + driver.getTitle());
        test.addScreenCaptureFromPath(utils.TakeScreenshot("seleccionar_titulo"));

        test.log(Status.INFO,"Se Obtiene el titulo de la pantalla principal: " + wait.until(ExpectedConditions.visibilityOf(title)).getText());
    }

    public void printUrl(){
        test.log(Status.INFO,"Se busca y se imprime la url actual: " + driver.getCurrentUrl());

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.netflix.com/ar/");
    }

}
