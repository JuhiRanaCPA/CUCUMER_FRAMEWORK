package runner;

import com.automation.qa.util.CustomAbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Juhi Rana on 10/08/2020.
 */
@CucumberOptions(
        dryRun = false,//Skip execution of glue code.
        strict = true,// Treat undefined and pending steps as errors.
        features = "src/test/java/features",
        glue = {"step_definitions"},
        plugin = {
                //  "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "pretty",
                "json:target/cucumber-reports/smokeTestResults.json",
                "html:target/cucumber-reports"
        },
        monochrome = false,//Don't colour terminal output.
        tags = {"@smoke"}
)
@Test
//public class TestRunner{
public class TestRunner extends CustomAbstractTestNGCucumberTests {
    @Override
    @DataProvider
//    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
