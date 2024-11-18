package com.selenumeg_pomm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

public class MainTestAppUnit {

	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeAll
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver-win32\\chromedriver.exe");

		// Create driver instance
		driver = new ChromeDriver();

		// wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
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

	@Test
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

	@Test
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
	
	@Test
	public void checkContactForm() {
		try {
		HomePage homePage = new HomePage(driver);
		ContactPage contactPage = homePage.gotoContactPage();
		
		wait.until(ExpectedConditions.titleContains("Contact"));
		
		contactPage.fillContactForm("abcd", "test@mail.com", "adfdsfdfs");
		
		String message = contactPage.checkSubmission();
		
		assertTrue(message.equals("Mail Sent Successfully"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
