package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveguru.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage {
    private WebDriver driver;

    public MyDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessfulMessageAtMyDashboard() {
        waitForElementVisible(driver, MyDashboardPageUI.SUCCESSFUL_MESSAGE);
        return getElementText(driver, MyDashboardPageUI.SUCCESSFUL_MESSAGE);
    }

    public void clickToAccountLabel() {
        waitForElementClickable(driver, MyDashboardPageUI.ACCOUNT_LABEL);
        clickToElement(driver, MyDashboardPageUI.ACCOUNT_LABEL);
    }

    public HomePageObject clickToLogOutLink() {
        waitForElementClickable(driver, MyDashboardPageUI.LOG_OUT_LINK);
        clickToElement(driver, MyDashboardPageUI.LOG_OUT_LINK);
        return PageGeneratorManager.getHomePage(driver);
    }

    public boolean isMyDashboardTitleDisplayed() {
        waitForAllElementsVisible(driver, MyDashboardPageUI.MY_DASHBOARD_PAGE_TITLE);
        return isElementDisplayed(driver, MyDashboardPageUI.MY_DASHBOARD_PAGE_TITLE);
    }

}
