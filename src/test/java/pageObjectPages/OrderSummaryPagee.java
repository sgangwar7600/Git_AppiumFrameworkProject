package pageObjectPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryPagee {

WebDriver ldriver;
	
	public OrderSummaryPagee(WebDriver rdriver) {   //constructor
	
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);	
		}
	
	//identify the webElements
	
	@FindBy(linkText = "Proceed to checkout")
	WebElement proceed;
	
	//identify the actions on WebElement
	public void clickOnProceedToCheckout() {
		proceed.click();
		
	}

	
	
	
}
