// This class checks if the tested page/element looks/acts as expected.
// In this case the string pageData_ contains an array with a title and a URL
// of the visited page. STR method checks if the data is not empty
// and sets theFlag boolean accordingly to true or false.
// Depending on result in the Extent Report the test will be marked as passed or failed. 
// RunTestNG.runTestNg() method fires Extent Report. 
// NetTrafficControl method downloads a file of 1MG to rate
// the current Internet speed.

package test_program.program_files_;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import test_program.Default_StartBrowser;


public class Start_STR extends Thread {
	
	static Object syncObject = VisitLinks.class;
	private static  int strs_count = 0;
	public static boolean theFlag = false;
	public static String testStatus;
	public static String [] getPageData;

	
	public static void STR(final String [] pageData_) throws IOException, InterruptedException {
		getPageData = pageData_;
		WebDriver webDriver = Default_StartBrowser.webDriver;
		if (pageData_.length == 0 || (strs_count%3 != 0 && strs_count < 12)) {
				theFlag = false;
				webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS); // A pause to fill the list
				testStatus = "<::: FAILED :::>\n No page was found";
			} else {
				testStatus = "<::: PASSED :::>\n The page " + pageData_[0].toString() + " was successfully opened.";
				theFlag = true;
			} //eoif
			
			RunTestNG.runTestNg();  // Starts TEST NG //////////////////////////////////////////////////////
		// A pause to fill the list
			webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS);
			TimeUnit.SECONDS.sleep(1);
			strs_count++;
		
		////////////////////////////////  NetTrafficControl  ////////////////////////////////////
		// * Fires a method checking current traffic
		// * and setting timeout accordingly.
				
		if (strs_count%2 == 0) { 
			try {
				NetTrafficControl.CheckTraffic.CheckTrafficRun();
			} catch (InterruptedException e1) {
				System.out.println("Start_STR: Failed NetTrafficControl");
				e1.printStackTrace();
			}
			System.out.println("\n================== NOW RATETOINTERVAL IS " + NetTrafficControl.rateToInterval + " ==================\n");
		}//eoif
		////////////////////////////////  end of NetTrafficControl  ////////////////////////////////////

	} //eofMethodSTR	
} // eo Class
