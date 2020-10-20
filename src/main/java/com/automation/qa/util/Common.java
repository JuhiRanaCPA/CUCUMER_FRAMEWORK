package com.automation.qa.util;

import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static org.testng.Assert.assertTrue;

/**
 * Created by Juhi Rana on 10/08/2020.
 */
public class Common extends Functions{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Common.class));
    public String openUrl(WebDriver driver,String url) {
        try {
            driver.get(url);
            LOGGER.info("INFO: user logged into url: "+url);
            return "Pass";
        } catch (Exception e) {
            e.getMessage();
            return "Fail";
        }
    }

    public void Assert(String Status) {
        try {
            LOGGER.info("<===========================================================================>");
            LOGGER.info("Asert Check: Value of Common Status list is " + St);
            System.out.print("St is: " + St);
            assertTrue(!St.contains(Status));
        } catch (Exception e) {
        } finally {
            St.clear();
            LOGGER.info("Common Status list after clearing " + St);
            LOGGER.info("<===========================================================================>");
        }
    }


}
