package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.nopcommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {

    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Register button")
    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    @Step("Verify error message display at first name textbox")
    public String getErrorMessageAtFirstNameTextbox() {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    @Step("Verify error message display at last name textbox")
    public String getErrorMessageAtLastNameTextbox() {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    @Step("Verify error message display at email textbox")
    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    @Step("Verify error message display at password textbox")
    public String getErrorMessageAtPasswordTextbox() {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    @Step("Verify error message display at password confirm textbox")
    public String getErrorMessageAtConfirmPasswordTextbox() {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    @Step("Enter to first name textbox with value is {0}")
    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to email textbox with value is {0}")
    public void inputToEmailTextbox(String email) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, email);
    }

    @Step("Enter to last name textbox with value is {0}")
    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Enter to password textbox with value is {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Enter to password confirm textbox with value is {0}")
    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    @Step("Verify succesful message display")
    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    @Step("Verify error email message display")
    public String getErrorEmailExistingMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
    }


}
