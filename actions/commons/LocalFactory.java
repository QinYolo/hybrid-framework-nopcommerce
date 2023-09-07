package commons;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import exception.BrowserNotSupport;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}
	
	public WebDriver createDriver() {
		 BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
	        if (browserList == BrowserList.FIREFOX) {
	            WebDriverManager.firefoxdriver().setup();
	            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
	            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstant.PROJECT_PATH + "\\browserLogs\\FirefoxLog.log");
	            FirefoxOptions options = new FirefoxOptions();
	            options.addArguments("--disable-notifications");
	            driver = new FirefoxDriver(options);
	        } else if (browserList == BrowserList.H_FIREFOX) {
	            WebDriverManager.firefoxdriver().setup();
	            FirefoxOptions options = new FirefoxOptions();
	            options.addArguments("--headless");
	            options.addArguments("window-size=1920x1080");
	            driver = new FirefoxDriver(options);
	        } else if (browserList == BrowserList.CHROME) {
	            WebDriverManager.chromedriver().setup();
	            System.setProperty("webdriver.chrome.args", "--disable-logging");
	            System.setProperty("webdriver.chrome.silentOutput", "true");
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("--disable-notifications");
	            options.setExperimentalOption("useAutomationExtension", false);
	            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	            driver = new ChromeDriver(options);
	        } else if (browserList == BrowserList.H_CHROME) {
	            WebDriverManager.chromedriver().setup();
	            ChromeOptions options = new ChromeOptions();
	            options.addArguments("--headless");
	            options.addArguments("window-size=1920x1080");
	            driver = new ChromeDriver(options);
	        } else if (browserList == BrowserList.EDGE) {
	            WebDriverManager.edgedriver().setup();
	            driver = new EdgeDriver();
	        } else if (browserList == BrowserList.OPERA) {
	            driver = WebDriverManager.operadriver().create();
	        } else if (browserList == BrowserList.COCCOC) {
	            WebDriverManager.chromedriver().driverVersion("109.0.5414.74").setup();
	            ChromeOptions options = new ChromeOptions();
	            options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
	            driver = new ChromeDriver(options);
	        } else if (browserList == BrowserList.BRAVE) {
	            WebDriverManager.chromedriver().driverVersion("110.0.5481.77").setup();
	            ChromeOptions options = new ChromeOptions();
	            options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
	            driver = new ChromeDriver(options);
	        } else {
	            throw new BrowserNotSupport(browserName);
	        }
	        return driver;
	}
}
