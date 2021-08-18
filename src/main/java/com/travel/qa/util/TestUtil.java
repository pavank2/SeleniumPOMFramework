package com.travel.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.travel.qa.base.TestBase;

public class TestUtil extends TestBase{
	 public static long PAGE_LOAD_TIMEOUT = 20;
	   public static long IMPLICIT_WAIT = 20;
	   public static String TESTDATA_SHEET = "C:\\Users\\PK\\Downloads\\FreeCrmData.xlsx";
	   public static void switchToFrame() {
		   driver.switchTo().frame("mainpanel");
	   }
	   
	  static Workbook book;
	  static Sheet sheet;
	  
	   public static Object[][] getTestData(String sheetName){
		   FileInputStream file = null;
		   try {
			 file= new FileInputStream(TESTDATA_SHEET);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		   try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
           Sheet sheet0 = book.getSheetAt(0);
           
            sheetName = sheet0.getSheetName();
            System.out.println(sheetName);
		   sheet = book.getSheet(sheetName);
		   
		   
		   int rows = sheet.getLastRowNum();
		   int cols = sheet.getRow(0).getLastCellNum();
		   
		   Object[][] data = new Object[rows][cols];
		   
		   for (int i = 0; i < rows; i++) {
			   for (int j=0;j<cols;j++) {
				   data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			   }
		   }
				   
		   return data;
	   }
	   
	public static void takeScreenshotAtEndOfTest() {
	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 String currDir = System.getProperty("user.dir");
	 try {
		FileUtils.copyFile(scrFile, new File(currDir+"\\screenshots\\"+System.currentTimeMillis()+".png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
