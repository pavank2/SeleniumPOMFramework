package com.travel.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.travel.qa.base.TestBase;
import com.travel.qa.pages.HomePage;
import com.travel.qa.pages.LoginPage;
import com.travel.qa.pages.ScheduleCallPage;
import com.travel.qa.util.TestUtil;

public class ScheduleCallTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	ScheduleCallPage sp;
	
	@BeforeClass
	public void SetUp() {
		initialize();
		loginPage = new LoginPage();
		loginPage.navigateToURL(prop.getProperty("url"));
        homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        TestUtil.switchToFrame();
        sp = homePage.clickScheduleCall();
        
	}
	
	@Test
	public void testScheduleCall() {
		
		sp.scheduleNewCall("14");
	}
	
}
