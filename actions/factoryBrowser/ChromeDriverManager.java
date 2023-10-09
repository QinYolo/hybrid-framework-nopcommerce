package factoryBrowser;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		   WebDriverManager.chromedriver().setup();
           System.setProperty("webdriver.chrome.args", "--disable-logging");
           System.setProperty("webdriver.chrome.silentOutput", "true");
           ChromeOptions options = new ChromeOptions();
           options.addArguments("--disable-notifications");
           options.setExperimentalOption("useAutomationExtension", false);
           options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		return new ChromeDriver(options);
	}

}
