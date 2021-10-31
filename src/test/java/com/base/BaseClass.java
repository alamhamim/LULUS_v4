package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.browsers.BrowserConfig;
import com.util.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class BaseClass {

    public WebDriver driver;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;



    @BeforeSuite
    public void setUp() {
        String reportFolder = "Test-Report/report_"+SeleniumHelper.getDateAndTime()+".html";
        htmlReporter = new ExtentHtmlReporter(reportFolder);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.setAppendExisting(true);

    }


    @Parameters({"couldTest", "environment", "OS", "browserName", "url"})
    @BeforeMethod
    public void browserSetup(@Optional("false") boolean cloudTest, @Optional("local") String environment, @Optional("MAC OS") String OS,
                             @Optional("chrome") String browserName, String url) throws MalformedURLException {
        //driver = BrowserConfig.getLocalDriver(driver, "MAC OS", "Chrome", "https://www.google.com/");
        if (cloudTest == true) {
            driver = BrowserConfig.getCloudDriver(driver, browserName, url);
        } else {
            driver = BrowserConfig.getLocalDriver(driver, OS, browserName, url);
        }

    }


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            logger.fail(result.getMethod().getMethodName()+" << is FAILED");

            SeleniumHelper.takeScreenShot(driver);
        } else {
            logger.pass(result.getMethod().getMethodName()+" << is PASSED");
        }

        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }



}
