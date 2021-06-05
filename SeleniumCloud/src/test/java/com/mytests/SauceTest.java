package com.mytests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceTest extends SeleniumGridBaseClass {

	public void doLogin() {
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//*[@type='text']")).sendKeys("google");
		driver.findElement(By.xpath("//*[@type='text']")).sendKeys(Keys.ENTER);
	}
	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getSearchBoxValue() {
		String value = driver.findElement(By.xpath("//*[@type='text']")).getAttribute("value");
		return value;
	}

	@Test(priority = 1)
	public void checkPageTitle() {
		doLogin();
		Assert.assertTrue(getPageTitle().contains("google"));
	}

	@Test(priority = 2)
	public void checkSearchBox() {
		doLogin();
		Assert.assertTrue(getSearchBoxValue().equals("google"));
	}

}
