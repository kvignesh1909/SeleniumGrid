package com.mytests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.utilities.ReadConfigFile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumGridBaseClass {
	
	ReadConfigFile readconfig=new ReadConfigFile();
	public String execution=readconfig.getExecution();
	public static final String USERNAME = "kvignesh_x5X3uy";
	public static final String AUTOMATE_KEY = "LxcnFfuspzKQsrwXnQ9r";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static WebDriver driver;
	String nodeUrl ="http:localhost:4455/wd/hub";
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browserName) throws MalformedURLException
	{	
		DesiredCapabilities cap = new DesiredCapabilities();
		
		if(execution.equals("local")) {
			if(browserName.equals("chrome"))
			{
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName(browserName);
			
				System.setProperty("webdriver.chrome.driver","C://Users//vignesh.k//Documents//inetbankingV1-master//Drivers//chromedriver_1.exe");	
				driver=new RemoteWebDriver(new URL(nodeUrl),cap);
				
			}
			else if(browserName.equals("firefox"))
			{
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName(browserName);
			
				System.setProperty("webdriver.chrome.driver","C://Users//vignesh.k//Documents//inetbankingV1-master//Drivers//geckodriver.exe");
				driver=new RemoteWebDriver(new URL(nodeUrl),cap);
			}/*
			else if(browserName.equals("internetExplorer")) {
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName(browserName);
			
				System.setProperty("webdriver.chrome.driver","C://Users//vignesh.k//Documents//inetbankingV1-master//Drivers//IEDriverServer.exe");
				driver=new RemoteWebDriver(new URL(nodeUrl),cap);
			}*/
		}
		else if(execution.equals("saucelabs")) {

			MutableCapabilities sauceOpts = new MutableCapabilities();
			
			sauceOpts.setCapability("build", "Java-W3C-Examples");
			sauceOpts.setCapability("seleniumVersion", "3.141.59");
			sauceOpts.setCapability("username", "kvignesh35");
			sauceOpts.setCapability("accessKey", "588cc98f-2edc-494d-ad27-f430f36b4ff0");
			sauceOpts.setCapability("tags", "w3c-chrome-tests");

			cap.setCapability("sauce:options", sauceOpts);
			
			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				cap.setCapability("browserName", "chrome");
			} else if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				cap.setCapability("browserName", "firefox");
			}
		/*	else if (browserName.equals("ie")) {
				WebDriverManager.iedriver().setup();
				cap.setCapability("browserName", "ie");
			}
			*/
			// https://kvignesh35:588cc98f-2edc-494d-ad27-f430f36b4ff0@ondemand.us-west-1.saucelabs.com:443/wd/hub
			try {
				driver = new RemoteWebDriver(new URL("https:/ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		}else if(execution.equals("browserstack")){
			 
			cap.setCapability("os", "Windows");
			cap.setCapability("os_version", 10);
			cap.setCapability("browser_version", 80.0);
			
			if (browserName.equals("Chrome")) {
				WebDriverManager.chromedriver().setup();
				cap.setCapability("browser", "Chrome");
			} else if (browserName.equals("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				cap.setCapability("browser", "Firefox");
			}
			try {
				driver = new RemoteWebDriver(new URL(URL), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
}
