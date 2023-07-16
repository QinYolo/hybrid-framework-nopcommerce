package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToCreateNewAccountButton() {
        waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
        clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
    }

    public boolean isEmailAddressTextBoxDisplayed() {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
    }

    public void enterToEmailAddressTextBox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public boolean isConfirmEmailAddressTextboxDisplayed() {
        waitForElementVisible(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }

    public boolean isConfirmEmailAddressTextboxUndisplay() {
        waitForElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }

    public void closeRegisteFormPopup() {
        waitForElementClickable(driver, LoginPageUI.CLOSE_REGISTER_FORM_BUTTON);
        clickToElement(driver, LoginPageUI.CLOSE_REGISTER_FORM_BUTTON);
    }


}
