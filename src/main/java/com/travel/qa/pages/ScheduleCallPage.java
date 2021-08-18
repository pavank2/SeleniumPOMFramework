package com.travel.qa.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.travel.qa.base.TestBase;

public class ScheduleCallPage extends TestBase {
	
	@FindBy(name="reminder_type")
	WebElement reminderType;
	
	@FindBy(name="reminder_note")
	WebElement reminderNote;

	@FindBy(xpath="//input[@name='contact_lookup']//following-sibling::input[@type='button']")
	WebElement contactLookUp;
	
	@FindBy(name="flag")
	WebElement flagAs;
	
	@FindBy(xpath="//img[@id='f_trigger_c_start_time']")
	WebElement startDateSelector;
	
	@FindBy(id="search")
	WebElement searchBox;
	
	@FindBy(name="save")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@value='Search']")
	WebElement searchButton;
	
	public ScheduleCallPage() {
		PageFactory.initElements(driver, this);
	}
	public void selectDate(String date) {
		
		List<WebElement> dates = driver.findElements(By.xpath("//div[@class='calendar'][9]//td"));
		
		for (WebElement dt: dates) {
			String dateText = dt.getText();
			
			if (dateText.equalsIgnoreCase(date)) {
				dt.click();
			    break;
			}
			
		}
	}
	
	public void selectinChildWindow() {
		String parent = driver.getWindowHandle();
		System.out.println(parent);
		Set<String> children = driver.getWindowHandles();
		
		Iterator<String> it = children.iterator();
		
		while(it.hasNext()) {
			String currentChildWindow = it.next();
			if (!parent.equals(currentChildWindow)) {
				driver.switchTo().window(currentChildWindow);
				break;
			}
		}
		
		
		searchBox.sendKeys("Joe");
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchButton.click();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String contactName=null;
		List<WebElement> items = driver.findElements(By.xpath("//table//td//a"));
		System.out.println(items.size());
		for (WebElement item:items) {
			contactName = item.getText();
			System.out.println(contactName);
			if (contactName.contains("joe")) {
				item.click();
				System.out.println("Clicked");
				break;
			}
		}
		
		//driver.close();
		driver.switchTo().window(parent);
		
		System.out.println(driver.getWindowHandle());
		driver.switchTo().frame("mainpanel");
	}
	public void selectDropDown(WebElement dropdown,String value) {
		Select select = new Select(dropdown);
		select.selectByValue(value);
	}
	public void scheduleNewCall(String date) {
		
		selectDropDown(reminderType,"S");
		reminderNote.sendKeys("Note");
		contactLookUp.click();
		selectinChildWindow();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='f_trigger_c_start_time']")));
		startDateSelector.click();
		selectDate(date);
		selectDropDown(flagAs,"Busy");
		saveBtn.click();
		
		
	}
	
	
}

