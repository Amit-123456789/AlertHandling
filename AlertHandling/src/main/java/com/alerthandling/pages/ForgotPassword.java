package com.alerthandling.pages;


import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassword {
    WebDriver driver;
    Alert alert;
    WebDriverWait wait;

    public ForgotPassword(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "txtlogin")
    WebElement emailText;

    @FindBy(name = "next")
    WebElement nextBtn;

    public void clickNext() {
        nextBtn.click();
        System.out.println("Clicked next button");
    }

    public String getAlertText() {
        alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert is displayed");
        return alert.getText();
    }

    public boolean verifyAlert(String alertText) {
        if (alertText.toLowerCase().contains("enter your email")) {
            System.out.println("Appropriate alert is displayed");
            return true;
        } else {
            System.out.println("Appropriate alert is not displayed");
            return false;
        }
    }

    public void closeAlert() {
        alert.accept();
        System.out.println("Alert closed");
    }

    public void navigateBack() {
        driver.navigate().back();
        System.out.println("Navigated back to home page");
    }
}
