package com.automation.qa.listener;

import com.automation.qa.driver.DriverFactory;
import com.automation.qa.util.Constant;
import java.util.logging.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class WebDriverListener implements IInvokedMethodListener {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(WebDriverListener.class));

    public WebDriverListener() {
    }

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        LOGGER.info("MESSAGE:BEGIN: com.automation.qa.listener.WebDriverListener.beforeInvocation");
        if (iInvokedMethod.isTestMethod()) {
            String browserName = (String)iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            String browserVersion = (String)iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("browserVersion");
            String platform = (String)iInvokedMethod.getTestMethod().getXmlTest().getLocalParameters().get("platform");
            Constant.BROWSER_NAME = browserName;
            Constant.BROWSER_VERSION = browserVersion;
            Constant.PLATFORM = platform;
            LOGGER.info("Execution Browser set as: " + browserName);
            LOGGER.info("Execution Browser Version set as: " + browserVersion);
            LOGGER.info("Execution Platform set as: " + platform);
            DriverFactory.createInstance(browserName, browserVersion, platform);
            LOGGER.info("Done! Created " + browserName + " driver!");
        } else {
            LOGGER.info(" MESSAGE: TTAF Provided method is NOT a TestNG testMethod!!!");
        }

        LOGGER.info("MESSAGE:END: com.automation.qa.listener.WebDriverListener.beforeInvocation");
    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        LOGGER.info("BEGINNING: com.automation.qa.listener.WebDriverListener.afterInvocation");
        if (iInvokedMethod.isTestMethod()) {
        }

        LOGGER.info("END: com.automation.qa.listener.WebDriverListener.afterInvocation");
    }
}
