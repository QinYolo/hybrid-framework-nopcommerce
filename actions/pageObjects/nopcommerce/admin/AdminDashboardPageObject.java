package pageObjects.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
private WebDriver driver;
	
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isAdminDashboardHeaderDisplayed() {
		waitForAllElementsVisible(driver, AdminDashboardPageUI.DASHBOARD_TITLE_HEADER);
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_TITLE_HEADER);
	}
	

}
