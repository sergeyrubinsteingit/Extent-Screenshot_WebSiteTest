//Builds a list of links from the drop menu.
//Cleans the list of nulls and passes the
//output to VisitLinks class

package test_program.program_files_;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test_program.Default_StartBrowser;


public class CreateLinkList  {

	public static WebDriver webDriver;
	static boolean readyStateComplete;
	public static List<String> navBarLnkCln;

	
	public static List<String> createlinkLst() throws InterruptedException { // Reads link names from the web site screen
		
		webDriver = Default_StartBrowser.webDriver;
		List<WebElement> navBarLinks = new ArrayList<>();	// List to collect navigation bar links in.
		webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list
		navBarLinks = webDriver.findElement(By.tagName("ul")).findElements(By.tagName("li"));	//	writes navigation bar links in the list navBarLinks 
//		TimeUnit.SECONDS.sleep(1);
		webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS); // A pause to fill the list
		//Below: String array to copy 'navBarLinks' in for further cleaning of Null elements
		String [] navBarLnkString = new String[navBarLinks.size()];
		// Below: New no-size list to copy a data from 'navBarLnkString' after cleaning of Nulls
		List<String> navBarLnkCln = new ArrayList<String>();
		int cnt_ = 0;
		// Filling "navBarLnkString"; still Nulls in
		for (WebElement wbLnk : navBarLinks) {
			if (!wbLnk.getText().isEmpty() && wbLnk.getText() != null) {
				navBarLnkString[cnt_] = wbLnk.getText();	
			}
			cnt_++;
		} // eofor
		
		// Filling "navBarLnkCln"; no Nulls
		for (String txtOnly : navBarLnkString) {
			if (txtOnly != null) {
				navBarLnkCln.add(txtOnly);
			} // eoif
		} // eofor

		try {
			VisitLinks.CreateAndVisitLinks.visitLinks(navBarLnkCln, readyStateComplete);
		} catch (InterruptedException | IOException e) {
			System.out.println("Failure: On CreateLinksList cannot fire [ VisitLinks.visitLinks ]");
			e.printStackTrace();
		}// eotry
		
		return navBarLnkCln;		
	}	// EOF

}
