package commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	
	public LambdaFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setCapability("platform", osName);
    	caps.setCapability("browserName", browserName);
    	caps.setCapability("version", "latest");
    	caps.setCapability("visual", true);
    	caps.setCapability("video", true);
    	caps.setCapability("name", "Run On " + osName + " and " + browserName );
    	Map<String, Object> lambdaOptions = new HashMap<>();
    	if (osName.contains("Window")) {
    		lambdaOptions.put("resolution","1600x1200");
		} else {
			lambdaOptions.put("resolution","1920x1080");
		}
    	try {
			driver = new RemoteWebDriver(new URL(GlobalConstant.LAMBDA_URL), caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
