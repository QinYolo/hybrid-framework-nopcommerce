package commons;

import java.io.File;

public class GlobalConstant {
    public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final String USER_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String STAGING_PAGE_URL = "https://staging.nopcommerce.com/";
    public static final String PRODUCTION_PAGE_URL = "https://production.nopcommerce.com/";
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "extentV2" + File.separator;
    public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFiles";
    public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
    public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
    public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
    public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGImage" + File.separator;
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final long RETRY_TEST_FAIL = 3;
    
    public static final String BROWSER_USERNAME = "haterest_v8bwiA";
    public static final String BROWSER_AUTOMATE_KEY = "fqLMfoXbwdPbzxgi2Lxv";
    public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    
    public static final String SAUCE_USERNAME = "oauth-pu3kluz-854a8";
    public static final String SAUCE_AUTOMATE_KEY = "68842e0a-9bf6-40e0-863c-2b42bba2adde";
    public static final String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_AUTOMATE_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
    
    public static final String LAMBDA_USERNAME = "pu3kluz";
    public static final String LAMBDA_AUTOMATE_KEY = "EUzM5tvZ7Ugtc464vAZdjHKlAcpev32ckwk5UsUJhmBwdpr79e";
    public static final String LAMBDA_URL = "https://" + LAMBDA_USERNAME + ":" + LAMBDA_AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

}
