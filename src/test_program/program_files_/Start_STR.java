// The string pageData_ is an array including a title and a URL
// of the visited page. STR method checks if the data is not empty
// and sets theFlag boolean to true or false. According
// to result the Extent Report test will be set as passed or failed. 
// NetTrafficControl method downloads a file of 1MG to rate
// the current Internet speed.

package test_program.program_files_;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

import test_program.Default_StartBrowser;


public class Start_STR extends Thread {
	
	static Object syncObject = VisitLinks.class;
	private static  int cnt2 = 0;
	public static boolean theFlag = false;
	public static String testStatus;
	public static String [] getPageData;

	
	public static void STR(final String [] pageData_) throws IOException, InterruptedException {
		getPageData = pageData_;
		WebDriver webDriver = Default_StartBrowser.webDriver;
		if (pageData_.length == 0 || cnt2 == 1 || cnt2 == 5) {
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
			cnt2++;
		
		////////////////////////////////  NetTrafficControl  ////////////////////////////////////
		// * Fires a method checking current traffic
		// * and setting timeout accordingly.
				
		if (cnt2%2 == 0) { 
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
