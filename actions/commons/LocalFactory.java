package commons;

import org.openqa.selenium.WebDriver;
import exception.BrowserNotSupport;
import factoryBrowser.BraveDriverManager;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.CocCocDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFirefoxDriverManager;
import factoryBrowser.OperaDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}
	
	public WebDriver createDriver() {
		 BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		 switch (browserList) {
		 case CHROME:
			 driver = new ChromeDriverManager().getBrowserDriver();
			 break;
		 case H_CHROME:
			 driver = new HeadlessChromeDriverManager().getBrowserDriver();
			 break;
		 case FIREFOX:
			 driver = new FirefoxDriverManager().getBrowserDriver();
			 break;
		 case H_FIREFOX:
			 driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
			 break;
		 case EDGE:
			 driver = new EdgeDriverManager().getBrowserDriver();
			 break;
		 case OPERA:
			 driver = new OperaDriverManager().getBrowserDriver();
			 break;
		 case COCCOC:
			 driver = new CocCocDriverManager().getBrowserDriver();
			 break;
		case BRAVE:
			driver = new BraveDriverManager().getBrowserDriver();
			break;
		default:
			 throw new BrowserNotSupport(browserName);
		}
	        return driver;
	}
}
