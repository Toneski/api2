package manapi;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.listeners.TestJiraListener;

import Jira.JiraPolicy;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class atest {
	@JiraPolicy(logTicketReady=true)
	@Test(priority=1,enabled=true)
	public static void abc() {
		TestJiraListener sendfail = new TestJiraListener();
		String actiontype="";
		File src = new File(".\\src\\main\\java\\config\\DataforAPI.xlsx");
		try {
			FileInputStream fis= new FileInputStream(src);
			XSSFWorkbook xsf = new XSSFWorkbook(fis);
			XSSFSheet sheet =xsf.getSheetAt(0);
			actiontype = sheet.getRow(0).getCell(0).getStringCellValue();
			System.out.println(actiontype);
			xsf.close();
		} catch (IOException e) {
			e.printStackTrace(); sendfail.onTestFailure(e.getMessage());
			
			
			
		}
		
		
		try {
		given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.header("Content-Type","application/json; charset=UTF-8")
		.
		when()
		.get("/"+actiontype+".php")
		.
		then()
		.statusCode(2010)
		.header("Content-Type","application/json; charset=UTF-8")
		
		;
		} catch(NoSuchMethodError e) {
			System.out.println("fail");
			sendfail.onTestFailure(e.getMessage());
		}
		
		
//		<listeners>
//		  <listener class-name="com.listeners.TestJiraListener"/>
//		    </listeners>
	}
		
	
	}

