package com.alerthandling.tests;
 
import com.alerthandling.base.DriverSetup;
import com.alerthandling.pages.ForgotPassword;
import com.alerthandling.pages.SignInPage;
import com.alerthandling.utils.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
import java.io.IOException;
 
public class AlertHandlingTests {
    public static WebDriver driver;
    static DriverSetup objDriver;
    static SignInPage signInPage;
    static ForgotPassword forgotPassword;
 
    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser) {
        objDriver = new DriverSetup();
        driver = objDriver.getDriver(browser);
 
        signInPage = new SignInPage(driver);
        forgotPassword = new ForgotPassword(driver);
 
        try {
            ScreenShot.screenShotTC(driver, "LandingPage");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Browser launched");
    }
 
    @Test(priority = 1)
    public void testEmptySignIn() {
        signInPage.clickSignIn();
        String alertText = signInPage.getSignInAlertText();
        boolean status = signInPage.verifyAlert(alertText);
        signInPage.closeAlert();
 
        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("successMessage", "1:3:Appropriate sign in alert message is displayed");
        result.setAttribute("driver", driver);
 
        Assert.assertTrue(status, "1:3:Invalid sign in alert message is displayed");
    }
 
    @Test(priority = 2)
    public void testEmptyForgotPasswordFields() {
        signInPage.clickForgotPassword();
 
        try {
            ScreenShot.screenShotTC(driver, "ForgotPasswordPage");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
 
        forgotPassword.clickNext();
        String alertText = forgotPassword.getAlertText();
        boolean status = forgotPassword.verifyAlert(alertText);
        forgotPassword.closeAlert();
        forgotPassword.navigateBack();
 
        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("successMessage", "2:3:Appropriate forgot password alert message is displayed");
        result.setAttribute("driver", driver);
 
        Assert.assertTrue(status, "2:3:Invalid forgot password alert message is displayed");
    }
 
    @Test(priority = 3)
    public void testPrivacyPolicyTab() {
        signInPage.clickPrivacyPolicy();
 
        try {
            ScreenShot.screenShotTC(driver, "PrivacyPolicyPage");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
 
        boolean status = signInPage.verifyNewTab();
 
        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("successMessage", "3:3:Privacy policy opened in new tab");
        result.setAttribute("driver", driver);
 
        Assert.assertTrue(status, "3:3:Privacy policy not opened in new tab");
    }
 
    @AfterClass
    public void tearDown() {
        objDriver.closeDriver();
        System.out.println("Browser closed.");
    }
}