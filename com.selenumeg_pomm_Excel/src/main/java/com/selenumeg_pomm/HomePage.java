package com.selenumeg_pomm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private WebDriver driver;

	// Create locators
	private By aboutLink = By.id("aboutLink");
	private By contactLink = By.id("contactLink");
	private By subscribeForm = By.id("subscribeForm");
	private By email = By.id("email");
	private By subscribeButton = By.id("subscribeButton");
	private By acceptTerms = By.id("acceptTerms");

	// Constructor expects WebDriver as Parameter
	public HomePage(WebDriver driver) {
		System.out.println("Displaying Home Page..." + driver.getTitle());
		this.driver = driver;
	}

	// Methods to perform actions on above WebElements
	public AboutPage gotoAboutPage() {
		WebElement aboutLinkElement = driver.findElement(aboutLink);
		aboutLinkElement.click();
		return new AboutPage(driver);
	}

	public ContactPage gotoContactPage() {
		WebElement contactLinkElement = driver.findElement(contactLink);
		contactLinkElement.click();
		return new ContactPage(driver);
	}
}
