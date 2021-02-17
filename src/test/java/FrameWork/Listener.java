package FrameWork;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resource.ExtentReporter;

public class Listener extends Base implements ITestListener{

	ExtentTest test;
	
	ExtentReports extentReport = ExtentReporter.config();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //This will make parallel execution as thread safe.
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 test = extentReport.createTest(result.getMethod().getMethodName()); //Use this method to create a separate row for this test
																								   //this "test" object can be used to perform some more actions
		 extentTest.set(test); //This line will make it thread safe by setting "test" variable in extentTest,
		 					   //In further steps we shouldn't use "test." intead we should use "extentTest.get()." so that there wont be any issue in parallel execution
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
//		test.pass("Test Case passed"); //Test object of Extentreport can be used to pass the script with a message in report
		extentTest.get().log(Status.PASS, "Test Case passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
//		test.fail(result.getThrowable()); //Test object of Extentreport can be used to Fail the script "with logs" in report
		extentTest.get().fail(result.getThrowable());
		
		//In "base" class we will write the code for screenshot and use it here by inheriting the "base" class 
		WebDriver driver = null;
		
		try {
			String testMethodName = result.getMethod().getMethodName();
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(driver, testMethodName), testMethodName);
			
//			getScreenShotPath(driver, testMethodName); //added this method in "addScreenCaptureFromPath()" method as "getScreenShotPath()" method
													   //has ability to capture and senn screenshot path
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport.flush();
	}

}
