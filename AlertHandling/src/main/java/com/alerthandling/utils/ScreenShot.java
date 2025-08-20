package com.alerthandling.utils;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
    public static String filepath = System.getProperty("user.dir") + "/Screenshots/";

    public static String screenShotTC(WebDriver scdriver, String fileName) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        Date date = new Date();
        File src = ((TakesScreenshot) scdriver).getScreenshotAs(OutputType.FILE);
        String destination = filepath + fileName + "_" + dateFormat.format(date) + ".png";
        try {
            FileHandler.copy(src, new File(destination));
            return destination;
        } catch (IOException e) {
            throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
        }
    }
}
