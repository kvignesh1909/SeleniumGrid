package com.mytests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserStackBaseTest {
	
	
	WebDriver driver;
	public static final String USERNAME = "kvignesh_x5X3uy";
	public static final String AUTOMATE_KEY = "LxcnFfuspzKQsrwXnQ9r";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(String browserName) {

		DesiredCapabilities caps = new DesiredCapabilities();
        
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", 10);
		caps.setCapability("browser_version", 80.0);
		
		if (browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			caps.setCapability("browser", "Chrome");
		} else if (browserName.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			caps.setCapability("browser", "Firefox");
		}
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
