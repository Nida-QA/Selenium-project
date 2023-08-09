package UtilitiesFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.MediaType;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;

public class ExtentReportFactory extends UtilFactory {

    String fileName = reportLocation + "extentreport.html";

    public ExtentReportFactory() throws Exception {
    }


    public void ExtentReport() {
        //First is to create Extent Reports
        extent = new ExtentReports();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("SmallWorld:Automation ChallengeReport");
        htmlReporter.config().setEncoding("uft-8");
        htmlReporter.config().setReportName("SmallWorld: Automation Execution Report");
        htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Selenium Web Driver Version", System.getProperty("webdriver.__version__"));
        extent.setSystemInfo("Maven", "3.5.2");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.attachReporter(htmlReporter);

    }

    public void ExtentFailStep() throws IOException {
//        failed++;
        if(deviceName == "API"){
            scenarioDef.log(Status.FAIL,
                      "<summary> <b> <font color=red> Test Failed </b> "
                            + "</font>" + "</summary>");
        }else {
            scenarioDef.log(Status.FAIL,
                    "<details>" + "<summary> <b> <font color=red> Cause of Failure: </b> "
                            + "</font>" + "</summary>"
                            + failureException.replaceAll(",", "<br>") + "</details>", MediaEntityBuilder.createScreenCaptureFromBase64String(
                            UtilFactory.getBase64Screenshot()).build());
        }
    }

    public void ExtentPassStep() throws IOException {
//        passed++;
        scenarioDef.log(Status.PASS,
                "<summary> <b> <font color=green> Test Passed </b> "
                        + "</font>" + "</summary>"
                , MediaEntityBuilder.createScreenCaptureFromBase64String(UtilFactory.getBase64Screenshot()).build());
    }

    public void FlushReport(){
        extent.flush();
    }
}
