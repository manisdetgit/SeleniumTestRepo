package com.selenumeg_pomm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AboutPage {

	private WebDriver driver;

	// Create locators
	private By homeLink = By.id("homeLink");
	private By contactLink = By.id("contactLink");
	private By showmoreButton = By.id("moreInfoButton");
	private By moreInfoDiv = By.id("moreInfo");

	public AboutPage(WebDriver driver) {
		System.out.println("Displaying About Page..." + driver.getTitle());
		this.driver = driver;
	}

	// Methods to perform actions on above WebElements
	public HomePage gotoHomePage() {
		WebElement homeLinkElement = driver.findElement(homeLink);
		homeLinkElement.click();
		return new HomePage(driver);
	}

	public ContactPage gotoContactPage() {
		WebElement contactLinkElement = driver.findElement(contactLink);
		contactLinkElement.click();
		return new ContactPage(driver);
	}

	public void showMoreInfo() {
		driver.findElement(showmoreButton).click();
	}

	public void validateShowMore() {
		String moreinfo = driver.findElement(moreInfoDiv).getText();
		System.out.println("moreinfo:" + moreinfo);
	}

	
}
