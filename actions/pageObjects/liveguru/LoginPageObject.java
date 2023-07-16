package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveguru.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickToCreateAnAccountButton() {
        waitForElementClickable(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        return PageGeneratorManager.getRegisterPage(driver);
    }

    public void inputToEmailAddressTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public MyDashboardPageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getMyDashboardPage(driver);
    }

    public String isErrorMessageDisplayed() {
        waitForElementVisible(driver, LoginPageUI.ERROR_MESSAGE);
        return getElementText(driver, LoginPageUI.ERROR_MESSAGE);
    }

}
