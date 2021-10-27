//This class visits all the links
//present in drop menus of the navigation bar of the site.
//After opening the page it runs in it the
//Start_STR class which checks if the elements tested
// looks or acts as expected.

package test_program.program_files_;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import test_program.Default_StartBrowser;

public class VisitLinks extends Thread{
	
	public static WebDriver webDriver;
	public static FluentWait<WebDriver> waitForElement;
	public static List<WebElement> tempArray;
	public static int linksCounter;
	public static WebElement navBarElem;
	public static String dropMnLink = new String();
	public static String pageTitle = new String();
	public static String pageUrl;
	public static ArrayList<String> allURLs = new ArrayList<>();
	public static String lastOfDrop;
	public static int lastOfNavbar;
	
	public static String[] pageTitleAndUrl;
	public static String [] dropEntries;
	public static String currntLnk = "";
	public static final Object muTex_ = new Object();
//	public static int waitCounter = 0;

	
public static class CreateAndVisitLinks {

	public static void visitLinks(List<String> navBarLnkCln, boolean readyStateComplete)  
			throws InterruptedException, IOException {
		
		webDriver = Default_StartBrowser.webDriver;

//		Calls a dialog window informing tester the test is running
		TimeUnit.SECONDS.sleep((long)0.2);
		WaitWindow_.main(pageTitleAndUrl);
		
			// Here WebDriverWait begins 
			System.out.println("+++++++++++ Here WebDriverWait begins +++++++++++\n");
			// FluentWait option
			waitForElement = new FluentWait<WebDriver>(webDriver).withTimeout(120, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			tempArray = new ArrayList<>(); // Array to copy drop menu links in, temporarily.		
			Actions acT_ = new Actions(webDriver);
			linksCounter = 0;
			int cnt3_ = 0;
			int cnt4_ = 0;
			int cnt5_ = 0;
			int cnt6_ = 0;
			JavascriptExecutor exectJS = (JavascriptExecutor) webDriver;
			if (!readyStateComplete) {
				System.err.println("============= !readyStateComplete. Usjo pohano... ================\n");
				try {
					readyStateComplete = ((String) exectJS.executeScript("return document.readyState"))
							.equals("complete");
				} catch (Exception e) {
					readyStateComplete = ((String) exectJS.executeScript("return document.readyState"))
							.equals("complete");
					TimeUnit.SECONDS.sleep(1);
					cnt4_++;
					if (cnt4_ > 5) {
						System.err.println("Ready state check failed\n*****************\n\n");
						cnt4_ = 0;
						e.printStackTrace();
						webDriver.quit();
						System.exit(-1);
					} //EOIf 2
				} //EOTry	
			} // eoif

			
			for (String navBarLink : navBarLnkCln) {

				// Cursor moves to the given position on navigation bar
				try {
					waitForElement.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(navBarLink)));
					navBarElem = webDriver.findElement(By.partialLinkText(navBarLink));
				} catch (NoSuchElementException e1) {
					pageTitleAndUrl = new String[] {};
					Start_STR.STR(pageTitleAndUrl);
					e1.printStackTrace();
					continue;
				}
				webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list
//				TimeUnit.SECONDS.sleep(2);
				System.out.println("++++++++++++++++ [For loop 1] navBarElem is :::>> " + navBarElem + "\n");

				acT_.moveToElement(navBarElem).build().perform();
				webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list
//				TimeUnit.SECONDS.sleep(2);

				System.out.println("navBarLnkCln: " + navBarLnkCln + "\n");

				List<WebElement> dropLinks = webDriver.findElements(By.className("dropdown-menu")).get(cnt6_)
						.findElements(By.tagName("a"));
				dropEntries = new String[dropLinks.size()];
				for (WebElement getLtext : dropLinks) {
					System.out.println("Test of droplinks:: >> " + getLtext.getAttribute("innerHTML"));
					if (getLtext.getAttribute("innerHTML") != "" && getLtext.getAttribute("innerHTML") != null) {
						try {
							dropEntries[cnt5_] = getLtext.getAttribute("innerHTML").trim().toString();
						} catch (ArrayIndexOutOfBoundsException e) {
							for (int i = 0; i < navBarLnkCln.size() - 1; i++) {
								dropEntries[cnt5_] = getLtext.getAttribute("innerHTML").trim().toString();
								if (i >= navBarLnkCln.size() - 1) {
									System.out.println("Drop menu list's population failed.");
									e.printStackTrace();
								} //EOIF
							} //EOFor
						} //EOTry
						cnt5_++;
					} //EOIf
				} //EOFor
				cnt6_++;
				cnt5_ = 0;

				webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list
//				TimeUnit.SECONDS.sleep(2);

				for (final String currntLnk__ : dropEntries) { // Search loop for a given link in the drop menu

					System.out.println(
							"+++++++++++ Here goThrougLinks: [For loop searching the drop menu begins] begins +++++++++++\n Element dropMenuItem now is ::: "
									+ currntLnk__ + "\n");
					System.out.println("+++++++++++ linksCounter = " + linksCounter + " +++++++++++\n");
					System.out.println("++++++++++++++++ [For loop 2] navBarElem is :::>> " + navBarElem + "\n");
					System.out.println("++++++++++++++++ [For loop 2] dropMnLink is :::>> " + dropMnLink + "\n");
					
					try {
						waitForElement.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(navBarLink)));
					} catch (Exception e) {
						System.out.println("Failed attempt to localize: " + navBarLink + ". Test is shut down.");
						e.printStackTrace();
						webDriver.quit();
						System.exit(-1);
					}
					webDriver.findElement(By.partialLinkText(currntLnk__)).click();
					// Two variables to use in Start_STR class
					pageTitle = webDriver.getTitle().toString().toUpperCase();
					pageUrl = webDriver.getCurrentUrl().toString();
					pageTitleAndUrl = new String[] { pageTitle, pageUrl };
					
//					Creates a list of page's URL used in AddShotsToHTML
					allURLs.add(pageUrl);
					
//					Calls a dialog window informing tester the test is running
					TimeUnit.SECONDS.sleep((long)0.1);
					WaitLabel.secondsCounter = 0;
					WaitLabel.testNmb = linksCounter + 1;
					WaitLabel.pageName = pageTitle;
					
					System.out.println("VisitLinks: Current URL is:::>> " + webDriver.getCurrentUrl().toString());
					System.out.println("VisitLinks: Page title: " + pageTitle);

					String shotsPath = ScreenShoter.pathToFl + linksCounter + ".png";
					File controlFl = new File(shotsPath.trim());

					ScreenShotsAndExtReports.reportOperations(currntLnk__);
					ShotIsDone_Check.main(null);
					
						for (int i = 0; i < 100; i++) {
							TimeUnit.SECONDS.sleep(1);
							if (!ShotIsDone_Check.testFlag_) {
								System.out.println("^&^&^&^&^&^&^&^& VisLinks is SUSPENDED ^&^&^&^&^&^&^&^&");
								System.out.println("TestCaseToExtentReport.vlinksFlg_TestCase = "
										+ TestCaseToExtentReport.vlinksFlg_TestCase);
								}
//							} //eowhile
						else if (ShotIsDone_Check.testFlag_) {
							System.out.println("**** ShotIsDone_Check.testFlag_ is now " + ShotIsDone_Check.testFlag_ + "****");
							break;}
						}// eofor
						System.out.println("\n\n#################### VisLinks is RESUMED ####################\n");

					try {
						waitForElement.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(navBarLink)));
					} catch (Exception e) {
						System.out.println("Failed attempt to localize: " + navBarLink + ". Test is shut down.");
						e.printStackTrace();
						webDriver.quit();
						System.exit(-1);
					}
					navBarElem = webDriver.findElement(By.partialLinkText(navBarLink));
					webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS); // A pause to fill the list
//					TimeUnit.SECONDS.sleep(1);

					acT_.moveToElement(navBarElem).build().perform();
					webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list
//					TimeUnit.SECONDS.sleep(2);

					System.out.println("((((( End of Loop 2, session " + linksCounter + " )))))");

					linksCounter++;
				} // EOFor
				cnt3_++;

				lastOfNavbar = navBarLnkCln.size();
				lastOfDrop = dropEntries[dropEntries.length - 1];

				System.out.println("<<< lastOfNavbar size = " + lastOfNavbar + " >>>/n<<< cnt3_ = " + cnt3_ + " >>>");

				if (cnt3_ + 1 > lastOfNavbar && lastOfDrop != null) {
					QuitTest.quitTest();
					webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list
//					TimeUnit.SECONDS.sleep(2);
					System.exit(1);
				}
			} // EOFor
} //EOVisitLinks
	
	} //eoclass CreateAndVisitLinks
	
	
	public static class ScreenShotsAndExtReports extends Thread {
		
		public static void reportOperations(String currntLnk_) {

					try {
						Start_STR.STR(pageTitleAndUrl);
						System.out.println("::::: VisitLinks - reportOperations() ::: ");
						TimeUnit.SECONDS.sleep((long)0.5);
						ScreenShoter.pageShot(pageTitle, linksCounter);
					} catch (IOException | InterruptedException | InvocationTargetException e) {
						System.out.println("##### Failure: Cannot fire Start_STR.STR on link: " + currntLnk_ + " #####");
						e.printStackTrace();
					}//eotry		
		}// eometh reportOperations
	}  // eoclass ScreenShotsAndExtReports
}//eomainclass
