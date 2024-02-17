package tests;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.ios.IOSDriver;
import utilities.ReadConfigFile;

public class BaseClass {

	
	ReadConfigFile readConfig = new ReadConfigFile(); //object of ReadConfigFilee class, also need to import the ReadConfigFilee class 
	
	//String browser = readConfig.getBrowser(); //invoke or execute the method

	String WebsiteUrl = readConfig.getBaseUrl(); //invoke or execute the method
	String email = readConfig.getEmail(); 
	String password = readConfig.getPassword();
	String firstName = readConfig.getFirstName();
	String lastName = readConfig.getLastName();
	String expectedUserName = readConfig.expectedUserName();
	
	//public static IOSDriver driver;
	public static AndroidDriver driver;
	public static Logger Logger ;  //creating static variable of logger class

	
	@BeforeClass		//before the class

	public String getbrowserURL() {
			try {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("platformversion", "14");
		
//		capabilities.setCapability("deviceName", "AndroidEmulator");
		capabilities.setCapability("deviceName", "OnePlus CPH2423");
		
		capabilities.setCapability("udid", "FA4DPRMZV4HEIVVS");

		capabilities.setCapability("newCommandTimeout", "60");

		capabilities.setCapability("browserName", "Chrome");
		
		//for firefox
		/*
		 * capabilities.setCapability("browserName", "MozillaFirefox");
		 * capabilities.setCapability("androidPackage", "org.mozilla.firefox");
		 * capabilities.setCapability("appPackage", "org.mozilla.firefox");
		 * capabilities.setCapability("appActivity", ".App");
		 */

		capabilities.setCapability("automationName", "uiautomator2");
		
		//capabilities.setCapability("app", "C:\\Users\\Shashikant\\Appium\\apkfiles\\BluSmart.apk");    //for installing the app
		
		//capabilities.setCapability("appPackage", "com.oneplus.calculator");   //for native app
		//capabilities.setCapability("appActivity" , "com.android.calculator2.Calculator");  //for native app
		
		URL url = URI.create("http://127.0.0.1:4723/").toURL();
		
		driver = new AndroidDriver(url,capabilities);
		
		//driver = new IOSDriver(url,capabilities);

		//implicit wait of 10 second
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				
		//creating the object of Logger and initializing
		Logger =  LogManager.getLogger("AppiumFrameworkProject");
       
        
		//open website URL
		driver.get(WebsiteUrl);
		Logger.info("open Website URL");	
		
		
		}
		
			
		catch(Exception exp) {
			
			System.out.println("Cause is : " + exp.getCause());
			System.out.println("Message is : " + exp.getMessage());
			exp.printStackTrace();
		}
		return WebsiteUrl ;
	}   
	
	
	
	@AfterClass			//after the class
	
	public void teardown() {
		
		driver.close();
		driver.quit();
		
	} 
	
	//user method to capture screen shot
			public void captureScreenShot(WebDriver driver,String testName) throws IOException
			{
				
				//timeStamp
				/*
				 * Date d = new Date(); String timestamp = d.toString().replace(":",
				 * "_").replace(" ", "_") ;
				 */
				//step1: convert web driver object to TakesScreenshot interface
				TakesScreenshot screenshot = ((TakesScreenshot)driver);
				
				//step2: call getScreenshotAs method to create image file
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				File dest = new File(System.getProperty("user.dir") + "//Screenshots//"  + testName + timestamp()  + ".png");
			
				//step3: copy image file to destination
				FileUtils.copyFile(src, dest);
			}
			
			  public static String timestamp() { return new
			  SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date() ); }
			 
			
}
