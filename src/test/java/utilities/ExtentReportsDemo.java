package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import tests.BaseClass;

//here ITestListener is a interface that's why we are implementing it , not extending , in case if it would be any class so in that case we may extent that class  
public class ExtentReportsDemo  implements ITestListener  {
	
	
	ExtentSparkReporter htmlRepoter;    // object of ExtentSparkReporter class
	ExtentReports reports;              // object of ExtentReports		 class	
	ExtentTest test;					// object of ExtentTest			 class

	public void  configureReport()   {
				
		BaseClass baseclas = new BaseClass();
		 
		String timestamp = new SimpleDateFormat("MM-dd-yyyy.hh-mm-ss").format(new Date()); 
		String reportName = "AppiumExtentReport -" + timestamp + ".html"; 
		//htmlRepoter = new ExtentSparkReporter("extent.html"); //optional
		htmlRepoter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//" + reportName ); //here initialize the object of ExtentSparkReporter class
		reports=new ExtentReports();          //here initialize the object of ExtentReports class
		reports.attachReporter(htmlRepoter);

		//add system information/environment info to reports
		reports.setSystemInfo("Tester", "Shashikant Gangwar");
		reports.setSystemInfo("Machine", "LaptopPC");
		reports.setSystemInfo("OS", "window 11");
		reports.setSystemInfo("browser", "Chrome");
		reports.setSystemInfo("user name:", "Shashikant Gangwar");
		reports.setSystemInfo("browser URL:", baseclas.getbrowserURL());

	    //configuration to change look and feel of report
		htmlRepoter.config().setDocumentTitle("Extent Listener Report Demo");
		htmlRepoter.config().setReportName("Appium FrameWork For Demo");
		htmlRepoter.config().setTheme(Theme.DARK);
		htmlRepoter.config().setTimeStampFormat("EEEE, MMMM dd , yyyy , hh:mm a '('zzz')'");		
	}
	
	
	// These all are methods of ITestListener interface and all are abstract method because whichever class inherit the ITestLisner interface, in that case it's mandatory to address that class all the abstract methods of ITestListener interface, whether you use/implement it or nor. It is rule of interface , whenever you inherit or implement the any interface you have to address all the methods in the same class whether you implemented it or not.
	public void onStart(ITestContext Result) {
		
		configureReport();
	
	System.out.println("On Start method invoked....");
	}
	public void onFinish(ITestContext Result) {
		System.out.println("On Finished method invoked....");
		reports.flush();  //Writes test information from the started reporters to their output view 
		//it is mandatory to call flush method to ensure information is written to the started reporter.
	}
	
	//when test case get failed, this method is called
	public void onTestFailure(ITestResult Result) // ITestResult This class describes the result of a test and this parameter ITestListener also store the Result of test					
	{		
		System.out.println("Name of test method failed:" + Result.getName() );  		
		test = reports.createTest(Result.getName());//create entry in html report
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName() ,ExtentColor.RED));
	
	String screenShotPath = System.getProperty("user.dir") + "\\Screenshots\\" + Result.getName() + ".png";
	
	File screenShotFile = new File(screenShotPath);
	
	if(screenShotFile.exists())
	{
		try {
			test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
	
	
	//when test case get skipped, this method is called
	public void onTestSkipped(ITestResult Result) 
	{
		System.out.println("Name of test method skipped : " + Result.getName() );
		
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skip test case is :" + Result.getName(), ExtentColor.YELLOW));
	}
	
	//when test case get started , this method is called
	public void onTestStart(ITestResult Result)  
	{
		System.out.println("Name of test method started : " + Result.getName() );
	}
	
	//when test case get passed, this method is called
	public void onTestSuccess(ITestResult Result) 
	{
		System.out.println("Name of test method sucessfully executed : " + Result.getName());
		
		test = reports.createTest(Result.getName()); //create entry of passed/success test case in html report
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the pass test case is :" + Result.getName(), ExtentColor.GREEN));
	
	}
	
	public void onTestFailedButWithInSuccessPercentage(ITestResult Result) {
		
	}
	
}



