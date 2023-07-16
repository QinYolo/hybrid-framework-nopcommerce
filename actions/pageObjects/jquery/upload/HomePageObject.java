package pageObjects.jquery.upload;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jquery.upload.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
    }

    public boolean isFileLinkUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
    }

    public boolean isFileImageUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
        return isImageLoaded(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtons = getListWebElement(driver, HomePageUI.START_BUTTON);
        for (WebElement oneStartButton : startButtons) {
            oneStartButton.click();
            sleepInSecond(2);
        }

    }

}
