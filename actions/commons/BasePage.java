package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopcommerce.user.UserAddressesPageObject;
import pageObjects.nopcommerce.user.UserChangePasswordPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopcommerce.user.UserOrdersPageObject;
import pageUIs.nopcommerce.user.BasePageUI;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.user.PageGeneratorManager;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openPageURL (WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}
	
	protected void quitPageURL (WebDriver driver) {
		driver.quit();
	}
	
	protected String getPageTitle (WebDriver driver) {
		return driver.getTitle();
	}
	
	protected String getPageURL (WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected String getPageSourceCode (WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPage (WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage (WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void refreshCurrentPage (WebDriver driver) {
		driver.navigate().refresh();
	}
	
	protected Alert waitForAlertPresence (WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected void acceptAlert (WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	protected void cancelAlert (WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	protected String getAlertText (WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	protected void sendKeyToAlert (WebDriver driver, String textToAlert) {
		waitForAlertPresence(driver).sendKeys(textToAlert);
	}
	
	protected String getWindowHandle (WebDriver driver) {
		return driver.getWindowHandle();
	}
	
	protected void switchToWindowByID (WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String eachWindowID : allWindowIDs) {
			if (!eachWindowID.equals(windowID)) {
				driver.switchTo().window(eachWindowID);
				break;
			}
		}
	}
	
	protected void switchToWindowByTitle (WebDriver driver, String titleWindow) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String eachWindowID : allWindowIDs) {
			driver.switchTo().window(eachWindowID);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(titleWindow)) {
				break;
			}
		}
	}
	
	protected void closeAllTabWithoutParent (WebDriver driver, String parentWindowID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		for (String ID : allWindowsID) {
			if (!ID.equals(parentWindowID)) {
				driver.switchTo().window(ID);
				sleepInSecond(2);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindowID);
	}
	
	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private By getByLocator (String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")  || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		return by;
	}
	
	private String getDynamicXpath(String locator, String... values) {
		if (locator.startsWith("xpath=") || locator.startsWith("Xpath=") 
				|| locator.startsWith("XPATH=")  || locator.startsWith("XPath=")) {
			locator = String.format(locator, (Object[]) values);
		}
		return locator;
	}
	
	private WebElement getWebElement (WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	private List<WebElement> getListWebElement (WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	protected void clickToElement (WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	
	protected void clickToElement (WebDriver driver, String locator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locator, dynamicValues)).click();
	}
	
	protected void sendKeyToElement (WebDriver driver, String locator, String textValue) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(textValue);
	}
	
	protected void sendKeyToElement (WebDriver driver, String locator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
		
	protected void selectItemInDefaultDropDown(WebDriver driver, String locator, String textItem) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(textItem);
	}
	
	protected void selectItemInDefaultDropDown(WebDriver driver, String locator, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	protected String getFirstSelectedItemDefaultDropDown (WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	protected String getFirstSelectedItemDefaultDropDown (WebDriver driver, String locator, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	protected boolean isDropDownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	
	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);
		WebDriverWait explicitWait;
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	protected String getElementAttribute (WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	protected String getElementAttribute (WebDriver driver, String locator, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getAttribute(attributeName);
	}
	
	protected String getElementText (WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	protected String getElementText (WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).getText();
	}
	
	protected String getElementCSSValue (WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	protected String getHexaColorFromRGBA (String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	protected int getElementsSize (WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}
	
	protected int getElementsSize (WebDriver driver, String locator, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locator, dynamicValues)).size();
	}
	
	protected void checkToDefautCheckboxRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	protected void uncheckToDefaultCheckbox (WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected boolean isElementDisplayed (WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	protected boolean isElementDisplayed (WebDriver driver, String locator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locator, dynamicValues)).isDisplayed();
	}
	
	protected boolean isElementEnabled (WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	protected boolean isElementSelected (WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	protected void switchToFrameiFrame (WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	protected void switchToDefaultContent (WebDriver driver, String locator) {
		driver.switchTo().defaultContent();
	}
	
	protected void hoverMouseToElement (WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	
	protected Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	protected String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	protected void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	protected void highlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	protected void highlightElement(WebDriver driver, String locator, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locator, dynamicValues));
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	
	protected void clickToElementByJS(WebDriver driver, String locator, String...dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
	}

	protected void scrollToElement(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	protected boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void waitForElementVisible (WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	
	protected void waitForElementVisible (WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	protected void waitForAllElementsVisible (WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}
	
	protected void waitForAllElementsVisible (WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}

	protected void waitForElementInvisible (WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}
	
	protected void waitForElementInvisible (WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}

	protected void waitForAllElementsInvisible (WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
	}
	
	protected void waitForAllElementsInvisible (WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locator, dynamicValues))));
	}

	protected void waitForElementClickable (WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	protected void waitForElementClickable (WebDriver driver, String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValues))));
	}
	
	protected boolean isKeywordDisplayInSearchResult(WebDriver driver, String locator, String keywords) {
		List<WebElement> listElement = getListWebElement(driver, locator);
		for (WebElement eachElement : listElement) {
			if (eachElement.getText().contains(keywords)) {
				return true;
			}
		}
		return false;
	}
	
	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Addresses":
			return PageGeneratorManager.getUserAddressesPage(driver);
		case "Orders":
			return PageGeneratorManager.getUserOrdersPage(driver);
		case "Change password":
			return PageGeneratorManager.getUserChangePasswordPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewsPage(driver);
		default:
			throw new RuntimeException("Invalid Page Name at My Account area");
		}
	}
	
	public UserHomePageObject clickToLogOutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickToLogOutLinkAtAdminPage(WebDriver driver) {
		waitForElementInvisible(driver, BasePageUI.LOADING_ICON_ON_HEADER_ADMIN);
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	private long longTimeout = 30;
}
