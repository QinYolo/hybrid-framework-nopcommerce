package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminPostAddNewUI;

public class AdminPostAddNewPO extends BasePage{
	private WebDriver driver;
	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}
	public void inputToTitleTextbox(String postTitleValue) {
		waitForElementVisible(driver, AdminPostAddNewUI.TITLE_POST_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewUI.TITLE_POST_TEXTBOX, postTitleValue);
	}
	public void inputToBodyTextbox(String postBodyValue) {
		waitForElementClickable(driver, AdminPostAddNewUI.PRE_BODY_POST_TEXTBOX);
		clickToElement(driver, AdminPostAddNewUI.PRE_BODY_POST_TEXTBOX);
		waitForElementVisible(driver, AdminPostAddNewUI.BODY_POST_TEXTBOX);
		sendKeyToElement(driver, AdminPostAddNewUI.BODY_POST_TEXTBOX, postBodyValue);
	}
	public void clickToPublishButton() {
		waitForElementClickable(driver, AdminPostAddNewUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewUI.PUBLISH_BUTTON);
	}
	public void clickToRePublishButton() {
		waitForElementClickable(driver, AdminPostAddNewUI.PRE_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewUI.PRE_PUBLISH_BUTTON);
	}
	public boolean isPublishMessageDisplayed(String publishMessage) {
		waitForElementVisible(driver, AdminPostAddNewUI.POST_PUBLISHED_BY_MESSAGE, publishMessage);
		return isElementDisplayed(driver, AdminPostAddNewUI.POST_PUBLISHED_BY_MESSAGE, publishMessage);
	}
	public void clickToCloseTutorialPopup() {
		waitForElementClickable(driver, AdminPostAddNewUI.CLOSE_BUTTON_TUTORIAL);
		clickToElement(driver, AdminPostAddNewUI.CLOSE_BUTTON_TUTORIAL);
	}
}
