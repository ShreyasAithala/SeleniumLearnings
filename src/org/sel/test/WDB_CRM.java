package org.sel.test;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Properties;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

//	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

public class WDB_CRM {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\config.properties");
		prop.load(fis);

		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browsername.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Drivers\\geckodriver-v0.23.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		String URL = prop.getProperty("URL");
		driver.get(URL);

		System.out.println(driver.getTitle());
		driver.findElement(By.name(prop.getProperty("username_ByName"))).sendKeys(prop.getProperty("username"));
		driver.findElement(By.name(prop.getProperty("Password_ByName"))).sendKeys(prop.getProperty("password"));

		boolean loginBtn = driver.findElement(By.xpath("//input[@value='Login']")).isDisplayed();
		System.out.println(loginBtn);

		WebElement loginButn = driver.findElement(By.xpath("//input[@value='Login']"));
		drawBorder(loginButn, driver);

		// Take screen shot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Copy the screen shot to desired location
//		FileUtils.copyFile(src, new File("E:\\Selenium_ScreenShots\\login.jpg"));

		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("LoginBtn_xpath"))).click();

		// When you have a Iframe panel
		driver.switchTo().frame("mainpanel");

		// For Mouse hover a specific element using actions class
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(prop.getProperty("Company_xpath")))).build().perform();
		driver.findElement(By.xpath(prop.getProperty("NewCompany_xpath"))).click();

		driver.findElement(By.id(prop.getProperty("Company_id"))).sendKeys(prop.getProperty("Company_Name"));
		driver.findElement(By.name(prop.getProperty("Industry_ByName"))).sendKeys(prop.getProperty("Industry_Name"));
		driver.findElement(By.id(prop.getProperty("PhoneNum"))).sendKeys(prop.getProperty("PhoneNum_Value"));

		/*
		 * List<WebElement> linkedlist = driver.findElements(By.tagName("a"));
		 * System.out.println("Available links " + linkedlist.size());
		 * 
		 * for (int i = 0; i < linkedlist.size(); i++) 
		 * { String linktext =
		 * linkedlist.get(i).getText(); System.out.println(linktext); }
		 * 
		 * List<WebElement> linkedlist1 =  driver.findElements(By.tagName("input"));
		 * System.out.println("Available Input Fields " + linkedlist1.size());
		 */

		/* Handle Browser Pop Up Window */
		driver.findElement(By.xpath(prop.getProperty("CompanyLookup_xpath"))).click();
		Set<String> handler = driver.getWindowHandles();
		Iterator<String> it = handler.iterator();

		String parentWindowId = it.next();
		System.out.println("parentWindowId is " + parentWindowId);

		String childWindowId = it.next();
		System.out.println("childWindowId is " + childWindowId);

		driver.switchTo().window(childWindowId);
		System.out.println("ChildWindow Title is " + driver.getTitle());
		driver.close();

		driver.switchTo().window(parentWindowId);
		System.out.println("ParentWindowTitle is " + driver.getTitle());

		driver.switchTo().frame("mainpanel");

		Select status_drpdwn = new Select(driver.findElement(By.xpath(prop.getProperty("status_drpdwn"))));
		status_drpdwn.selectByIndex(4);
		Select category_drpdwn = new Select(driver.findElement(By.xpath(prop.getProperty("Category_xpath"))));
		category_drpdwn.selectByIndex(3);
		Select Priority = new Select(driver.findElement(By.name(prop.getProperty("Priority"))));
		Priority.selectByIndex(2);

		driver.findElement(By.name(prop.getProperty("Address"))).sendKeys(prop.getProperty("Address_Value"));
		driver.findElement(By.xpath(prop.getProperty("Save_Btn"))).click();

		act.moveToElement(driver.findElement(By.xpath(prop.getProperty("Company_xpath")))).build().perform();
		driver.findElement(By.xpath(prop.getProperty("FullSearch"))).click();
		driver.findElement(By.xpath(prop.getProperty("SearchComp"))).click();

		boolean Chk_Box = driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).isSelected();
		if (Chk_Box == false) 
		driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).click();
		System.out.println(Chk_Box);
		
		boolean Chk_Box1 = driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).isSelected();
		if (Chk_Box1 == true)
		driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).click();
		System.out.println(Chk_Box1);
		
	}

	public static void drawBorder(WebElement element, WebDriver driver) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border= '3px solid red'", element);
	}

	/*
	 * // Explicit Wait for an element to be clicked
	 * 
	 * public static void clickOn(WebDriver driver, WebElement locator, int
	 * timeout){ new WebDriverWait(driver,
	 * timeout).ignoring(StaleElementReferenceException.class)
	 * .until(ExpectedConditions.elementToBeSelected(locator)); locator.click();
	 * 
	 * }
	 */

}
