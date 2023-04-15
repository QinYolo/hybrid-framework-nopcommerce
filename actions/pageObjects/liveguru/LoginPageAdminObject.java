package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveguru.LoginPageAdminUI;

public class LoginPageAdminObject extends BasePage {
	private WebDriver driver;
	public LoginPageAdminObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToUserNameTextbox(String username) {
		waitForElementVisible(driver, LoginPageAdminUI.USER_NAME_TEXTBOX);
		sendKeyToElement(driver, LoginPageAdminUI.USER_NAME_TEXTBOX, username);
	}
	
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageAdminUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageAdminUI.PASSWORD_TEXTBOX, password);
	}

	public AdminPanelPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageAdminUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageAdminUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminPanel(driver);
	}
}
