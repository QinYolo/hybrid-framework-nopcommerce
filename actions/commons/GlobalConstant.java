package commons;

import java.io.File;

import lombok.Getter;

@Getter
public class GlobalConstant {
	private static GlobalConstant globalInstance;
	private GlobalConstant() {
	}
	
	public static synchronized GlobalConstant getGlobalConstants() {
		if (globalInstance == null) {
			globalInstance = new GlobalConstant();
		}
		return globalInstance;
	}
	private final String adminPageURL = "https://admin-demo.nopcommerce.com/";
    private final String userPageURL = "https://demo.nopcommerce.com/";
    private final String stagingPageURL = "https://staging.nopcommerce.com/";
    private final String productionPageURL = "https://production.nopcommerce.com/";
    private final String projectPath = System.getProperty("user.dir");
    private final String javaVersion = System.getProperty("java.version");
    private final String osName = System.getProperty("os.name");
    private final String extentPath = projectPath + File.separator + "extentV2" + File.separator;
    private final String uploadFile = projectPath + File.separator + "uploadFiles" + File.separator;
    private final String downloadFile = projectPath + File.separator + "downloadFiles";
    private final String browserLog = projectPath + File.separator + "browserLogs";
    private final String dragDropHTML5 = projectPath + File.separator + "dragDropHTML5";
    private final String autoITScript = projectPath + File.separator + "autoIT";
    private final String reportingScreenshot = projectPath + File.separator + "ReportNGImage" + File.separator;
    private final long shortTimeout = 5;
    private final long longTimeout = 30;
    private final long retryTestFail = 3;
    
    private final String browserUsername = "haterest_v8bwiA";
    private final String browserAutomateKey = "fqLMfoXbwdPbzxgi2Lxv";
    private final String browserStackURL = "https://" + browserUsername + ":" + browserAutomateKey + "@hub-cloud.browserstack.com/wd/hub";
    
    private final String sauceUsername = "oauth-pu3kluz-854a8";
    private final String sauceAutomateKey = "68842e0a-9bf6-40e0-863c-2b42bba2adde";
    private final String sauceURL = "https://" + sauceUsername + ":" + sauceAutomateKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
    
    private final String lambdaUsername = "pu3kluz";
    private final String lambdaAutomateKey = "EUzM5tvZ7Ugtc464vAZdjHKlAcpev32ckwk5UsUJhmBwdpr79e";
    private final String lambdaURL = "https://" + lambdaUsername + ":" + lambdaAutomateKey + "@hub.lambdatest.com/wd/hub";

}
