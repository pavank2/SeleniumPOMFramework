package com.travel.qa.base;
import com.travel.qa.util.TestUtil;
import com.travel.qa.util.WebEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestBase {

	  public static WebDriver driver;
	    public static Properties prop;
	    public static EventFiringWebDriver e_driver;
	    public static WebEventListener listener;

	    public TestBase() {
	        try {

	            prop = new Properties();
	            FileInputStream fis = new FileInputStream("C:\\Users\\PK\\eclipse-workspace\\Selenium\\src\\main\\"+
	            		"java\\com\\travel\\qa\\conf\\config.properties");
	            prop.load(fis);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }


	    }

	    public static void initialize(){

	        String browser = prop.getProperty("browser");
	        System.out.println(browser);
	        if(browser.equals("chrome")){
	            System.setProperty("webdriver.chrome.driver","C:\\Users\\PK\\Downloads\\chromedriver.exe");
	            driver = new ChromeDriver();
	        } else {
	            System.setProperty("webdriver.gecko.driver","C:\\Users\\PK\\Downloads\\geckodriver.exe");
	            driver = new FirefoxDriver();
	        }

	        
	        e_driver = new EventFiringWebDriver(driver);
	        listener = new WebEventListener();
	        e_driver.register(listener);
	        driver = e_driver;
	        
	        
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
   }
}
	    
