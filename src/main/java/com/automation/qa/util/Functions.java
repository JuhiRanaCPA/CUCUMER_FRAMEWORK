package com.automation.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Juhi Rana on 10/08/2020.
 */
public class Functions {
    public static ArrayList<String> St = new ArrayList<String>();


    /**
     * Method Name: JavaScriptExecutor Parameters: webelment,action Return Type:
     * String Objective: Public function used to perform ajavscript opeartions
     */
    public String JavaScriptExecutor(WebDriver driver, WebElement element, String action) {
        String Flag = "Pass";

        try {
            Properties properties = System.getProperties();
            /*
             * String fld = properties.getProperty(webelment); String val; if (fld != null)
             * val = fld; else val = webelment.toString();
             */
            switch (action) {
                case "click":
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", element);
                    break;

                case "getText":
                    return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", element);

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Need handling");
            Flag = "Fail";
        }
        return Flag;

    }

    public void addPageLoadTimeOut(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Constant.Page_Load_TimeOut, TimeUnit.SECONDS);
    }

    public void addImplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Constant.Implicit_Wait_Time, TimeUnit.SECONDS);
    }

    public void addHardWait(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

}
