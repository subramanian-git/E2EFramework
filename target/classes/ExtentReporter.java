package resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	static ExtentReports extent;
	
	public static ExtentReports config() {
		//We need two classes object to achieve this Extent Report
		//ExtentReports - Main class which will create report
		//ExtendSparkReporter - this class will need a path as argument, where the report will be created
		//					  - And this class has "config" method which has ability to modify the report title, name, etc...
		
		String path = System.getProperty("user.dir")+"//reports//report.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Test Report");
		report.config().setDocumentTitle("My Result");
		
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Mani");
		
		return extent;
	}

}
