package com.travel.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.travel.qa.base.TestBase;
import com.travel.qa.pages.ContactsPage;
import com.travel.qa.pages.HomePage;
import com.travel.qa.pages.LoginPage;
import com.travel.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	HomePage homePage;
	LoginPage loginPage;
	ContactsPage contactsPage;
	String sheetName="contacts";
	
	@BeforeClass
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		loginPage.navigateToURL(prop.getProperty("url"));
        homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        TestUtil.switchToFrame();
       // contactsPage = homePage.clickNewContact();
       contactsPage = homePage.clickContacts();

	}
	
	@Test
	public void testSearchCompany() {
		contactsPage.searchByCompany("Google");
		
	}
	
	@Test
	public void testSelectContacts() {
		contactsPage.selectContacts("Apple A");
	}
 
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test (dataProvider="getCRMTestData")
	public void testNewContact(String title,String firstName,String lastName,String company) {
		homePage.clickNewContact();
		contactsPage.addNewContact(title,firstName,lastName,company);
	}
}
