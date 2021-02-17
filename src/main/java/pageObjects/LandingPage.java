package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	private By loginLink = By.xpath("//*[text()='Login']");
	private By featureContent = By.xpath("//*[@class='container']/*[@class='text-center']/h2");
	private By Contact = By.xpath("//*[text()='Contact']");
	
	/**
	 * This method will click login in landing screen and create object for Login screen
	 * @return - LoginPage object
	 */
	public LoginPage clickLogin() {
//		return driver.findElement(loginLink);
		driver.findElement(loginLink).click();
		System.out.println("Login clicked");
		return new LoginPage(driver);
//		return loginPage;
	}
	
	public WebElement featureContent() {
		return driver.findElement(featureContent);
	}
	
	public WebElement contactLink() {
		return driver.findElement(Contact);
	}
}
