package PageObjectFactory.Assingment;

import UtilitiesFactory.UtilFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.junit.Assert;

public class CommonPageFactory extends UtilFactory {
    public static String PageName;
    public CommonPageFactory() throws Exception {
    }

    public String removeSpaces(String ScreenName) {
        PageName = ScreenName;
        String propertyFileName = ScreenName.replace(" ","");
        return propertyFileName;
    }
    public String getpropertyName(String fieldProperty) {
        String propertyFileName = fieldProperty.replace(" ",".");
        return propertyFileName;
    }

    public void textEnterField(String textToEnter,String Locator,String ScreenName) throws Exception{
        String locator = UtilFactory.locatorXpath(ScreenName,Locator);
        try{
            waitFactory.waitForElementToBeClickable(locator);
            enterString(locator,textToEnter);
            if(locator.contains("pass")){
                scenarioDef.log(Status.PASS,"Entered: "+textToEnter.replaceAll(textToEnter,"****")+" in "+getLocatorNameforLog(Locator)+" Field on "+PageName+" Page.",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            }else{
                scenarioDef.log(Status.PASS,"Entered: "+getLocatorNameforLog(textToEnter)+" in "+getLocatorNameforLog(Locator)+" Field on "+PageName+" Page.",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            }
        }catch (Exception e){
            failureException = e.toString();
            scenarioDef.log(Status.FAIL,"Could not Enter: "+getLocatorNameforLog(textToEnter)+" on "+getLocatorNameforLog(Locator)+" Field on "+PageName+" Page.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            throw e;
        }
    }

    public void clickButton(String Locator,String ScreenName) throws Exception {
        String locator = UtilFactory.locatorXpath(ScreenName,Locator);
        if(Locator.contains("XPATH_APPLY_SHIPPING")){
            waitFactory.waitForElementToBeClickable(locator);
            Thread.sleep(10000);
        }
        try{
            waitForPageLoad();
            waitFactory.waitForElementToBeClickable(locator);
            click(locator);
            scenarioDef.log(Status.PASS,"Clicked on "+getLocatorNameforLog(Locator)+" Field on "+PageName+" Page.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
        }catch (Exception e){
            failureException = e.toString();
            scenarioDef.log(Status.FAIL,"Could not Click on "+getLocatorNameforLog(Locator)+" Field on "+PageName+" Page.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            throw e;
        }
    }

    public void JsclickButton(String Locator,String ScreenName) throws Exception {
        String locator = UtilFactory.locatorXpath(ScreenName,Locator);
//        Specifically for Add To Product Button Dynamically Fetch any Button instead of Static Button
        if(Locator.equals("XPATH_ADD_TO_CART")){
            locator = "("+locator+")["+getRandomNumber(1,6)+"]";
        }
        try{
            waitFactory.waitForElementToBeClickable(locator);
            jsClick(locator);
            scenarioDef.log(Status.PASS,"Clicked on "+getLocatorNameforLog(Locator)+" Field on "+PageName+" Page.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
        }catch (Exception e){
            failureException = e.toString();
            scenarioDef.log(Status.FAIL,"Could not Click on "+getLocatorNameforLog(Locator)+" Field on "+PageName+" Page.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            throw e;
        }
    }

    public void validateDynamicString(String expectedValue, String locator, String ScreenName) throws Exception {
        String Locator = UtilFactory.locatorXpath(ScreenName,locator);
        try {
            waitFactory.waitForElementToBeVisible(Locator);
            String actualText = getText(Locator);
            if (actualText.contains(expectedValue)) {
                scenarioDef.log(Status.PASS, "Validated Actual Value " + actualText + " on " + PageName + "Page is same as Expected Value "+expectedValue,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            } else {
                scenarioDef.log(Status.PASS, "Validated Actual Value " + actualText + " on " + PageName + "Page is not same as Expected Value "+expectedValue,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            }
        }catch (Exception e){
            failureException = e.toString();
            scenarioDef.log(Status.FAIL,getLocatorNameforLog(Locator) + " is not visible  on " + PageName+" Page.",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
            throw e;
        }

    }

    public void checkElementVisibility(String Locator,String ScreenName, boolean visibility) throws Exception {
        String locator = UtilFactory.locatorXpath(ScreenName,Locator);
        visibility = UtilFactory.isVisible(locator);
        Assert.assertEquals(visibility,false);
    }
}
