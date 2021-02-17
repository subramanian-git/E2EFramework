package FrameWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.LandingPage;


public class LandingScreenTest extends Base {
	
	WebDriver driver; //This driver object has to be initialized in all the test classes to help PARALLEL execution without any issue
	//For now i have made driver initialisaion in @BeforeMethod in "base" class, to achieve this parallel execution we should declare @BeforeMethod 
	// in all the test classes as "driver=initializeDriver();" so that this driver will have life only in that method
	@BeforeMethod
	public void start() {
		driver = initializeDriver();
		driver.get(getURL());
	}
	
	public Logger log = LogManager.getLogger(Base.class.getName());
	@Test
	public void validateFeaturedHeader() {
		LandingPage landingPage = new LandingPage(driver); 
		log.info("Featured validation test started");
		
		Assert.assertEquals(landingPage.featureContent().getText(), "FEATURED COURSES123");
		log.info("Featured validation test completed");
	}
	
	@Test
	public void validateContentLink() {
		LandingPage landingPage = new LandingPage(driver); 
		log.info("content text validation test started");
		Assert.assertTrue(landingPage.contactLink().isDisplayed());
		log.info("content text validation test completed");
	}
	
}
