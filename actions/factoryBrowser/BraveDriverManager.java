package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BraveDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		  WebDriverManager.chromedriver().driverVersion("110.0.5481.77").setup();
          ChromeOptions options = new ChromeOptions();
          options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
		return new ChromeDriver(options);
	}

}
