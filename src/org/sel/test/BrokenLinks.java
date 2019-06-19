package org.sel.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrokenLinks {
	static WebDriver driver;

	public static void main(String[] args) throws IOException, InterruptedException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\config.properties");
		prop.load(fis);

		String browsername = prop.getProperty("browser");

		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browsername.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Drivers\\geckodriver-v0.23.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		String Url = prop.getProperty("URL1");
		driver.get(Url);

//		driver.findElement(By.name(prop.getProperty("username_ByName"))).sendKeys(prop.getProperty("username"));
//		driver.findElement(By.name(prop.getProperty("Password_ByName"))).sendKeys(prop.getProperty("password"));

//		Thread.sleep(2000);
//		driver.findElement(By.xpath(prop.getProperty("LoginBtn_xpath"))).click();

//		driver.switchTo().frame("mainpanel");

//1:	 Get the list of all the links and images.

		List<WebElement> linkslist = driver.findElements(By.tagName("a"));
		linkslist.addAll(driver.findElements(By.tagName("img")));

		System.out.println("Size of entire links and images ----> " + linkslist.size());

//2:	Iterate linksList [Exclude all the links/images that doesn't have href attribute

		List<WebElement> activelinks = new ArrayList<WebElement>();
		for (int i = 0; i < linkslist.size(); i++) {
			if (linkslist.get(i).getAttribute("href") != null
					&& (!linkslist.get(i).getAttribute("href").contains("javascript")))
				;
			{
				activelinks.add(linkslist.get(i));

			}
		}

//		Get the size of all the active links		
		System.out.println("List of all the active links " + activelinks.size());

//3: 	Check the href url with http connection 

		for (int j = 0; j < activelinks.size(); j++) {

		HttpURLConnection conn = (HttpURLConnection) new URL(activelinks.get(j).getAttribute("href")).openConnection(); // Casting in HttpURLConnection
			conn.connect();
			String Response = conn.getResponseMessage();
			conn.disconnect();

			System.out.println(activelinks.get(j).getAttribute("href") + "----->" + Response);
		}

	}

}
