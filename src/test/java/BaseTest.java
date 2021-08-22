package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {

    private static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\reports\\Automation.html");;
    private static ExtentReports extent;
    private static ExtentTest extentLogger;
    private static Logger logger = Logger.getLogger("BaseTest");
    public static String baseUri = "https://api.spacexdata.com";

    @BeforeTest
    public void beforeTest() {
        PropertyConfigurator.configure("log4j.properties");
        logger.info("***Test Start***");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation-Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation  Tester", "Akshay Kousal");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        logger.info("***Method " + method.getName() + " Start***");
        extentLogger = extent.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        logger.info("***Method End***");
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "TEST CASE : " + methodName + " PASSED";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            extentLogger.log(Status.PASS, m);

        } else if (result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logText = "TEST CASE : " + methodName + " FAILED";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            extentLogger.log(Status.FAIL, m);
        }
    }


    @AfterTest
    public void afterTest() {
        logger.info("***Test End***");
        extent.flush();
    }

}
