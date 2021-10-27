// Re-creates test_program/screen_shots directory, a storage of  
// screen shots made while testing the site.
// First deletes all the files inside the directory.
// Then creates directory of the same name anew.

package test_program.program_files_;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

import test_program.Default_StartBrowser;


public class RecreateShotsDir {
	
	public static WebDriver webDriver = Default_StartBrowser.webDriver;
	private static String dirPath = System.getProperty("user.dir") + "\\src\\test_program\\screen_shots\\";
	
	public static void recreateFolder() throws IOException, InterruptedException {
		
		String filesToDelete = dirPath;
		String shotsDir = dirPath;
		File fl_ = new File(filesToDelete);
		File dir_ = new File(shotsDir);
		System.out.println("filesToDelete::" + filesToDelete);
		
		try {
			webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list

			for(File file: fl_.listFiles()) {
				if (!file.isDirectory() && file != null) {
					System.out.println("Testing file.delete(). The file deleted is ::: " + file.getName());
					file.delete();
				} else {
					System.out.println("Testing file.delete() break");
					break;
				}//eoif 
			}//eofor
			
			webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list

			for(File dir: dir_.listFiles()) { 
				if (dir.isDirectory()) {
					System.out.println("Testing dir.delete()");
					dir.delete();
				}//eoif 
			}//eofor
			
			webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 2 ), TimeUnit.SECONDS); // A pause to fill the list

			File shotsFolder = new File(shotsDir);
			shotsFolder.mkdir();
			System.out.println("Testing shotsFolder.mkdir()");
		} 
		catch (SecurityException secexp) {
			secexp.printStackTrace();
		}//eotry

	}//eo deleteFolder

}//eoclass
