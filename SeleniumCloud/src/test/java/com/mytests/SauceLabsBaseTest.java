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

public class SauceLabsBaseTest {

	WebDriver driver;

	@BeforeMethod
	@Parameters({"browser"})
	public void setUp(String browserName) {

		MutableCapabilities sauceOpts = new MutableCapabilities();
		
		sauceOpts.setCapability("build", "Java-W3C-Examples");
		sauceOpts.setCapability("seleniumVersion", "3.141.59");
		sauceOpts.setCapability("username", "kvignesh35");
		sauceOpts.setCapability("accessKey", "588cc98f-2edc-494d-ad27-f430f36b4ff0");
		sauceOpts.setCapability("tags", "w3c-chrome-tests");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("sauce:options", sauceOpts);
		
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			cap.setCapability("browserName", "chrome");
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			cap.setCapability("browserName", "firefox");
		}
		// https://kvignesh35:588cc98f-2edc-494d-ad27-f430f36b4ff0@ondemand.us-west-1.saucelabs.com:443/wd/hub
		try {
			driver = new RemoteWebDriver(new URL("https:/ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
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
