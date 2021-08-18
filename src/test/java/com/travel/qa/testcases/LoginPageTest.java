package com.travel.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.travel.qa.base.TestBase;
import com.travel.qa.pages.HomePage;
import com.travel.qa.pages.LoginPage;

import java.io.IOException;
import java.util.Properties;

public class LoginPageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;

    LoginPageTest() throws IOException {
        super();
    }

    @BeforeMethod
    public void setup(){
        initialize();
        loginPage = new LoginPage();
    }

    @Test
    public void testLogin(){
    	loginPage.navigateToURL(prop.getProperty("url"));
        homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
     

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }




}
