package step_definitions;

import com.automation.qa.driver.DriverManager;
import com.automation.qa.util.Constant;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.formatter.model.Feature;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.lang.management.ManagementFactory;

/**
 * Created by Juhi Rana on 10/08/2020.
 */
public class ServiceHooks {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ServiceHooks.class));

    @BeforeSuite
    public void beforeSuite(Feature feature)throws Exception{
        Process p =  Runtime.getRuntime().exec("cmd /c start \"C:\\GIT\\ip-search\\KillDriver.Bat\"") ;
    }


    @Before
    public void before() {

        long threadId = Thread.currentThread().getId();
        threadId = 3;
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Started in thread: " + threadId + ", in JVM: " + processName);
    }


    @After
    public void after(Scenario scenario) throws Exception {
        RemoteWebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            Constant.SCENARIO_NAME = scenario.getName(); //set the scenario name to create a node in Reporter
            driver.quit();
            LOGGER.info("TTAF MESSAGE: Closing the " + Constant.BROWSER_NAME + " browser...");
        }
    }
    @AfterSuite
    public void afterSuite(Feature feature)throws Exception{
        Process p =  Runtime.getRuntime().exec("cmd /c start \"C:\\GIT\\ip-search\\KillDriver.Bat\"") ;
    }
}
