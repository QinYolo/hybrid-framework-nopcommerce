package pageObjects.jquery.database;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.database.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	public HomePageObject (WebDriver driver) {
		this.driver = driver;
	}
	
	public void openPagingByPageNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUI.PAGINATION_BY_PAGE_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION_BY_PAGE_NUMBER, pageNumber);
	}
	
	public void enterToHeaderTextboxByLabel(String headerLabel, String valueSendToTextbox) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
		sendKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, valueSendToTextbox, headerLabel);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
	}
	
	public boolean isPageNumberActive(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_PAGE_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_PAGE_NUMBER, pageNumber);
	}

	public boolean isDataDisplayedByInputLabelTextbox(String headerLabel, String valuesResult) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_VALUE_DISPLAYED_BY_LABEL, headerLabel.toLowerCase(), valuesResult);
		return isElementDisplayed(driver, HomePageUI.HEADER_TEXTBOX_VALUE_DISPLAYED_BY_LABEL, headerLabel.toLowerCase(), valuesResult);
	}

	public List<String> getVallueEachRowAtAllPage() {
		int totalPage = getElementsSize(driver, HomePageUI.TOTAL_PAGINATION);
		List<String> allRowValuesOfAllPages = new ArrayList<String>();
		// Duyệt qua tất cả các page number
		for (int index = 1; index <= totalPage ; index++) {
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(index));
			sleepInSecond(1);
			//Get text của all row mỗi page
			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				// Đưa text vào array list
				allRowValuesOfAllPages.add(eachRow.getText());
			}
		}
		for (String value : allRowValuesOfAllPages) {
			System.out.println(value);
			System.out.println("----------------------------");
		}
		return allRowValuesOfAllPages;
	}

	public void enterToTextboxByColumnNameAtRowNumber(String nameColumn, String rowNumber, String valueToSend) {
		int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, nameColumn) + 1;
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		sendKeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToSend, rowNumber, String.valueOf(columnIndex));
	}

	public void selectDefaultDropDownByColumnNameAtRowNumber(String nameColumn, String rowNumber, String valueToSelect) {
		int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, nameColumn) + 1;
		waitForElementClickable(driver, HomePageUI.DROP_DOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		selectItemInDefaultDropDown(driver, HomePageUI.DROP_DOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToSelect, rowNumber, String.valueOf(columnIndex));
	}

	public void clickToLoadButton() {
		waitForElementClickable(driver, HomePageUI.LOAD_BUTTON);
		clickToElement(driver, HomePageUI.LOAD_BUTTON);
	}

	public void checkToCheckBoxByColumnNameAtRowNumber(String nameColumn, String rowNumber) {
		int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, nameColumn) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		checkToDefautCheckboxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
	}

	public void uncheckToCheckBoxByColumnNameAtRowNumber(String nameColumn, String rowNumber) {
		int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, nameColumn) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
		uncheckToDefaultCheckbox(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowNumber, String.valueOf(columnIndex));
	}

	
}
