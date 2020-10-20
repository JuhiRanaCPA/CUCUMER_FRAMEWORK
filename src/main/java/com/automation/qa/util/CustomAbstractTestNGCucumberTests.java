//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.automation.qa.util;

import cucumber.api.CucumberOptions;
import cucumber.api.java.an.E;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import com.automation.qa.util.Constant;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@CucumberOptions(
        strict = true,
        plugin = {"json:target/cucumber-report-feature-composite.json"}
)
public class CustomAbstractTestNGCucumberTests {
    public static String executionTime =null;
    private TestNGCucumberRunner testNGCucumberRunner;
    public static String destiPath ;
    public CustomAbstractTestNGCucumberTests() {
    }


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");
        executionTime = dtf.format(LocalDateTime.now());
    }

    @BeforeClass(
            alwaysRun = true
    )
    public void setUpClass() throws Exception {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        this.testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        this.testNGCucumberRunner.finish();
        File dir = new File("src/test/resources/drivers");
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start","KillDriver.Bat");
        pb.directory(dir);
        Process p = pb.start();
        p.destroy();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception{
        FrameworkProperties loadProperties = new FrameworkProperties();
        Properties properties = loadProperties.readProjEnvConfig(Constant.class.getClassLoader().getResource("config/Configuation.properties").getPath());
        File source = new File("target/allure-results");
        destiPath = properties.getProperty("Report_Location")+"/"+executionTime;
        File dest = new File(destiPath);
        dest.mkdir();
        System.out.println("Generating HTML report");
        String absolutepath =System.getProperty("user.dir");
        try {
            FileUtils.copyDirectory(source, dest);
            ReportGenerate.generateHtmlReport(destiPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
