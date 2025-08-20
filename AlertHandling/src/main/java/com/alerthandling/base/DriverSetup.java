package com.alerthandling.base;
 
import java.time.Duration;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.alerthandling.utils.DataUtils;
 
 
 
public class DriverSetup {
	public WebDriver driver;
	public static String propertiesPath = System.getProperty("user.dir")+"\\testdata\\config.properties";  
	public static String url = DataUtils.getURL(propertiesPath);
	public static String browserType;
	public WebDriver getDriver(String browser) {
		browserType = browser;
		switch(browserType.toLowerCase()){
			case "chrome": driver=new ChromeDriver(); break;
			case "edge" :
				System.setProperty("webdriver.edge.driver", "C:\\Users\\2421194\\Downloads\\edgedriver_win64\\msedgedriver.exe");
				driver=new EdgeDriver();break;
			default: throw new IllegalArgumentException("Unsupported browser: "+ browser);
		}
 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		return driver;
	}
	public void closeDriver() {
		driver.quit();
	}
 
}