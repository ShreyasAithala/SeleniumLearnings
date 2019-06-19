package org.sel.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/*
 * This is not a part of selenium 3.0 and to use this concept we need to
 * download htmlUnitDriver JAR file.
 * Also called ghost driver. 
 * Headless browser(HTMLUnitDriver with Java)
 * PhantomJs - JavaScript
 * 	
 * Advantages : Behind the scene testing, no browser is launched
 * 				Execution of Test case is fast.
 * 
 * Disadvantages : Not suitable for action class like mouse movement 
 * 					double click	drag and drop
 */

public class HtmlUnitDriverConcept {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new HtmlUnitDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://www.freecrm.com/");
		System.out.println("Title before logging in is :" + driver.getTitle());

		driver.findElement(By.name("username")).sendKeys("shreyasaithala");
		driver.findElement(By.name("password")).sendKeys("Password1");
		Thread.sleep(3000);
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		System.out.println("Title after logging in is :" + driver.getTitle());

		WebElement loginBtn = driver.findElement(By.xpath("//input[contains(@type,'submit')]"));
		flash(loginBtn, driver);

	}

	public static void flash(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 100; i++) {
			changeColor("rgb(0,200,0)", element, driver);
			changeColor(bgcolor, element, driver);
		}
	}

	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" +color+ "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

}
