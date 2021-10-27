//This class runs the test
//and arranges the data to be displayed
//in the HTML file of Extent Report

package test_program.program_files_;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import test_program.Default_StartBrowser;


public class TestCaseToExtentReport extends org.testng.TestListenerAdapter implements org.testng.IInvokedMethodListener
 {
    //helps to generate the logs in extnTest report.
    public static ExtentTest  extnTest;    
    public static ExtentTest  extnTestNode;            
    public static ITestResult testingResult;    
	public static WebDriver webDriver = Default_StartBrowser.webDriver;
	public static boolean vlinksFlg_TestCase = true;

	
    @Test(alwaysRun = true)
    public static void testingCase() throws IOException {
    	
    	vlinksFlg_TestCase = false;
    	
    	boolean theFlag = Start_STR.theFlag;

    // Creates a test entry in HTML report file [ExtentRepLog.html]
    	extnTest = SetHTMLReporter.extnReports.createTest("Test Case Nmb " + VisitLinks.linksCounter, 
    			"::: PAGE'S TITLE: " + Start_STR.getPageData[0] + " :::");
    	webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    // Adds a node to the test entry	
    	extnTestNode = extnTest.createNode("A Node for Test Case Nmb " + VisitLinks.linksCounter, 
    			"::: PAGE'S URL: " + Start_STR.getPageData[1] + " :::");
    	webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    	
			if (theFlag == false) {
				Assert.assertFalse(theFlag, "@@ ASSERT FALSE test block @@");
				extnTest.log(Status.FAIL, "///////////////// Test FAILED /////////////////");
			} else {
				Assert.assertTrue(theFlag, "$$ ASSERT TRUE test block $$");
				extnTest.log(Status.PASS, "///////////////// Test PASSED /////////////////");
			}
    } // eomth testingCase

    @AfterMethod
    public static  void getResult(ITestResult testingResult) throws IOException {
    	
    	System.out.println("############## The testingResult is " + testingResult.toString() + " ################");
    	
    	try {
			if(testingResult.getStatus() == ITestResult.FAILURE) {
			    extnTest.log(Status.FAIL, MarkupHelper.createLabel(testingResult.getName()+" FAILED ", ExtentColor.RED));
			    extnTestNode.log(Status.FAIL, MarkupHelper.createLabel(testingResult.getName()+" FAILED ", ExtentColor.RED));
//            TestCase2.extnTest.fail(testingResult.getThrowable());
			    System.out.println("\ngetResult(): part Failed");
			}
			else if(testingResult.getStatus() == ITestResult.SUCCESS) {
			    extnTest.log(Status.PASS, MarkupHelper.createLabel(testingResult.getName()+" PASSED ", ExtentColor.GREEN));
			    extnTestNode.log(Status.PASS, MarkupHelper.createLabel(testingResult.getName()+" PASSED ", ExtentColor.GREEN));
			    System.out.println("\ngetResult(): part Succeded");
			}
			else {
			    extnTest.log(Status.SKIP, MarkupHelper.createLabel(testingResult.getName()+" SKIPPED++ ", ExtentColor.ORANGE));
			    extnTestNode.log(Status.SKIP, MarkupHelper.createLabel(testingResult.getName()+" SKIPPED++ ", ExtentColor.ORANGE));
			    extnTest.skip(testingResult.getThrowable());
			    System.out.println("\ngetResult(): part Skipped");
			}
		} catch (Throwable trrr) {
			extnTest.log(Status.ERROR, trrr.getMessage());
			System.err.println("Error in TestCase : getResult" + trrr.getMessage() + "\n=======================================");
		}
    }

	@AfterClass
    public static void tearDown() throws IOException {
				
		//to write or update Test information to reporter
		SetHTMLReporter.extnReports.flush();
		vlinksFlg_TestCase = true;
		System.out.println("\n000000000000000   TearDown's running   000000000000000\n");
    }
}