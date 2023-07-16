package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserPostDetailUI;

public class UserPostDetailPO extends BasePage {
    private WebDriver driver;

    public UserPostDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPostDetailDisplayedTitle(String postTitle) {
        waitForElementVisible(driver, UserPostDetailUI.POST_TITLE_TEXT, postTitle);
        return isElementDisplayed(driver, UserPostDetailUI.POST_TITLE_TEXT, postTitle);
    }

    public boolean isPostDetailDisplayedAuthor(String postTitle, String authorName) {
        waitForElementVisible(driver, UserPostDetailUI.POST_AUTHOR_BY_POST_TITLE, postTitle, authorName);
        return isElementDisplayed(driver, UserPostDetailUI.POST_AUTHOR_BY_POST_TITLE, postTitle, authorName);
    }

    public boolean isPostDetailDisplayedTextBody(String postTitle, String postBody) {
        waitForElementVisible(driver, UserPostDetailUI.POST_BODY_BY_POST_TITLE, postTitle, postBody);
        return isElementDisplayed(driver, UserPostDetailUI.POST_BODY_BY_POST_TITLE, postTitle, postBody);
    }

    public boolean isPostDetailDisplayedDate(String postTitle, String currentDay) {
        waitForElementVisible(driver, UserPostDetailUI.POST_DATE_BY_POST_TITLE, postTitle, currentDay);
        return isElementDisplayed(driver, UserPostDetailUI.POST_DATE_BY_POST_TITLE, postTitle, currentDay);
    }


}
