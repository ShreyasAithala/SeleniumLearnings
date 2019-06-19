package org.sel.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.google.com");
	}

	@Test(priority = 2, groups = "title")
	public void googleTitleTest() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Google123");
	}

	@Test(priority = 1, groups = "logo", invocationCount = 2)
	public void googleLogoTest() {
		boolean logo = driver.findElement(By.xpath("//*[@id=\"hplogo\"]")).isDisplayed();
		Assert.assertTrue(logo);
	}

	@Test(priority = 3, groups = "Links", expectedExceptions = NoSuchElementException.class)
	public void googleLinkTest() {
		boolean link = driver.findElement(By.linkText("Gmails")).isDisplayed();
		System.out.println(link);
	}

	@Test(priority = 4, groups = "buttons")
	public void GoogleSearchButton() {
		String text = driver.findElement(By.name("btnK")).getText();
		System.out.println(text);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
