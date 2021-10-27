package test_program.program_files_;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import test_program.Default_StartBrowser;


public class CreateHTML_logFile {
	
	public static WebDriver webDriver;
	
	public static void createHTML_log() {
		
	webDriver = Default_StartBrowser.webDriver;
	
	String logDir = (System.getProperty("user.dir") + "\\src\\test_program\\logFile\\");
	String [] html_fl_name_ = {"ExtentRepLog.html", "ScreenShotsRep.html"};
	
	try {
		System.out.println("Files in the logDir directory:\n");
		for ( Path theFilePath : Files.newDirectoryStream(Paths.get(logDir)) ) {
			theFilePath.normalize();
			System.out.println(theFilePath.getFileName());
		}
	} catch (IOException e1) {
		System.out.println("Failed to read the logDir files: " + e1.getMessage());
	}
			
	for (int i = 0; i < html_fl_name_.length; i++) {
		//Creates a log file		
		File logFile = new File(logDir + html_fl_name_[i]);
		if (logFile.exists()) {
			logFile.delete();
			System.out.println("Log file DELETED");
		}
		try {
			logFile.createNewFile();
			System.out.println("Log file CREATED");
			webDriver.manage().timeouts().implicitlyWait(((int) NetTrafficControl.rateToInterval * 1),
					TimeUnit.SECONDS);
		} catch (IOException e) {
			System.out.println("Failed on creating of a new file");
			e.printStackTrace();
			System.exit(-1);
		} //eotry
	}//eofor
}//eomethod

}//eoclass
