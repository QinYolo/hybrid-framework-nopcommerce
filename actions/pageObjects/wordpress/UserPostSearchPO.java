package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserPostSearchUI;

public class UserPostSearchPO extends BasePage {
	private WebDriver driver;
	public UserPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isNothingFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, UserPostSearchUI.NOTHING_FOUND_MESSAGE, message);
		return isElementDisplayed(driver, UserPostSearchUI.NOTHING_FOUND_MESSAGE, message);
	}
}
