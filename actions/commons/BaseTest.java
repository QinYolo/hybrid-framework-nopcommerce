package commons;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.PropertiesConfig;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected final Log log;

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    @BeforeSuite
    public void initBeforeSuite() {
        deleteAllureReport();
    }

    public WebDriver getDriverInstance() {
        return driver.get();
    }
    
    public WebDriver getBrowserDriver(String envName, String severName, String browserName, 
    		String browserVersion, String osName, String osVersion, String ipAddress, String portNumber) {
    	switch (envName) {
    	case "local":
    		driver.set(new LocalFactory(browserName).createDriver());
    		break;
    	case "grid":
    		driver.set(new GridFactory(browserName, osName, ipAddress, portNumber).createDriver());
    		break;
    	case "browserStack":
    		driver.set(new BrowserstackFactory(browserName, browserVersion, osName, osVersion).createDriver());
    		break;
    	case "sauceLab":
    		driver.set(new SauceLabFactory(browserName, osName).createDriver());
    		break;
    	case "lambda":
    		driver.set(new LambdaFactory(browserName, osName).createDriver());
    		break;
		default:
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		}
    	driver.get().manage().timeouts().implicitlyWait(PropertiesConfig.getFileConfigReader().getLongTimeout(), TimeUnit.SECONDS);
        driver.get().manage().window().maximize();
        driver.get().get(getEnvironmentValue(severName));
    	return driver.get();
    }

    protected WebDriver getBrowserDriver(String browserName, String browserURL) {
    	BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
    	if (browserList == BrowserList.FIREFOX) {
    		WebDriverManager.firefoxdriver().setup();
    		driver.set(new FirefoxDriver());
    	} else if (browserList == BrowserList.H_FIREFOX) {
    		WebDriverManager.firefoxdriver().setup();
    		FirefoxOptions options = new FirefoxOptions();
    		options.addArguments("--headless");
    		options.addArguments("window-size=1920x1080");
    		driver.set(new FirefoxDriver(options));
    	} else if (browserList == BrowserList.CHROME) {
    		WebDriverManager.chromedriver().setup();
    		driver.set(new ChromeDriver());
    	} else if (browserList == BrowserList.H_CHROME) {
    		WebDriverManager.chromedriver().setup();
    		ChromeOptions options = new ChromeOptions();
    		options.addArguments("--headless");
    		options.addArguments("window-size=1920x1080");
    		driver.set(new ChromeDriver(options));
    	} else if (browserList == BrowserList.EDGE) {
    		WebDriverManager.edgedriver().setup();
    		driver.set(new EdgeDriver());
    	} else if (browserList == BrowserList.OPERA) {
    		driver.set(WebDriverManager.operadriver().create());
    	} else if (browserList == BrowserList.COCCOC) {
    		WebDriverManager.chromedriver().driverVersion("109.0.5414.74").setup();
    		ChromeOptions options = new ChromeOptions();
    		options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
    		driver.set(new ChromeDriver(options));
    	} else if (browserList == BrowserList.BRAVE) {
    		WebDriverManager.chromedriver().driverVersion("110.0.5481.77").setup();
    		ChromeOptions options = new ChromeOptions();
    		options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
    		driver.set(new ChromeDriver(options));
    	} else {
    		throw new RuntimeException("Browser name invalid");
    	}
    	
    	driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.get().manage().window().maximize();
    	driver.get().get(browserURL);
    	
    	return driver.get();
    }

    protected String getEnvironmentValue(String severName) {
        String envUrl = null;
        EnvironmentList environment = EnvironmentList.valueOf(severName.toUpperCase());
        if (environment == EnvironmentList.DEV) {
            envUrl = GlobalConstant.getGlobalConstants().getUserPageURL();
        } else if (environment == EnvironmentList.TESTING) {
            envUrl = GlobalConstant.getGlobalConstants().getAdminPageURL();
        } else if (environment == EnvironmentList.STAGING) {
            envUrl = GlobalConstant.getGlobalConstants().getStagingPageURL();
        } else if (environment == EnvironmentList.PROD) {
            envUrl = GlobalConstant.getGlobalConstants().getProductionPageURL();
        }
        return envUrl;
    }

    protected int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public void deleteAllureReport() {
        try {
            String pathFolderDownload = GlobalConstant.getGlobalConstants().getProjectPath() + "/allure-json";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected String getCurrentDate() {
        DateTime nowUTC = new DateTime();
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            String dayValue = "0" + day;
            return dayValue;
        }
        return String.valueOf(day);
    }

    protected String getCurrentMonth() {
        DateTime now = new DateTime();
        int month = now.getMonthOfYear();
        if (month < 10) {
            String monthValue = "0" + month;
            return monthValue;
        }
        return String.valueOf(month);
    }

    protected String getCurrentYear() {
        DateTime now = new DateTime();
        return String.valueOf(now.getYear());
    }

    protected String getCurrentDay() {
        return getCurrentDate() + "/" + getCurrentMonth() + "/" + getCurrentYear();
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstant.getGlobalConstants().getOsName();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.get().toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("Windows")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.get().manage().deleteAllCookies();
                driver.get().quit();
                driver.remove();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
