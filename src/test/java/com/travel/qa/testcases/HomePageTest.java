package com.travel.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.travel.qa.base.TestBase;
import com.travel.qa.pages.ContactsPage;
import com.travel.qa.pages.DealsPage;
import com.travel.qa.pages.HomePage;
import com.travel.qa.pages.LoginPage;
import com.travel.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	HomePage homePage;
    LoginPage loginPage;
    DealsPage dealsPage;
    ContactsPage contactsPage;
    
    
    public HomePageTest() {
    	super();
    }
	
	@BeforeClass
     public void SetUp() {
		initialize();
		loginPage = new LoginPage();
		loginPage.navigateToURL(prop.getProperty("url"));
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		TestUtil.switchToFrame();
	}
	
	@Test
	public void testTitle() {
		
		String title = homePage.verifyTitle();
		assertEquals(title, "CRMPRO");
		
	}
	
	@Test
	public void testContacts() {
		
		
	//	contactsPage = homePage.clickContacts();
		homePage.clickNewContact();
		
	}
	
	
	
//	@Test
//	public void testDeals() {
//		dealsPage = homePage.clickDeals();
//		
//	}
}
