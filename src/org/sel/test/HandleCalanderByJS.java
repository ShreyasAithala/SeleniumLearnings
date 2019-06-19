package org.sel.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleCalanderByJS {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.spicejet.com");

		String dateVal1 = "21-05-2019";
		String dateVal2 = "01-06-2019";
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).sendKeys("Mumbai (BOM)");
		driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).sendKeys("Goa (GOI)");
		selectDateByJS(driver, dateVal1);
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		selectDateByJS1(driver, dateVal2);
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
		System.out.println("Succes");
	}

	public static void selectDateByJS(WebDriver driver, String dateVal1) {

		JavascriptExecutor js = ((JavascriptExecutor) driver); // casting driver into Javascript executor
//		js.executeScript("arguments[0].setAttribute('value','" + dateVal + "');", element);
		js.executeScript("document.getElementById('ctl00_mainContent_view_date1').value='" + dateVal1 + "'");
//		js.executeScript("document.getElementById('ctl00_mainContent_view_date2').value='" + dateVal2 + "'");

	}

	public static void selectDateByJS1(WebDriver driver, String dateVal2) {

		JavascriptExecutor js = ((JavascriptExecutor) driver); // casting driver into Javascript executor
//		js.executeScript("arguments[0].setAttribute('value','" + dateVal + "');", element);
//		js.executeScript("document.getElementById('ctl00_mainContent_view_date1').value='" + dateVal1 + "'");
		js.executeScript("document.getElementById('ctl00_mainContent_view_date2').value='" + dateVal2 + "'");

	}
}
