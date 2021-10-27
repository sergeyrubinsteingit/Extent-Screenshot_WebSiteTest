package test_program.program_files_;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test_program.Default_StartBrowser;


public class QuitTest {
	
	public static WebDriver webDriver;
//	final static WaitWindow_ wWin_ = new WaitWindow_();

	public static void quitTest() throws IOException, InterruptedException {
		
		webDriver = Default_StartBrowser.webDriver;
		
	// Closing the page
		final WaitWindow_ wWin_ = new WaitWindow_();
		wWin_.dispose();
		webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS);
//			TimeUnit.SECONDS.sleep((long) 0.5);
		AddShotsToHTML.main(null);
		System.out.println("<<<<<< The test is finished >>>>>>");
		
	// Opens HTML log files in the new tabs
		
		ArrayList<String> logFileNames = new ArrayList<>();
		File logFileDir = new File(System.getProperty("user.dir") + "\\src\\test_program\\logFile\\");
		
			for (final File logFl : logFileDir.listFiles()) {
				if (!logFl.isDirectory()) {
					logFileNames.add(logFl.getName().toString());
				}//eoif
			}//eofor

			for (int i = 0; i < logFileNames.size(); i++) {
				TimeUnit.SECONDS.sleep(1);
				((JavascriptExecutor) webDriver).executeScript("window.open()");
				(new WebDriverWait(webDriver, 30)).until(ExpectedConditions.numberOfWindowsToBe(i+2));
				ArrayList<String> tabsList = new ArrayList<>(webDriver.getWindowHandles());
				webDriver.switchTo().window(tabsList.get(i+1));
				webDriver.navigate().to(System.getProperty("user.dir") + "\\src\\test_program\\logFile\\" + logFileNames.get(i).toString());
			}//eofor
		//			webDriver.quit();
		System.exit(0);
		} //eofMethQuitTest
			
}
