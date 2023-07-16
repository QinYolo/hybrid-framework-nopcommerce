package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserHomePageUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    public UserPostDetailPO clickToPostTitle(String postTitle) {
        waitForElementClickable(driver, UserHomePageUI.POST_TILE_TEXT, postTitle);
        clickToElement(driver, UserHomePageUI.POST_TILE_TEXT, postTitle);
        return PageGeneratorManager.getUserPostDetailPage(driver);
    }

    public boolean isPostInforDisplayedTitle(String postTitle) {
        waitForElementVisible(driver, UserHomePageUI.POST_TILE_TEXT, postTitle);
        return isElementDisplayed(driver, UserHomePageUI.POST_TILE_TEXT, postTitle);
    }

    public boolean isPostInforDisplayedAuthor(String postTitle, String authorName) {
        waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_BY_POST_TITLE, postTitle, authorName);
        return isElementDisplayed(driver, UserHomePageUI.POST_AUTHOR_BY_POST_TITLE, postTitle, authorName);
    }

    public boolean isPostInforDisplayedTextBody(String postTitle, String postBody) {
        waitForElementVisible(driver, UserHomePageUI.POST_BODY_BY_POST_TITLE, postTitle, postBody);
        return isElementDisplayed(driver, UserHomePageUI.POST_BODY_BY_POST_TITLE, postTitle, postBody);
    }

    public boolean isPostInforDisplayedDate(String postTitle, String currentDay) {
        waitForElementVisible(driver, UserHomePageUI.POST_DATE_BY_POST_TITLE, postTitle, currentDay);
        return isElementDisplayed(driver, UserHomePageUI.POST_DATE_BY_POST_TITLE, postTitle, currentDay);
    }

    public boolean isPostTitleNoDisplayedAtHomePage(String postTitle) {
        return isElementUndisplayed(driver, UserHomePageUI.POST_TILE_TEXT, postTitle);
    }

    public void enterTextToSearchTextbox(String textToSearch) {
        waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX);
        sendKeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, textToSearch);
    }

    public UserPostSearchPO clickToSearchButton() {
        waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
        clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
        return PageGeneratorManager.getUserPostSearchPage(driver);
    }
}
