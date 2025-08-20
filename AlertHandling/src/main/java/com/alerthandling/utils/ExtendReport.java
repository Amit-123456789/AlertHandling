package com.alerthandling.utils;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class ExtendReport implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public String filePath = System.getProperty("user.dir") + "/reports/alertHandlingReport";
 
    public void onStart(ITestContext context) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        Date date = new Date();
        String reportpath = filePath + "/ExtentReport_" + formatter.format(date) + ".html";
 
        sparkReporter = new ExtentSparkReporter(reportpath);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Alert Handling Report");
        sparkReporter.config().setTheme(Theme.DARK);
 
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
 
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Environment", "QEA");
        extent.setSystemInfo("Tester Name", "Amit Kumar Singh");
        extent.setSystemInfo("OS", "Windows 10");
    }
 
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test case PASSED is: " + result.getName());
 
        String[] message = ((String) result.getAttribute("successMessage")).split(":");
        int r = Integer.parseInt(message[0]);
        int c = Integer.parseInt(message[1]);
        DataUtils.writeDataIntoExcel(r, c, message[2]);
        DataUtils.writeDataIntoExcel(r, c + 1, "PASS");
        DataUtils.fillGreenColor(r, c + 1);
    }
 
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case FAILED is: " + result.getName());
        test.log(Status.FAIL, "Cause: " + result.getThrowable());
 
        String[] message = (result.getThrowable().getMessage()).split(":");
        int r = Integer.parseInt(message[0]);
        int c = Integer.parseInt(message[1]);
        DataUtils.writeDataIntoExcel(r, c, message[2]);
        DataUtils.writeDataIntoExcel(r, c + 1, "FAIL");
        DataUtils.fillRedColor(r, c + 1);
 
        WebDriver driver = (WebDriver) result.getAttribute("driver");
        try {
            String screenshotPath = ScreenShot.screenShotTC(driver, result.getName());
            test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName());
    }
 
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}