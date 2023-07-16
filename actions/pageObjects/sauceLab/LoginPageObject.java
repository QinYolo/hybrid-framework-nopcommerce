package pageObjects.sauceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sauceLab.LoginPageUI;

public class LoginPageObject extends BasePage {

    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToUserNameTextbox(String userName) {
        waitForAllElementsVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, userName);
    }

    public void inputToPasswordTextbox(String password) {
        waitForAllElementsVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public ProductPageObject clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getProductPage(driver);
    }
}
