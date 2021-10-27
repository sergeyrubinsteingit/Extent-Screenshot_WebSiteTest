//This method initiates TestNG library
//and starts testing process running file testng.xml.
//Testng.xml sets testing parameters.
//List testSuites can be useful in case
//of multiply .xml files applied.

package test_program.program_files_;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import com.beust.jcommander.internal.Lists;

import test_program.Default_StartBrowser;


public class RunTestNG {
	
	public static WebDriver webDriver;
	
	public static void runTestNg() throws InterruptedException {
		
		webDriver = Default_StartBrowser.webDriver;
		
		TestNG tNg = new TestNG();
		List<String> testSuites = Lists.newArrayList();
		testSuites.add(System.getProperty("user.dir") + "\\src\\test_program\\program_files_\\testng.xml");
		webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS);
		
		tNg.setTestSuites(testSuites);
		tNg.run();
	} // eomth runTestNg
} //eoclass
