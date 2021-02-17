package FrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterMethod;
import org.apache.commons.io.FileUtils;


public class Base {

	//Making the driver object "static" will  help in using only this driver throughtout the test execution
	//But making driver static is having a risk of affecting parallel execution 
	//---> yes, this affected the parallel execution, so removed static WebDriver
	public  WebDriver driver;
	public Properties prop;
	
	/*
	 * This method helps in initializing and starting the browser whichever is specified in config.properties file
	 */
	public WebDriver initializeDriver() {
		try {
		prop = new Properties();
		FileInputStream popertyFile= new FileInputStream("./src/main/java/resource/config.properties");
		prop.load(popertyFile);
		
		String browser;// = System.getProperty("browser");
//		if(System.getProperty("browser").isEmpty()) {
//			browser = prop.getProperty("browser");
//		}else {
//			browser = System.getProperty("browser");
//		}
		
		browser = (System.getProperty("browser") != null) ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			
			if(browser.contains("headless")) {
			chromeOptions.addArguments("headless");
			driver = new ChromeDriver(chromeOptions);
			}else {
			driver = new ChromeDriver();
			}
			
		}else if(browser.equalsIgnoreCase("firefox")) {
			//Code for firefox driver
		}else if(browser.equalsIgnoreCase("IE")){
			//Code for IE driver
		}
		
		driver.manage().timeouts().implicitlyWait(500,TimeUnit.SECONDS );
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Found exception :" + e.getMessage());
		}
		return driver;
	}
	
	/*
	 * This method is used to get the url from config.properties file
	 */
	public String getURL() {
		String url = null;
		String server = prop.getProperty("server");
		if(server.equalsIgnoreCase("qaURL")) {
			url = prop.getProperty("qaURL");
		}else if(server.equalsIgnoreCase("devURL")) {
			url = prop.getProperty("devURL");
		}
		return url;
	}
	
	/*
	 * This method executes before all test method, where the browser will start and the url will hit
	 */
//	@BeforeMethod
	public void start() {
		initializeDriver();
		driver.get(getURL());
	}
	
	/*
	 * This method executes after all the test method, here the browser will be closed and driver object will be nullified 
	 */
	@AfterMethod
	public void teardown() {
		driver.close();
		driver = null; //making the driver to null to reduce heap memory
	}
	
	/**
	 * This method will help in capturing screenshot and placing it in a destination file
	 * @param driver - Since this method doesn't have knowledge of driver, we are fetching it from onTestFailure method in Listener class
	 * @param testCaseName - test case name which failed, screenshot will be created on this name
	 * @return 
	 */
	public String getScreenShotPath(WebDriver driver,String testCaseName) {
		String destinationPath = null;
		try {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source =screenshot.getScreenshotAs(OutputType.FILE);
		
		destinationPath = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		
		//In latest version of selenium we dont have "apache common io" in selenium jars, so get it from Maven
		FileUtils.copyFile(source , new File(destinationPath));
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationPath;
	}
	
}
