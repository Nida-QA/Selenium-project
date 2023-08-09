package UtilitiesFactory;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.WordUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static UtilitiesFactory.WaitFactory.staticWait;

public class UtilFactory {

    protected static ElementFactory elementFactory;
    protected static WaitFactory waitFactory;
    private static String envPropFile = "environment.properties";
    public static String ENumPackage = "EnumFactory.Assingment.";
    private static String screenshotFolder;
    public static String reportLocation;
    protected static String deviceName;
    public static ServiceFactory serviceFactoryInstance = ServiceFactory.getInstance();
    private static ATUTestRecorder recorder;

    static {
        try {
            screenshotFolder = new PropertyLoaderFactory().getPropertyFile(envPropFile).getProperty("screenshot.folder");
            reportLocation = new PropertyLoaderFactory().getPropertyFile(envPropFile).getProperty("extent.report.folder");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExtentReports extent;

    public static String scenarioName;
    public static ExtentTest scenarioDef;
    public static ExtentTest features;
    public static String failureException;

    public UtilFactory() throws Exception {
    }

    protected void click(String locator){
        WebElement element = elementFactory.getElement(locator);
        click(element);
    }

    protected void click(WebElement element){
        try
        {
            element.click();
        } catch (Exception e)
        {
            throw e;
        }
    }

    protected void jsClick(String locatorValue)
    {
        WebElement element = elementFactory.getElement(locatorValue);
        jsClick(element);
    }

    protected void jsClick(WebElement element)
    {
        JavascriptExecutor executor = (JavascriptExecutor) ServiceFactory.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }
    protected void clearField(String locatorValue) throws Exception
    {
        WebElement element = elementFactory.getElement(locatorValue);
        clearField(element);
    }
    protected void clearField(WebElement element) throws Exception
    {
        element.clear();
    }

    protected void enterString(String locatorValue, String fieldValue)
    {
        WebElement element = elementFactory.getElement(locatorValue);
        enterString(element,fieldValue);
    }

    protected void enterString(WebElement element, String fieldValue)
    {
        element.sendKeys(fieldValue);
    }

    protected void hover(WebElement element) {
        Actions action = new Actions(ServiceFactory.getDriver());
        action.moveToElement(element).perform();
    }

    public static Boolean isVisible(String locatorValue)
    {
        int size = elementFactory.getElementList(locatorValue);
        if(size > 0 ){
            return true;
        }
        else {
            return false;
        }
    }

    protected String getText(String locatorValue)
    {
        return getText(elementFactory.getElement(locatorValue));
    }

    protected String getText(WebElement element)
    {
        return element.getText();
    }

    protected void waitForPageLoad(){
        waitFactory.waitForPageToFinishLoading(ServiceFactory.getDriver());
    }

    protected void customWait(int waitTime){
        staticWait(waitTime);
    }

    public static String getBase64Screenshot() throws IOException {

        String Base64StringofScreenshot="";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM/dd/");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("__HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        TakesScreenshot ts;
        ts = (TakesScreenshot) ServiceFactory.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String dest = screenshotFolder + "/" +dtf.format(now) + "/" + deviceName + "/" +scenarioName+"/" +dtf2.format(now)+ ".png";

        File destination = new File(dest);
        FileUtils.copyFile(source, destination);

        byte[] fileContent = FileUtils.readFileToByteArray(source);
        Base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
        return Base64StringofScreenshot;
    }

      public static String getLocatorNameforLog(String Locator) throws IOException {
        Locator = Locator.replace("XPATH_","");
        Locator = Locator.replace("_"," ");
        Locator = WordUtils.capitalizeFully(Locator);
        return Locator;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    protected String getCurrentDateWithoutYear() {
        SimpleDateFormat format = new SimpleDateFormat("M/dd");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Dubai"));
        String date = format.format(new Date());
        return date;
    }

    public static String locatorXpath(String enumClassName, String locator) throws ClassNotFoundException {
        int i = 0;
        String XPath = null;
        Class cls = Class.forName(ENumPackage+enumClassName);
        for (Object obj : cls.getEnumConstants()) {
            try {
                Method m = cls.getMethod("getValue", null);
                XPath = m.invoke(obj, null).toString();
                if(cls.getEnumConstants()[i].toString().equals(locator)){
                    return XPath;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                System.out.println("could not find enum");
            }
            i++;
        }
        return XPath;
    }

    public static ATUTestRecorder recording(String path) throws ATUTestRecorderException, IOException {
        DateFormat datefromat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
        Date date = new Date();
        path = System.getProperty("user.dir")+path+"\\";
        Path fileToDeletePath = Paths.get(path).toAbsolutePath();
        File delFile = fileToDeletePath.toAbsolutePath().toFile();
        if(delFile.isDirectory()){
            File[] files = delFile.listFiles();

            for (File f: files) {
                f.delete();
            }
        }
        recorder = new ATUTestRecorder(path,"Execution Video-"+datefromat.format(date),false);
        return recorder;
    }
}
