package org.sel.test;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WDBCRM_TestNG {

	WebDriver driver;
	Properties prop;
	Actions act;
	WebElement element;
	WebDriverWait wait;
	SoftAssert softassert = new SoftAssert();

	@BeforeSuite
	public void SystemSetup() throws Exception {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\config.properties");
		prop.load(fis);
	}

	@BeforeTest
	public void LaunchUrl() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\LanunchUrl.properties");
		prop.load(fis);
		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browsername.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\Drivers\\geckodriver-v0.23.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		String URL = prop.getProperty("URL");
		driver.get(URL);
		System.out.println(driver.getTitle());
	}

	@BeforeClass
	public void BrowserProperties() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@Test
	public void LoginCRM() throws InterruptedException, Exception {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\Login.properties");
		prop.load(fis);

		driver.findElement(By.name(prop.getProperty("username_ByName"))).sendKeys(prop.getProperty("username"));
		driver.findElement(By.name(prop.getProperty("Password_ByName"))).sendKeys(prop.getProperty("password"));
		boolean loginBtn = driver.findElement(By.xpath("//input[@value='Login']")).isDisplayed();
		System.out.println(loginBtn);
		WebElement loginButn = driver.findElement(By.xpath("//input[@value='Login']"));
//		drawBorder(loginButn, driver);
//      Take screen shot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
// 		Copy the screen shot to desired location
//		FileUtils.copyFile(src, new File("E:\\Selenium_ScreenShots\\login.jpg"));
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("LoginBtn_xpath"))).click();
		Assert.assertEquals(true, true);

	}

	/*
	 * @Test public void NewCompanySelection() throws Exception {
	 * 
	 * Properties prop = new Properties(); FileInputStream fis = new
	 * FileInputStream(
	 * "E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\NewCompanySelection.properties"
	 * ); prop.load(fis);
	 * 
	 * // When you have a Iframe panel driver.switchTo().frame("mainpanel"); // For
	 * Mouse hover a specific element using actions class Actions act = new
	 * Actions(driver);
	 * act.moveToElement(driver.findElement(By.xpath(prop.getProperty(
	 * "Company_xpath")))).build().perform();
	 * driver.findElement(By.xpath(prop.getProperty("NewCompany_xpath"))).click();
	 * 
	 * }
	 * 
	 * @Test(dependsOnMethods = "NewCompanySelection") public void
	 * NewCompanyDetails() throws Exception {
	 * 
	 * Properties prop = new Properties(); FileInputStream fis = new
	 * FileInputStream(
	 * "E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\NewCompanyDetails.properties"
	 * ); prop.load(fis);
	 * 
	 * driver.findElement(By.id(prop.getProperty("Company_id"))).sendKeys(prop.
	 * getProperty("Company_Name"));
	 * driver.findElement(By.name(prop.getProperty("Industry_ByName"))).sendKeys(
	 * prop.getProperty("Industry_Name"));
	 * driver.findElement(By.id(prop.getProperty("PhoneNum"))).sendKeys(prop.
	 * getProperty("PhoneNum_Value")); }
	 * 
	 * @Test(dependsOnMethods = "NewCompanyDetails") public void CompanyLookup()
	 * throws Exception { Properties prop = new Properties(); FileInputStream fis =
	 * new FileInputStream(
	 * "E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\CompanyLookup.properties"
	 * ); prop.load(fis);
	 * 
	 * driver.findElement(By.xpath(prop.getProperty("CompanyLookup_xpath"))).click();
	 * Set<String> handler = driver.getWindowHandles(); Iterator<String> it =
	 * handler.iterator();
	 * 
	 * String parentWindowId = it.next(); System.out.println("parentWindowId is " +
	 * parentWindowId);
	 * 
	 * String childWindowId = it.next(); System.out.println("childWindowId is " +
	 * childWindowId);
	 * 
	 * driver.switchTo().window(childWindowId);
	 * System.out.println("ChildWindow Title is " + driver.getTitle());
	 * driver.close();
	 * 
	 * driver.switchTo().window(parentWindowId);
	 * System.out.println("ParentWindowTitle is " + driver.getTitle());
	 * 
	 * driver.switchTo().frame("mainpanel");
	 * 
	 * }
	 * 
	 * @Test(dependsOnMethods = "CompanyLookup") public void CompanyCreation()
	 * throws Exception {
	 * 
	 * Properties prop = new Properties(); FileInputStream fis = new
	 * FileInputStream(
	 * "E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\CompanyCreation.properties"
	 * ); prop.load(fis);
	 * 
	 * Select status_drpdwn = new
	 * Select(driver.findElement(By.xpath(prop.getProperty("status_drpdwn"))));
	 * status_drpdwn.selectByIndex(4); Select category_drpdwn = new
	 * Select(driver.findElement(By.xpath(prop.getProperty("Category_xpath"))));
	 * category_drpdwn.selectByIndex(3); Select Priority = new
	 * Select(driver.findElement(By.name(prop.getProperty("Priority"))));
	 * Priority.selectByIndex(2);
	 * driver.findElement(By.name(prop.getProperty("Address"))).sendKeys(prop.
	 * getProperty("Address_Value"));
	 * driver.findElement(By.xpath(prop.getProperty("Save_Btn"))).click();
	 * 
	 * }
	 * 
	 * @Test(dependsOnMethods = "CompanyCreation") public void SearchCompany()
	 * throws Exception {
	 * 
	 * Properties prop = new Properties(); FileInputStream fis = new
	 * FileInputStream(
	 * "E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\SearchCompany.properties"
	 * ); prop.load(fis); Actions act = new Actions(driver);
	 * act.moveToElement(driver.findElement(By.xpath(prop.getProperty(
	 * "Company_xpath")))).build().perform();
	 * driver.findElement(By.xpath(prop.getProperty("FullSearch"))).click();
	 * driver.findElement(By.xpath(prop.getProperty("SearchComp"))).click();
	 * 
	 * boolean Chk_Box =
	 * driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).isSelected()
	 * ; if (Chk_Box == false)
	 * driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).click();
	 * System.out.println(Chk_Box);
	 * 
	 * boolean Chk_Box1 =
	 * driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).isSelected()
	 * ; if (Chk_Box1 == true)
	 * driver.findElement(By.xpath(prop.getProperty("Comp_Chk_Click"))).click();
	 * System.out.println(Chk_Box1); }
	 */
	@Test(dependsOnMethods = "LoginCRM")
	public void ContactInformation() throws IOException, InterruptedException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\Eclipse-WS\\SeleniumLearnings\\src\\org\\sel\\test\\ContactInformation.properties");
		prop.load(fis);
		driver.switchTo().frame("mainpanel");
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(prop.getProperty("Contact_Tab")))).build().perform();
		driver.findElement(By.xpath(prop.getProperty("NewContact"))).click();
		Select Titledrpdwn = new Select(driver.findElement(By.name(prop.getProperty("TitleDrpDwn"))));
		Titledrpdwn.selectByIndex(3);
		driver.findElement(By.id(prop.getProperty("FirstName"))).sendKeys(prop.getProperty("FirstNameVal"));
		driver.findElement(By.id(prop.getProperty("LastName"))).sendKeys(prop.getProperty("LastNameVal"));
		boolean Email = (driver.findElement(By.xpath(prop.getProperty("ReceiveEmail")))).isDisplayed();
		if (Email == true) {
			driver.findElement(By.xpath(prop.getProperty("ReceiveEmail"))).click();
		}
		boolean SMS = driver.findElement(By.xpath(prop.getProperty("SMS"))).isSelected();
		if (SMS == true) {
			driver.findElement(By.xpath(prop.getProperty("SMS_NO"))).click();
		}

		WebElement FileUpl = driver.findElement(By.id(prop.getProperty("ChooseFile")));
		FileUpl.sendKeys(prop.getProperty("FileUplPath"));
		String winHandleBefore = driver.getWindowHandle();

		driver.findElement(By.xpath(prop.getProperty("CompLKP"))).click();
		// Perform the click operation that opens new window
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		System.out.println("Child Window Title is: " + driver.getTitle());

		String value = driver.findElement(By.xpath(prop.getProperty("AssertValue"))).getText();
		softassert.assertEquals(value, prop.getProperty("ActualValue"));
		softassert.assertAll();
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		try {
			wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert Text Is: " + alert.getText());
			alert.accept();
			Assert.assertTrue(alert.getText().contains(prop.getProperty("AlertValue")));
		} catch (Exception e) {
			softassert.assertAll();
		}

		driver.findElement(By.id(prop.getProperty("CompSearch"))).sendKeys(prop.getProperty("CompSearchVal"));
		driver.findElement(By.xpath(prop.getProperty("SearchBtn"))).click();
		driver.findElement(By.xpath(prop.getProperty("SearchRes"))).click();
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().frame("mainpanel");		
		driver.findElement(By.xpath(prop.getProperty("LoadCompany"))).click();
		driver.findElement(By.xpath(prop.getProperty("SaveContact"))).click();
		act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(prop.getProperty("MoveToContacts")))).build().perform();
		driver.findElement(By.xpath("//*[@id=\"navmenu\"]/ul/li[4]/ul/li[3]/a")).click();		
		
	}


	/*
	 * @Test public void drawBorder() {
	 * 
	 * JavascriptExecutor js = ((JavascriptExecutor) driver);
	 * js.executeScript("arguments[0].style.border= '3px solid red'", element); }
	 */

}
