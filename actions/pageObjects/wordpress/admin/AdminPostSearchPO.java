package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminPostSearchUI;

public class AdminPostSearchPO extends BasePage {
	private WebDriver driver;
	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}
	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostSearchUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getAdminPostAddNewPage(driver);
	}
}
