package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features ="src/test/resources/FeatureFiles/Assingment/",
        monochrome = true,
        plugin = {"json:target/cucumber.json", "pretty"},
        glue = {"com.cucumber.StepsDefinitions",
        "UtilitiesFactory"})

public class SwagLabs extends AbstractTestNGCucumberTests {


}