package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostSearchUI;

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

    public void inputToSearchTextBox(String postTitle) {
        waitForElementVisible(driver, AdminPostSearchUI.POST_SEARCH_TEXTBOX);
        sendKeyToElement(driver, AdminPostSearchUI.POST_SEARCH_TEXTBOX, postTitle);
    }

    public void clickToSearchPostsButton() {
        waitForElementClickable(driver, AdminPostSearchUI.SEARCH_POST_BUTTON);
        clickToElement(driver, AdminPostSearchUI.SEARCH_POST_BUTTON);
    }

    public boolean isPostSearchTableDisplayedByID(String idColumn, String titleValue) {
        int columnIndex = getElementsSize(driver, AdminPostSearchUI.INDEX_COLUMN_BY_ID, idColumn) + 1;
        waitForElementVisible(driver, AdminPostSearchUI.TABLE_ROW_VALUE_BY_INDEX, String.valueOf(columnIndex), titleValue);
        return isElementDisplayed(driver, AdminPostSearchUI.TABLE_ROW_VALUE_BY_INDEX, String.valueOf(columnIndex), titleValue);
    }

    public void clickToPostTitleLink(String idColumn, String postTitle) {
        int columnIndex = getElementsSize(driver, AdminPostSearchUI.INDEX_COLUMN_BY_ID, idColumn) + 1;
        waitForElementClickable(driver, AdminPostSearchUI.TABLE_ROW_VALUE_BY_INDEX, String.valueOf(columnIndex), postTitle);
        clickToElement(driver, AdminPostSearchUI.TABLE_ROW_VALUE_BY_INDEX, String.valueOf(columnIndex), postTitle);
    }

    public void selectActionNameAtDropdownList(String actionName) {
        waitForElementClickable(driver, AdminPostSearchUI.ACTION_SELECT_DROPDOWN_TOP);
        selectItemInDefaultDropDown(driver, AdminPostSearchUI.ACTION_SELECT_DROPDOWN_TOP, actionName);
    }

    public void clickToCheckboxByTitle(String PostTitle) {
        waitForElementClickable(driver, AdminPostSearchUI.CHECKBOX_BUTTON_BY_TITLE, PostTitle);
        checkToDefautCheckboxOrRadio(driver, AdminPostSearchUI.CHECKBOX_BUTTON_BY_TITLE, PostTitle);
    }

    public void clickToApplyButton() {
        waitForElementClickable(driver, AdminPostSearchUI.APPLY_ACTION_BUTTON);
        clickToElement(driver, AdminPostSearchUI.APPLY_ACTION_BUTTON);
    }

    public boolean isPostStatusMessageDisplayed(String statusPost) {
        waitForElementVisible(driver, AdminPostSearchUI.POST_STATUS_UPDATE_MESSAGE, statusPost);
        return isElementDisplayed(driver, AdminPostSearchUI.POST_STATUS_UPDATE_MESSAGE, statusPost);
    }

    public boolean isNoPostFoundMessageDisplayed() {
        waitForElementVisible(driver, AdminPostSearchUI.NO_POST_FOUND_MESSAGE);
        return isElementDisplayed(driver, AdminPostSearchUI.NO_POST_FOUND_MESSAGE);
    }

}
