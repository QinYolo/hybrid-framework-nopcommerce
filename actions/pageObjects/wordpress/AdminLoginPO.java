package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminLoginUI;

public class AdminLoginPO extends BasePage{
	private WebDriver driver;
	
	public AdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToUsernameTextbox(String adminUsername) {
		waitForElementVisible(driver, AdminLoginUI.USERNAME_TEXTBOX);
		sendKeyToElement(driver, AdminLoginUI.USERNAME_TEXTBOX, adminUsername);
	}

	public void inputToPasswordTextbox(String adminPassword) {
		waitForElementVisible(driver, AdminLoginUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, AdminLoginUI.PASSWORD_TEXTBOX, adminPassword);
	}

	public AdminDashboardPO clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
}
