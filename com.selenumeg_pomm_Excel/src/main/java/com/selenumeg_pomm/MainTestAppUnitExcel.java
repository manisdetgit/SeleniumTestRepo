package com.selenumeg_pomm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.excel.utils.ExcelReadUtils;
import com.excel.utils.ExcelWriteUtils;

import static org.junit.jupiter.api.Assertions.*;

public class MainTestAppUnitExcel {

	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeAll
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver-win32\\chromedriver.exe");

		// Create driver instance
		driver = new ChromeDriver();

		// wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		ExcelReadUtils.init();
		ExcelWriteUtils.init();
	}

	@Disabled
	public void testNavigate() {

		// load the home page

		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.selenumeg_pomm\\src\\main\\resources\\Home.html");

		// create homepage instance
		HomePage homePage = new HomePage(driver);

		// on homepage click on about page
		AboutPage aboutPage = homePage.gotoAboutPage();

		// wait until the page is loaded.

		wait.until(ExpectedConditions.titleContains("About"));

		// assert with page title
		assertTrue(driver.getTitle().contains("About"));

	}

	@Disabled
	public void testNavigateHomeContact() {

		// load the home page

		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.selenumeg_pomm\\src\\main\\resources\\Home.html");

		// create homepage instance
		HomePage homePage = new HomePage(driver);

		// on homepage click on contact page
		ContactPage contactPage = homePage.gotoContactPage();

		// wait until the page is loaded.

		wait.until(ExpectedConditions.titleContains("Contact"));

		// assert with page title
		assertTrue(driver.getTitle().contains("Contact"));

	}

	@Disabled
	public void testNavigateHomeAboutContact() {

		// load the home page

		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.selenumeg_pomm\\src\\main\\resources\\Home.html");

		// create homepage instance
		HomePage homePage = new HomePage(driver);

		// on homepage click on about page
		AboutPage aboutPage = homePage.gotoAboutPage();

		// wait until the page is loaded.

		wait.until(ExpectedConditions.titleContains("About"));

		// navigate to contact page
		ContactPage contactPage = aboutPage.gotoContactPage();

		// wait until the page is loaded.
		wait.until(ExpectedConditions.titleContains("Contact"));

		// assert with page title
		assertTrue(driver.getTitle().equals("Contact Page"));

	}
	
	static Stream<Arguments> getContactFormData(){
		//invoke ExcelReadUtils method
		Stream<Arguments> contactdata = ExcelReadUtils.readContactForm();
		return contactdata;
		
	}
	
	
	@ParameterizedTest
	@MethodSource("getContactFormData")
	public void checkContactForm(String tcase_id, String name, String email, String details) {
		try {
			
			driver.get(
					"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.selenumeg_pomm\\src\\main\\resources\\Home.html");

		HomePage homePage = new HomePage(driver);
		ContactPage contactPage = homePage.gotoContactPage();
		
		wait.until(ExpectedConditions.titleContains("Contact"));
		
		//contactPage.fillContactForm("abcd", "test@mail.com", "adfdsfdfs");
		
		contactPage.fillContactForm(name, email, details);
		String message = contactPage.checkSubmission();
		Thread.sleep(2000);
		
		assertTrue(message.equals("Mail Sent Successfully"));
		ExcelWriteUtils.writeTCResult(tcase_id, "Pass", "");
		}
		catch(AssertionError ae) {
			ExcelWriteUtils.writeTCResult(tcase_id, "Fail", "");
			ae.printStackTrace();
		}
		catch(Exception e) {
			ExcelWriteUtils.writeTCResult(tcase_id, "Error", "");
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void tearDown() {
		
		ExcelWriteUtils.generateExcel();
		if (driver != null) {
			driver.quit();
		}
	}

}
