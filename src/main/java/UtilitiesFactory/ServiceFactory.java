package UtilitiesFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static ServiceFactory instance = null;
    private static String BROWSER;
    private static RequestSpecification request;
    private static Response response;
    private static JSONObject requestParams = new JSONObject();

    // Singleton to make thread safe
    private ServiceFactory()
    {
        // hide this
    }

    public static ServiceFactory getInstance()
    {
        if (instance == null)
        {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public final void setDriver(String browser)
    {
        switch (browser.toUpperCase())
        {

            //Emulator settings for Android Mobile
            case "MOBILE":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions_androidMobile()));
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver(firefoxOptions()));
                break;

            case "CHROME":
//                WebDriverManager.chromedriver().proxy("https://chromedriver.storage.googleapis.com:443").setup();
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions_desktop()));
                break;

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver(edgeOptions()));
                break;

            default:
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(chromeOptions_desktop()));
                break;
        }
    }

    public static WebDriver getDriver()
    {
        return driver.get();
    }

    private ChromeOptions chromeOptions_androidMobile() {

        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", "Galaxy S5");

        // Setup Chrome environment:
        ChromeOptions chromeOptions = new ChromeOptions();

        // Add command-line switches:
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-extensions-file-access-check");
        chromeOptions.addArguments("--disable-extensions-http-throttling");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=240,720");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        chromeOptions.addArguments("--enable-automation");
        if (System.getenv("CHROME_HEADLESS") != null) {

            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-setuid-sandbox");
            chromeOptions.addArguments("--hide-scrollbars");
            chromeOptions.addArguments("--ignore-ssl-errors");
        }

        // Set Chrome Profile Preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

    private ChromeOptions chromeOptions_desktop()
    {
        // Setup Chrome environment:
        ChromeOptions chromeOptions = new ChromeOptions();

        // Add command-line switches:
//        chromeOptions.addArguments("--disable-extensions");
//        chromeOptions.addArguments("-incognito");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        chromeOptions.addArguments("--enable-automation");
        if (System.getenv("CHROME_HEADLESS") != null)
        {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-setuid-sandbox");
            chromeOptions.addArguments("--hide-scrollbars");
            chromeOptions.addArguments("--ignore-ssl-errors");
        }

        // Set Chrome Profile Preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

    private FirefoxOptions firefoxOptions()
    {
        // Setup Firefox Environment
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        firefoxOptions.addArguments("--window-size=1536,722");
        firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);


        return firefoxOptions;
    }

    private EdgeOptions edgeOptions()
    {
        // Setup Edge Environment
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        return edgeOptions;
    }

    public void setBrowser(String browser){
        BROWSER = browser;
    }

    public String getBrowser(){
        return BROWSER;
    }
    public static RequestSpecification getRequest(){
        return request;
    }
    public static void setRequest(RequestSpecification requestSpecificationData){
        request = requestSpecificationData;
    }

    public static void setResponse(Response responseData){
        response = responseData;
    }
    public static void setParams(JSONObject paramsData){
        requestParams = paramsData;
    }

}
