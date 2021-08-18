package com.travel.qa.pages;

import com.travel.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestBase{

    @FindBy(name="username")
    WebElement username;

    @FindBy(name="password")
    WebElement password;

    @FindBy(xpath="//input[@type='submit']")
    WebElement login;

    //Initialize
    public LoginPage(){

        PageFactory.initElements(driver,this);
    }
   
    //Actions
    
    public void navigateToURL(String url) {
    	driver.get(url);
    	
    	
    }
    public HomePage login(String user, String pass){
    	new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(username)); 
        username.sendKeys(user);
        password.sendKeys(pass);
        login.click();
        return new HomePage();
    }

}

