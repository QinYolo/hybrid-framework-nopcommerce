package pageObjects.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.ProductPageUI;

public class ProductPageObject extends BasePage {

    private WebDriver driver;

    public ProductPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectItemInProductDropdown(String itemValue) {
        waitForElementVisible(driver, ProductPageUI.PRODUCT_DROPDOWN_MENU);
        selectItemInDefaultDropDown(driver, ProductPageUI.PRODUCT_DROPDOWN_MENU, itemValue);
    }

    public boolean isProductNameSortByAscending() {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> nameProducts = getListWebElement(driver, ProductPageUI.PRODUCT_NAME);
        for (WebElement elementName : nameProducts) {
            arrayList.add(elementName.getText());
        }
        ArrayList<String> arrayListSorted = new ArrayList<>();
        for (String nameElement : arrayList) {
            arrayListSorted.add(nameElement);
        }
        Collections.sort(arrayListSorted);
        return arrayListSorted.equals(arrayList);
    }

    public boolean isProductNameSortByDescending() {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> nameProduct = getListWebElement(driver, ProductPageUI.PRODUCT_NAME);
        for (WebElement nameElement : nameProduct) {
            arrayList.add(nameElement.getText());
        }
        ArrayList<String> arrayListSorted = new ArrayList<>();
        for (String child : arrayList) {
            arrayListSorted.add(child);
        }
        Collections.sort(arrayListSorted);
        Collections.reverse(arrayListSorted);
        return arrayListSorted.equals(arrayList);
    }

    public boolean isProductPriceSortByAscending() {
        ArrayList<Float> arrayList = new ArrayList<>();
        List<WebElement> nameProduct = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE);
        for (WebElement nameElement : nameProduct) {
            arrayList.add(Float.parseFloat(nameElement.getText().replace("$", "")));
        }
        ArrayList<Float> arrayListSorted = new ArrayList<>();
        for (Float child : arrayList) {
            arrayListSorted.add(child);
        }
        Collections.sort(arrayListSorted);
        return arrayListSorted.equals(arrayList);
    }

    public boolean isProductPriceSortByDescending() {
        ArrayList<Float> arrayList = new ArrayList<>();
        List<WebElement> nameProduct = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE);
        for (WebElement nameElement : nameProduct) {
            arrayList.add(Float.parseFloat(nameElement.getText().replace("$", "")));
        }
        ArrayList<Float> arrayListSorted = new ArrayList<>();
        for (Float child : arrayList) {
            arrayListSorted.add(child);
        }
        Collections.sort(arrayListSorted);
        Collections.reverse(arrayListSorted);
        return arrayListSorted.equals(arrayList);
    }
}
