package step_definitions;

import com.automation.qa.driver.DriverManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import com.automation.qa.util.Common;



/**
 * Created by Juhi on 8/8/20.
 * This is a sample steps definitions for Login page
 */

public class LoginPageSteps extends Common {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPageSteps.class));
    private RemoteWebDriver driver = DriverManager.getDriver();
    private LoginPage loginPageOld;

    @Step("I visit the web site")
    @Given("I visit the web site")
    public void iVisitTheWebSite() throws Exception {
        Reporter.log("Given I visit the web site as a guest user");
        loginPageOld = new LoginPage(driver);
        boolean pageTitle = loginPageOld.check_HomePage_Page_Title("My Store");
        Assert.assertTrue(pageTitle, "Expected Title is Not Display");
        LOGGER.info("I am on Home page");

    }


    @Step("User Enters {string} for signup")
    @When("User Enters {string} for signup")
    public void userEntersFor_SignUp(String value) {
        St.add(loginPageOld.navigate_To_SigUp_Page(value));
        Assert("Fail");
    }

    @Step("User enters the details for signup with following values:")
    @Then("User enters the details for signup with following values:")
    public void userEntersTheDetailsForSignupWithFollowingValues(DataTable datatable) {
        St.add(loginPageOld.enter_SignUp_Data(datatable));
        Assert("Fail");
    }

    @Step("User successfully signed up with message {string}")
    @Then("User successfully signed up with message {string}")
    public void userSuccessfullySignedUpWithMessage(String expectedValue) {
        St.add(loginPageOld.signUp_Status(expectedValue));
        Assert("Fail");
    }

}
