package org.sel.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebDriverBasics {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "F:\\Drivers\\k-v0.23.0-win64\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		driver.get("https://www.rediff.com");
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[@title='Already a user? Sign in']")).click();
		driver.findElement(By.name("proceed")).click();

		// For Alert
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();

		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Create a Rediffmail account')]")).click();

		driver.findElement(By.xpath("//input[@type='text' and @maxlength='61']")).sendKeys("Tadaka Singh");
		driver.findElement(By.xpath("//input[@type='text' and @onclick='javascript:UncheckAllOptions();']"))
				.sendKeys("t.singh");
		driver.findElement(By.xpath("//input[starts-with(@name,'passwd')]")).sendKeys("tadkasingh");

		driver.findElement(By.xpath("//input[starts-with(@name,'confirm_passwd')]")).sendKeys("tadkasingh");

		driver.findElement(By.xpath("//input[starts-with(@name,'altemail')]")).sendKeys("infotosingh@gmail.com");

		driver.findElement(By.xpath("//input[@class='nomargin']")).click();

		Select drpdown = new Select(
				driver.findElement(By.xpath("//div[@id='div_hintQS']/table/tbody/tr[2]/td[3]/select")));
		drpdown.selectByValue("What is your favourite food?");

		Select DOB_Name = new Select(
				driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[1]")));
		DOB_Name.selectByValue("14");

		Select DOB_Mon = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[2]")));
		DOB_Mon.selectByValue("07");

		Select DOB_Yr = new Select(driver.findElement(By.xpath("//*[@id=\"tblcrtac\"]/tbody/tr[22]/td[3]/select[3]")));
		DOB_Yr.selectByValue("1968");

	}

}
