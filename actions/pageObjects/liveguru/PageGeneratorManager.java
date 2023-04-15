package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static MyDashboardPageObject getMyDashboardPage(WebDriver driver) {
		return new MyDashboardPageObject(driver);
	}
	
	public static LoginPageAdminObject getLoginPageAdmin(WebDriver driver) {
		return new LoginPageAdminObject(driver);
	}
	
	public static AdminPanelPageObject getAdminPanel(WebDriver driver) {
		return new AdminPanelPageObject(driver);
	}
	
}
