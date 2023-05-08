package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.nopcommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;
	
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to Login Button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Enter email to textbox with value is {0}")
	public void inputToEmailTexbox(String email) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
	}

	@Step("Enter password to textbox with value is {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXBOX);
		sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXBOX, password);
	}

	public String getLoginErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.LOGIN_ERROR_MESSAGE);
	}

	public UserHomePageObject loginAsUser (String email, String password) {
		inputToEmailTexbox(email);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}
}
