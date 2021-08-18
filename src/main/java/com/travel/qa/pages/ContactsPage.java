package com.travel.qa.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.travel.qa.base.TestBase;

public class ContactsPage extends TestBase {


	@FindBy(name="cs_company_name")
	WebElement companyToSearch;
	
	@FindBy(xpath="//input[@value='Search']")
	WebElement searchButton;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement surName;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
    @FindBy(xpath="//input[@value='Load From Company']//following-sibling::input[@type='submit' and @value='Save']")
    WebElement savebtn;
	
	
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void searchByCompany(String company) {
		
	//	TestUtil.switchToFrame();
	    companyToSearch.sendKeys(company);
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    searchButton.click();
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	public void selectContacts(String name) {
	    WebElement checkbox = driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input"));
	//	WebElement element = driver.findElement(By.xpath("//a[contains(text(),'microsoft')][1]"));
	//	element.findElement(By.xpath(".//input[@type='checkbox']")).click();
	
	//	WebElement secondCompany = companies.get(2);
		//secondCompany.findElement(By.xpath("//parent::td//following-sibling::td[5]//i")).click();
		checkbox.click();
		
		List<WebElement> rows = driver.findElements(By.xpath("//form[@id='vContactsForm']//tr"));
		int i=0,j=0;
		WebElement row=null;
		for (j=0;j<rows.size();j++) {
			row = rows.get(j);
			if (row.findElement(By.xpath("//a[text()='microsoft']"))!=null) {
				System.out.println("Microsoft");
				i++;
			}	
				if (i == 2) {
					row.findElement(By.xpath(".//td//input[@type='checkbox']")).click();
					return;
				}
		}
	}
	
	public void addNewContact(String title,String fname,String lname,String comp) {
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByValue("Mr.");  //Mr.
		
		
		firstName.sendKeys(fname);
		surName.sendKeys(lname);
		company.sendKeys(comp);
		savebtn.click();
	}
}
