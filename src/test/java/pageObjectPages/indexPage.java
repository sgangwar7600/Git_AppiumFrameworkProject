package pageObjectPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class indexPage {

	//1. creating the object of Web driver
		WebDriver ldriver;   //it's a local driver
		
		//constructor
		//when we make object , constructor call/invoke itself and execute/invoke automatically
		//constructor have no return type, not even void
		//allow only public, protected, default and private modifier
		// constructor use to initialize an objects, not to used to create an object
		
						//initializing the object(parameters/parameters)	
		public indexPage(WebDriver rdriver) {   
		
		ldriver = rdriver;  //Initializing the local driver with remote driver
									
									//parameters
			PageFactory.initElements(rdriver, this);	//here we use remote driver to search the webElements  
		}
		
		//identify the webElements
		
		@FindBy(linkText = "Sign in")
		WebElement signIn;
		
		//identify the action on WebElement
		public void clickOnSignIn() {
			signIn.click();
				
	}

	
}
