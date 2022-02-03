package TestCases1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import CommonUtil.TestBrowser;
import ExcelUtil.ExcelApiTest4;
import Pages1.*;

public class Verify_Addjobs {
WebDriver driver;
	
	
	Map<String, HashMap<String, String>> Datatable = new HashMap<String, HashMap<String, String>>();

	ExtentTest logger;
	 ExtentReports extent;
	 String screenShotPath;
	 public static String TestScriptName;
	 public static String TestName;
	
	 @BeforeTest 
		public void  TestSetup()throws Exception {
			
			driver = TestBrowser.OpenChromeBrowser();
			String TestURL = "https://opensource-demo.orangehrmlive.com/";
			driver.get(TestURL);
			
			ExcelApiTest4 eat = new ExcelApiTest4();
			Datatable=eat.getDataTable("C://HTML Report//OrangeHRM6//TC01_Addjobs.xlsx", "Sheet1");
			
			TestScriptName=(Datatable.get("TC03").get("TestName"));
			
					
			//Extent HTML Report Creation Starts
			 SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy_MMM_dd_h_mm_ss_SSS_a");
			 Date now = new Date();
			 String strDate = sdfDate.format(now);
			 
			 TestName=TestScriptName+"_"+strDate+".html";
			 TestScriptName=TestScriptName+"_"+strDate;
			 String TestHtmlName="C:/HTML Report/test-output/ExtentReportScreenShots/"+ TestScriptName +"/"+TestName;
			  
		
			 ExtentHtmlReporter reporter=new ExtentHtmlReporter(TestHtmlName);
			 System.out.println("Html Report path is : "+TestHtmlName);
			 
			 extent=new ExtentReports();
			 extent.attachReporter(reporter);
			 logger=extent.createTest(TestName);
			//Extent HTML Report Creation Ends
		}
	
	
  @Test
  public void VeriyAddjobs() throws Exception{
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	    String UserName1= Datatable.get("TC03").get("UserName1");
		String Password1= Datatable.get("TC03").get("Password1");
		String jobtitle= Datatable.get("TC03").get("jobtitle");
		String jobDescription = Datatable.get("TC03").get("jobDescription");
		String jobNote = Datatable.get("TC03").get("jobNote");
		
	  
		Login L2 = new Login();
		L2.Login(driver,TestScriptName,logger,extent);
		L2.Loginpage(UserName1,Password1);
		
		Addjobs A1= new Addjobs();
		A1.Addjobs(driver,TestScriptName,logger,extent);
		A1.Addjobspage(jobtitle,jobDescription,jobNote);
		
		Homepage H2= new Homepage();
		H2.Homepage(driver,TestScriptName,logger,extent);
		H2.Logout();
	}
	
	 
  @AfterTest 
	public void  TestCloser()throws Exception {	
		driver.quit();
		extent.flush();
	  
  }
}
