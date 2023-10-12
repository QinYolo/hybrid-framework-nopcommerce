package commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	
	public SauceLabFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setCapability("platformName", osName);
    	caps.setCapability("browserName", browserName);
    	Map<String, Object> sauceOptions = new HashMap<>();
    	if (osName.contains("Window")) {
    		sauceOptions.put("screenResolution","1920x1080");
    	} else {
    		sauceOptions.put("screenResolution","1600x1200");
    	}
    	sauceOptions.put("name", "Run On " + osName + " and " + browserName );
    	caps.setCapability("sauce:options", sauceOptions);
    	try {
    		driver = new RemoteWebDriver(new URL(GlobalConstant.getGlobalConstants().getSauceURL()), caps);
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	}
		return driver;
	}
}
