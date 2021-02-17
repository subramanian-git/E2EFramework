package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By userName = By.xpath("//*[@id='user_email']");
	private By password = By.xpath("//*[@id='user_password']");
	private By loginSubmit = By.xpath("//*[@type='submit']");
	
	public WebElement userName() {
		return driver.findElement(userName);
	}
	
	public WebElement password() {
		return driver.findElement(password);	
	}
	
	public WebElement loginSubmit() {
		return driver.findElement(loginSubmit);
	}
}
