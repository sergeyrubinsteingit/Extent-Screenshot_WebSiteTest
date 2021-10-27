//There are settings for the Extent Report's HTML output file

package test_program.program_files_;

import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SetHTMLReporter {
		
	public static //builds a new report using the html template 
	ExtentHtmlReporter extnHTMLreporter;
	    
    public static ExtentReports extnReports;	
	public static String targetFilePath = System.getProperty("user.dir") + "\\src\\test_program\\logFile\\ExtentRepLog.html";	
	public static File logFile;
	
//	@Parameters({ "OS", "setBrowser" })
	public static ExtentReports htmlReporter() {

		System.out.print("SetHTMLReporter::::::::::::::::::::::::::::::\n"
				+ "======================= The flag is = " + Start_STR.theFlag + " =======================\n"
						+ "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii\n");
		
		logFile = new File(targetFilePath);
		
		extnReports = null; // Temp
		
			if (extnReports == null) {
				extnHTMLreporter = new ExtentHtmlReporter(targetFilePath);
			}
		
		//instantiates the ExtentReports class and attaches the extnHTMLreporter
		extnReports = new ExtentReports();
		extnReports.attachReporter(extnHTMLreporter);
		
		// Enables an additions of new entries to HTML report file
		extnHTMLreporter.setAppendExisting(true);
		extnHTMLreporter.start();    
		
		//configuration items to change the look and feel
		//add content, manage tests etc
		extnHTMLreporter.config().setChartVisibilityOnOpen(true);
		extnHTMLreporter.config().setDocumentTitle("The Extent Report Log");
		extnHTMLreporter.config().setReportName("Test Report");
		extnHTMLreporter.config().setTestViewChartLocation(ChartLocation.TOP);
		extnHTMLreporter.config().setTheme(Theme.STANDARD);
		extnHTMLreporter.config().setEncoding("utf-8");
		extnHTMLreporter.config().setJS("js-string");
		extnHTMLreporter.config().setProtocol(Protocol.HTTPS);
				
		//To add system or environment info by using the setSystemInfo method.       
		extnReports.setSystemInfo("OS", "Windows");
		extnReports.setSystemInfo("Browser", "Firefox");
		
		return extnReports;

		} //eomethod
		
	} //eoclass
