package com.automation.qa.driver;

import com.automation.qa.util.Constant;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DriverFactory.class));
    protected static DesiredCapabilities capability;
    protected static String node;
    protected static String oPlatform;

    public DriverFactory() {
    }

    public static void createInstance(String browserName, String browserVersion, String platform) {
        browserName = browserName != null ? browserName : "chrome";
        oPlatform = platform;
        setDesiredCapabilities(browserName);
        setPlatform(platform);
        setBrowserName(browserName);
        capability.setVersion(browserVersion);
        if (Constant.GRID_MODE.equals("on")) {
            node = Constant.hubURL;

            try {
                DriverManager.driver.set(new RemoteWebDriver(new URL(node), capability));
                LOGGER.info("Selenium Grid is: " + Constant.GRID_MODE);
                LOGGER.info("Selenium Grid url: " + Constant.hubURL);
                LOGGER.info("MESSAGE: Successfully set up Selenium grid.");
            } catch (Exception var4) {
                LOGGER.error("MESSAGE: Failed to set up Selenium grid.");
            }
        } else {
            setWebDriverLocation(browserName);
            createLocalInstance(browserName);
        }

        setWebDriver();
    }

    private static void setBrowserName(String browserName) {
        byte var2 = -1;
        switch(browserName.hashCode()) {
            case -1361128838:
                if (browserName.equals("chrome")) {
                    var2 = 0;
                }
                break;
            case -909897856:
                if (browserName.equals("safari")) {
                    var2 = 2;
                }
                break;
            case -849452327:
                if (browserName.equals("firefox")) {
                    var2 = 1;
                }
                break;
            case 3356:
                if (browserName.equals("ie")) {
                    var2 = 3;
                }
                break;
            case 3108285:
                if (browserName.equals("edge")) {
                    var2 = 4;
                }
        }

        switch(var2) {
            case 0:
                capability.setBrowserName("chrome");
                break;
            case 1:
                capability.setBrowserName("firefox");
                break;
            case 2:
                capability.setBrowserName("safari");
                break;
            case 3:
                capability.setBrowserName("internet explorer");
                break;
            case 4:
                capability.setBrowserName("MicrosoftEdge");
        }

    }

    private static void createLocalInstance(String browserName) {
        byte var2 = -1;
        switch(browserName.hashCode()) {
            case -1361128838:
                if (browserName.equals("chrome")) {
                    var2 = 0;
                }
                break;
            case -909897856:
                if (browserName.equals("safari")) {
                    var2 = 2;
                }
                break;
            case -849452327:
                if (browserName.equals("firefox")) {
                    var2 = 1;
                }
                break;
            case 3356:
                if (browserName.equals("ie")) {
                    var2 = 3;
                }
                break;
            case 3108285:
                if (browserName.equals("edge")) {
                    var2 = 4;
                }
        }

        switch(var2) {
            case 0:
                DriverManager.driver.set(new ChromeDriver());
                break;
            case 1:
                DriverManager.driver.set(new FirefoxDriver());
                break;
            case 2:
                DriverManager.driver.set(new SafariDriver());
                break;
            case 3:
                DriverManager.driver.set(new InternetExplorerDriver());
                break;
            case 4:
                DriverManager.driver.set(new EdgeDriver());
                break;
            default:
                DriverManager.driver.set(new ChromeDriver());
        }

    }

    private static void setPlatform(String platform) {
        byte var2 = -1;
        switch(platform.hashCode()) {
            case 107855:
                if (platform.equals("mac")) {
                    var2 = 1;
                }
                break;
            case 102977780:
                if (platform.equals("linux")) {
                    var2 = 2;
                }
                break;
            case 1349493379:
                if (platform.equals("windows")) {
                    var2 = 0;
                }
        }

        switch(var2) {
            case 0:
                capability.setPlatform(Platform.WINDOWS);
                break;
            case 1:
                capability.setPlatform(Platform.MAC);
                break;
            case 2:
                capability.setPlatform(Platform.LINUX);
                break;
            default:
                LOGGER.info("MESSAGE: Failed to set the Platform as: " + Constant.PLATFORM);
                System.exit(1);
        }

        LOGGER.info("MESSAGE: Successfully set the Platform as: " + Constant.PLATFORM);
    }

    private static void setDesiredCapabilities(String browserName) {
        capability = new DesiredCapabilities();
        byte var2 = -1;
        switch(browserName.hashCode()) {
            case -1361128838:
                if (browserName.equals("chrome")) {
                    var2 = 0;
                }
                break;
            case -909897856:
                if (browserName.equals("safari")) {
                    var2 = 2;
                }
                break;
            case -849452327:
                if (browserName.equals("firefox")) {
                    var2 = 1;
                }
                break;
            case 3356:
                if (browserName.equals("ie")) {
                    var2 = 3;
                }
                break;
            case 3108285:
                if (browserName.equals("edge")) {
                    var2 = 4;
                }
        }

        switch(var2) {
            case 0:
                HashMap<String, Object> chromePrefs = new HashMap();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.setExperimentalOption("useAutomationExtension", false);
                capability = DesiredCapabilities.chrome();
                capability.setCapability("acceptSslCerts", true);
                capability.setCapability("goog:chromeOptions", options);
                System.out.println("Success : setDesiredCapabilities");
                break;
            case 1:
                FirefoxProfile firefoxProfile = new FirefoxProfile();
//                firefoxProfile.setPreference("enableNativeEvents", true);
//                firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
//                firefoxProfile.setPreference("browser.download.folderList", 2);
//                firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
//                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
//                firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
//                firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
//                firefoxProfile.setPreference("browser.download.useDownloadDir", true);
//                firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
//                firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
//                firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
//                firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
//                firefoxProfile.setPreference("browser.download.manager.useWindow", false);
//                firefoxProfile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
//                firefoxProfile.setPreference("pdfjs.disabled", true);
//                firefoxProfile.setPreference("browser.cache.disk.enable", false);
//                firefoxProfile.setPreference("browser.cache.memory.enable", false);
//                firefoxProfile.setPreference("browser.cache.offline.enable", false);
//                firefoxProfile.setPreference("network.http.use-cache", false);
                capability = DesiredCapabilities.firefox();
//                capability.setCapability("marionette", true);
                capability.setCapability("marionette", true);
//                capability.setCapability("acceptSslCerts", true);
//                capability.setCapability("firefox_profile", firefoxProfile);
                System.out.println("Success : setDesiredCapabilities");
                break;
            case 2:
                capability = DesiredCapabilities.safari();
                capability.setJavascriptEnabled(true);
                capability.setCapability("unexpectedAlertBehaviour", "accept");
                break;
            case 3:
                capability = DesiredCapabilities.internetExplorer();
                capability.setCapability("ignoreProtectedModeSettings", true);
                capability.setCapability("ignoreProtectedModeSettings", true);
                capability.setCapability("requireWindowFocus", true);
                capability.setCapability("initialBrowserUrl", true);
                capability.setCapability("acceptSslCerts", true);
                capability.setCapability("enablePersistentHover", false);
                capability.setCapability("ignoreZoomSetting", true);
                capability.setCapability("requireWindowFocus", false);
                capability.setCapability("nativeEvents", false);
                break;
            case 4:
                capability = DesiredCapabilities.edge();
                capability.setCapability("acceptSslCerts", true);
                capability.setCapability("ignoreProtectedModeSettings", true);
                capability.setCapability("javascriptEnabled", true);
                capability.setCapability("pageLoadStrategy", "eager");
                break;
            default:
                LOGGER.error("MESSAGE: Failed to set Browser Capabilities.");
                System.exit(1);
        }

        LOGGER.info("MESSAGE: Successfully set Capabilities for " + browserName + " browser");
    }

    private static void setWebDriverLocation(String browserName) {
        URL driverLocation = Constant.class.getClassLoader().getResource("drivers/");
        byte var3 = -1;
        switch(browserName.hashCode()) {
            case -1361128838:
                if (browserName.equals("chrome")) {
                    var3 = 0;
                }
                break;
            case -849452327:
                if (browserName.equals("firefox")) {
                    var3 = 1;
                }
                break;
            case 3356:
                if (browserName.equals("ie")) {
                    var3 = 2;
                }
                break;
            case 3108285:
                if (browserName.equals("edge")) {
                    var3 = 3;
                }
        }

        switch(var3) {
            case 0:
                if (oPlatform.equals("windows")) {
                    System.setProperty("webdriver.chrome.driver", driverLocation.getPath() + "chromedriver.exe");
                } else if (oPlatform.equals("linux")) {
                    System.setProperty("webdriver.chrome.driver", driverLocation.getPath() + "chromedriver");
                }
                break;
            case 1:
                if (oPlatform.equals("windows")) {
                    System.setProperty("webdriver.gecko.driver", driverLocation.getPath() + "geckodriver.exe");
                } else if (oPlatform.equals("linux")) {
                    System.setProperty("webdriver.gecko.driver", driverLocation.getPath() + "geckodriver");
                }
                break;
            case 2:
                System.setProperty("webdriver.ie.driver", driverLocation.getPath() + "IEDriverServer.exe");
                break;
            case 3:
                System.setProperty("webdriver.edge.driver", driverLocation.getPath() + "MicrosoftWebDriver.exe");
                break;
            default:
                LOGGER.info("MESSAGE: Failed to  Set the driver locations");
                System.exit(1);
        }

        LOGGER.info("MESSAGE: Successfully Set the driver locations");
    }

    public static void setWebDriver() {
        ((RemoteWebDriver)DriverManager.driver.get()).manage().timeouts().implicitlyWait((long)Constant.TIMEOUT_IMPLICIT, TimeUnit.MILLISECONDS);
        ((RemoteWebDriver)DriverManager.driver.get()).manage().window().maximize();
        ((RemoteWebDriver)DriverManager.driver.get()).manage().deleteAllCookies();
        ((RemoteWebDriver)DriverManager.driver.get()).navigate().to(Constant.URL);
    }
}
