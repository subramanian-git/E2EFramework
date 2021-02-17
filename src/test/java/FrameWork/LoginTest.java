package FrameWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class LoginTest extends Base{
	
	WebDriver driver;
	
	public Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeMethod
	public void start() {
		driver = initializeDriver();
		driver.get(getURL());
	}
	
	@Test(dataProvider = "getData")
	public void Test1(String userMail , String password , String text){
//		driver = initializeDriver();
		log.info("Login test started");
		driver.get("http://www.qaclickacademy.com");
		System.out.println("url launched");
		LandingPage landingPage = new LandingPage(driver);
		LoginPage loginPage = landingPage.clickLogin();
		
//		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.userName().sendKeys(userMail);
		log.info("Entered username: "+userMail);
		loginPage.password().sendKeys(password);
		log.info("Entered username: "+password);
		System.out.println(text);
		loginPage.loginSubmit().click();
		log.info("Login test completed");
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];
		data[0][0] = "nonrestrictedaccount@gmail.com";
		data[0][1] = "ampassword";
		data[0][2] = "Non-restricted acount";
		
		data[1][0] = "restrictedaccount@gmail.com";
		data[1][1] = "ampassword";
		data[1][2] = "restricted acount";
		
		return data;
	}

}
