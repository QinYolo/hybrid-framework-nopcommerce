package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveguru.AdminPanelPageUI;

public class AdminPanelPageObject extends BasePage{
	private WebDriver driver;
	
	public AdminPanelPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToCloseButtonPopup() {
		waitForElementClickable(driver, AdminPanelPageUI.CLOSE_BUTTON_POPUP);
		clickToElement(driver, AdminPanelPageUI.CLOSE_BUTTON_POPUP);
	}
	
	public void inputToTextboxByColumnNameAndRowNumber(String columnName, String rowNumber, String valueToSend) {
		int columnNumber = getElementsSize(driver, AdminPanelPageUI.NUMBER_COLUMN_BY_NAME, columnName) + 1;
		waitForAllElementsVisible(driver, AdminPanelPageUI.TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, rowNumber, String.valueOf(columnNumber));
		sendKeyToElement(driver, AdminPanelPageUI.TEXTBOX_BY_ROW_INDEX_AND_COLUMN_INDEX, valueToSend, rowNumber, String.valueOf(columnNumber));
	}
	
	public void clickToButtonAboveTable(String buttonName) {
		waitForElementClickable(driver, AdminPanelPageUI.BUTTON_BY_NAME_ABOVE_DATA_GRID, buttonName);
		clickToElement(driver, AdminPanelPageUI.BUTTON_BY_NAME_ABOVE_DATA_GRID, buttonName);
		waitForElementInvisible(driver, AdminPanelPageUI.LOADING_MASK);
	}
	
	public boolean isAccountDisplayed(String fullName, String emailAddress) {
		waitForElementVisible(driver, AdminPanelPageUI.SEARCH_RESULT_BY_FULLNAME_AND_EMAIL, fullName, emailAddress);
		return isElementDisplayed(driver, AdminPanelPageUI.SEARCH_RESULT_BY_FULLNAME_AND_EMAIL, fullName, emailAddress);
	}
	
	public void clickToAccountCheckboxByEmail(String emailAddress) {
		waitForElementClickable(driver, AdminPanelPageUI.ACCOUNT_CHECKBOX_BY_EMAIL, emailAddress);
		checkToDefautCheckboxOrRadio(driver, AdminPanelPageUI.ACCOUNT_CHECKBOX_BY_EMAIL, emailAddress);
	}
	
	public void selectActionDropDownByName(String textToSelect) {
		waitForElementClickable(driver, AdminPanelPageUI.ACTIONS_DROPDOWN_MENU);
		selectItemInDefaultDropDown(driver, AdminPanelPageUI.ACTIONS_DROPDOWN_MENU, textToSelect);
	}
	
	public void clickSubmitButton() {
		waitForElementClickable(driver, AdminPanelPageUI.SUBMIT_BUTTON);
		clickToElement(driver, AdminPanelPageUI.SUBMIT_BUTTON);
	}
	
	public void clickToAcceptAlert() {
		acceptAlert(driver);
	}
	
	public String isSuccessMessageDisplayed() {
		waitForElementVisible(driver, AdminPanelPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminPanelPageUI.SUCCESS_MESSAGE);
	}
}
