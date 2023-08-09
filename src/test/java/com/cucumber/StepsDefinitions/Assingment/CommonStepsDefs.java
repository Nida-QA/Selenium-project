package com.cucumber.StepsDefinitions.Assingment;

import PageObjectFactory.Assingment.CommonPageFactory;
import UtilitiesFactory.*;
import com.cucumber.StepsDefinitions.HarnessVariables;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CommonStepsDefs extends HarnessVariables {

    CommonPageFactory commonPage;
    String runPropFile = "run.properties";

    public CommonStepsDefs() throws Exception {
        commonPage = new CommonPageFactory();
    }

    @Given("User Setup Web Browser Session")
    public void userSetupWebBrowserSession() throws Exception {
        serviceFactoryInstance.setDriver(serviceFactoryInstance.getBrowser());
        deviceName = "WEB";
        waitFactory = new WaitFactory(ServiceFactory.getDriver());
        elementFactory = new ElementFactory(ServiceFactory.getDriver());
    }

    @Then("User Navigates to {string} URL")
    public void userNavigatesToURL(String url) throws Exception {
        url = commonPage.getpropertyName(url);
        url = new PropertyLoaderFactory().getPropertyFile(runPropFile).getProperty(url);
        ServiceFactory.getDriver().get(url);
    }

    @Then("User Validates {string} Title")
    public void userValidatesTitle(String expectedTitle) throws Exception {
        expectedTitle = commonPage.getpropertyName(expectedTitle);
        expectedTitle = new PropertyLoaderFactory().getPropertyFile(runPropFile).getProperty(expectedTitle);
        String actualTitle = ServiceFactory.getDriver().getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }
    @When("User Enters {string} on {string} Field on {string} Page")
    public void user_enters_on_field_on_page(String testData, String locator, String screenName) throws Exception{
        screenName = commonPage.removeSpaces(screenName);
        testData = commonPage.removeSpaces(testData);
        locator=commonPage.removeSpaces(locator);
        locator = new PropertyLoaderFactory().getLocatorPropertyFile(screenName+".properties").getProperty(locator);
        testData = new PropertyLoaderFactory().getTestDataPropertyFile(screenName+".properties").getProperty(testData);
        commonPage.textEnterField(testData,locator,screenName);
}

    @And("User Click on {string} Button on {string} Page")
    public void userClickOnButtonOnPage(String locator, String screenName) throws Exception{
        screenName = commonPage.removeSpaces(screenName);
        locator=commonPage.getpropertyName(locator);
        locator = new PropertyLoaderFactory().getLocatorPropertyFile(screenName+".properties").getProperty(locator);
        commonPage.clickButton(locator,screenName);
    }

    @Then("User Validates {string} Element Not Displayed on {string} Page")
    public void userValidatesElementNotDisplayedOnPage(String locator, String screenName) throws Exception {
        screenName = commonPage.removeSpaces(screenName);
        locator = new PropertyLoaderFactory().getLocatorPropertyFile(screenName+".properties").getProperty(locator);
        commonPage.checkElementVisibility(locator,screenName,true);
    }


    @Then("User Validates {string} of {string} On {string} Page")
    public void userValidatesOfOnPage(String testData, String locator, String screenName) throws Exception {
        screenName = commonPage.removeSpaces(screenName);
        testData = commonPage.removeSpaces(testData);
        locator = commonPage.removeSpaces(locator);
        testData = new PropertyLoaderFactory().getTestDataPropertyFile(screenName+".properties").getProperty(testData);
        locator = new PropertyLoaderFactory().getLocatorPropertyFile(screenName+".properties").getProperty(locator);
        commonPage.validateDynamicString(testData,locator ,screenName);
    }

    @And("User Clicks on {string} Button on {string} Page")
    public void userClicksOnButtonOnPage(String locator, String screenName) throws Exception {
        screenName = commonPage.removeSpaces(screenName);
        locator=commonPage.removeSpaces(locator);
        locator = new PropertyLoaderFactory().getLocatorPropertyFile(screenName+".properties").getProperty(locator);
        commonPage.JsclickButton(locator,screenName);
    }
}
