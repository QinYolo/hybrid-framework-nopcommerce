package factoryBrowser;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OperaDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		return WebDriverManager.operadriver().create();
	}

}
