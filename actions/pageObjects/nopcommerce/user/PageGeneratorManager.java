package pageObjects.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;

public class PageGeneratorManager {
    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserCustomerInforPageObject getUserCustomerInforPage(WebDriver driver) {
        return new UserCustomerInforPageObject(driver);
    }

    public static UserAddressesPageObject getUserAddressesPage(WebDriver driver) {
        return new UserAddressesPageObject(driver);
    }

    public static UserOrdersPageObject getUserOrdersPage(WebDriver driver) {
        return new UserOrdersPageObject(driver);
    }

    public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
        return new UserChangePasswordPageObject(driver);
    }

    public static UserMyProductReviewsPageObject getUserMyProductReviewsPage(WebDriver driver) {
        return new UserMyProductReviewsPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }

    public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }

}
