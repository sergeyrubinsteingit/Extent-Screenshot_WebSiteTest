//Gets a browser name selected by user to test the site on.
//Opens the site, deletes the cookies from the system
//to avoid test failure.

package test_program;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import test_program.program_files_.Combo_Box;
import test_program.program_files_.CreateHTML_logFile;
import test_program.program_files_.CreateLinkList;
import test_program.program_files_.ListBrowsers_;
import test_program.program_files_.MakeBrowsersList;
import test_program.program_files_.NetTrafficControl;
import test_program.program_files_.RecreateShotsDir;
import test_program.program_files_.SetHTMLReporter;


public abstract class Default_StartBrowser {
	
	public static WebDriver webDriver;
	public static String driverType;
	private static String driverPath = System.getProperty("user.dir") + "\\src\\test_program\\web_drivers_\\";
	private static String comboTitle;
	private static String comboTarget;

	
	@SuppressWarnings("deprecation")
	public static void startBrowser() throws InterruptedException {
		
		SetHTMLReporter.htmlReporter(); // Creates an HTML file for Extent Report
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("\n=+=+=+=+=+=+=+=+=+=+= THIS CODE GETS LINK NAMES FROM NAVIGATION BAR =+=+=+=+=+=+=+=+=+=+=\n");
		System.out.println("=+=+=+=+=+=+=+=+=+=+=> " + Combo_Box.selectedBrowser + " <=+=+=+=+=+=+=+=+=+=+=\n");
		TimeUnit.SECONDS.sleep(1);
		if (Combo_Box.selectedBrowser.matches("Firefox")) { // FIREFOX driver
			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
			DesiredCapabilities capabilities;
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			capabilities.setCapability("marionette", true);
			webDriver = new FirefoxDriver(capabilities);
		} else if (Combo_Box.selectedBrowser.matches("Chrome")) { // CHROME driver
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
			webDriver = new ChromeDriver();
		} else if(Combo_Box.selectedBrowser.matches("MS Edge")) { // EDGE driver
			System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
			webDriver = new EdgeDriver();
		} else if(Combo_Box.selectedBrowser.matches("Internet Explorer")) { // EXPLORER driver
			System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer_x64_\\IEDriverServer.exe");
			InternetExplorerOptions ie_options = new InternetExplorerOptions().enableNativeEvents().ignoreZoomSettings();
			webDriver = new InternetExplorerDriver(ie_options);
		} else if(Combo_Box.selectedBrowser.matches("Opera")) { // OPERA driver
			System.setProperty("webdriver.opera.driver", driverPath + "operadriver.exe");
			webDriver = new OperaDriver();
		} else if(Combo_Box.selectedBrowser.matches("Safari")) { // SAFARI driver. 
//////////	Needs to be enabled in Safari browser. Do the following: ////////////
//			Go to Safari -> Preferences-> Advanced
//			Tick mark the Checkbox with the label – Show Develop menu in menu bar
//////////	Once done, go to the Develop menu and click on the Allow Remote Automation option to enable it.
			webDriver = new SafariDriver();
		} else {
			System.out.println("\n---------------- Failure: Web driver was not found ------------------");
			System.exit(-1);
		}
		
		try { // Deletes a folder with former screenshots and recreates an empty folder
			
	        webDriver.manage().window().maximize();
	        webDriver.manage().deleteAllCookies();
			
			webDriver.navigate().to("http://edu.gov.il/owlHeb/Pages/default.aspx");
			webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS); // A pause to fill the list
						
			RecreateShotsDir.recreateFolder();
			CreateHTML_logFile.createHTML_log();
		} catch (IOException | NullPointerException ioe_nulle) {
			System.out.println("Default_StartBrowser:: Failed to recreate \\com.new_Version\\screen_shots\\ directory.");
			webDriver.quit();
			System.exit(-1);
			ioe_nulle.printStackTrace();
		} catch (TimeoutException toe) {
			System.out.println("<<<<< Selected webdriver failed to open the site in " 
					+ Combo_Box.selectedBrowser.toString()
					+ ". The system was shut down. >>>>>\n");
			webDriver.quit();
			System.exit(-1);
			toe.printStackTrace();
		}
		CreateLinkList.createlinkLst();
	}	// EOF

	public static void main(String [] args) throws InterruptedException {
		
		try {
			ListBrowsers_.main(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		MakeBrowsersList.main(args);
		
		TimeUnit.SECONDS.sleep(1);
				
		comboTarget = "browser you want to run a test on";
		
		setComboTitle("<html><body>"
				+ "<font size='15px' family='Helvetica, sans-serif'>"
				+ "<i>Please choose the " + comboTarget + ".</i>"
				+ "</font></body></html>");
		
		Combo_Box.main(getComboTitle(), MakeBrowsersList.listStringArray);
		
		while (Combo_Box.timeFlag != true) {
			for (int i = 0; i < 1000; i++) {
				
					TimeUnit.SECONDS.sleep(1);
					i++;
					if (Combo_Box.timeFlag == true) {
						startBrowser();
						break;
					}
					else if (i >= 1000) {
						System.exit(-1);
						System.out.println("FAILED!!! Time is out.");
					}/// eo if1
				}/// eo while
			}/// eo for
		
	} //eomain

	public static String getComboTitle() {
		return comboTitle;
	} //eogetter

	public static void setComboTitle(String comboTitle) {
		Default_StartBrowser.comboTitle = comboTitle;
	} //eosetter

}// eoclass
