package commons;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserstackFactory {
	private WebDriver driver;
	private String browserName;
	private String browserVersion;
	private String osName;
	private String osVersion;
	
	
	public BrowserstackFactory(String browserName, String browserVersion, String osName, String osVersion) {
		this.browserName = browserName;
		this.browserVersion = browserVersion;
		this.osName = osName;
		this.osVersion = osVersion;
	}


	public WebDriver createDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setCapability("os", osName);
    	caps.setCapability("os_version", osVersion);
    	caps.setCapability("browser", browserName);
    	caps.setCapability("browser_version", browserVersion);
    	caps.setCapability("browserstack.debug", "true");
    	caps.setCapability("name", "Run On " + osName + " and " + browserName + " with version " + browserVersion);
    	caps.setCapability("resolution","1600x1200");
    	try {
    		driver = new RemoteWebDriver(new URL(GlobalConstant.BROWSER_STACK_URL), caps);
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	}
		return driver;
	}
}
