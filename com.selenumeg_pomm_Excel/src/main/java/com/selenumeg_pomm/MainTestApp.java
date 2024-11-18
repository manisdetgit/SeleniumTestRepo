package com.selenumeg_pomm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainTestApp {
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver-win32\\chromedriver.exe");

		// Create driver instance
		WebDriver driver = new ChromeDriver();

		// Load the webpage
		driver.get(
				"file:///C:\\Users\\Administrator\\eclipse-workspace\\com.selenumeg_pomm\\src\\main\\resources\\Home.html");

		//create homepage instance
		HomePage homePage = new HomePage(driver);	
		Thread.sleep(2000);
		
		//create about instance
		AboutPage aboutPage = homePage.gotoAboutPage();
		Thread.sleep(2000);
		
		ContactPage ContactPage = aboutPage.gotoContactPage();
		Thread.sleep(2000);
		
		ContactPage.gotoHomePage();
		Thread.sleep(2000);
		
		homePage.gotoContactPage();
		Thread.sleep(2000);
		
		ContactPage.fillContactForm("Test Name", "testmail@email.com", "Test message");
		Thread.sleep(2000);
		ContactPage.checkSubmission();
		
		
		driver.quit();
	}

}
