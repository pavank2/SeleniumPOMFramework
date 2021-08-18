package com.travel.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.travel.qa.base.TestBase;
import com.travel.qa.util.TestUtil;

public class HomePage extends TestBase{
	

	@FindBy(xpath="//td[contains(text(),'User: group automation')]")
	WebElement userTitle;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contacts;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement deals;
	
	@FindBy(xpath="//a[@title='New Contact']")
	WebElement newContact;
	
	@FindBy(xpath="//a[@title='Schedule Call']")
	WebElement scheduleCall;
	
	public HomePage(){

        PageFactory.initElements(driver,this);
    }
   
	
	public String verifyTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickContacts() {
    	//new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(contacts)); 
		
		contacts.click();
		return new ContactsPage();
	}
	
  public DealsPage clickDeals() {
		
		deals.click();
		return new DealsPage();
	}
  
  public ContactsPage clickNewContact() {
	  
	  Actions actions = new Actions(driver);
	  
	  actions.moveToElement(contacts);
	  actions.perform();  //build.perform is only required if you are performing multiple actions
	  
	  newContact.click();
	  return new ContactsPage();
  }
  
  public ScheduleCallPage clickScheduleCall() {
	  scheduleCall.click();
	  return new ScheduleCallPage();
  }
  
}
