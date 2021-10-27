package test_program.program_files_;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

public class ListBrowsers_ {
	
	public static String pathToDir = System.getProperty("user.dir") + "\\src\\test_program\\ListBrowsers\\";
	
	public static void renewLogFile() {
//		String browsLogDir = System.getProperty("user.dir") + "\\src\\test_program\\program_files_\\ListBrowsers\\";
		File browsLogFile = new File(pathToDir + "browsers_.txt");
		
			try {
				if (browsLogFile.exists()) {
					browsLogFile.delete();
					System.out.println("Browsers list file is DELETED");
				}
				TimeUnit.SECONDS.sleep(1);
				
				browsLogFile.createNewFile();
				System.out.println("Browsers list file is CREATED");
			} catch (InterruptedException | IOException e) {
				System.out.println("Browsers list file FAILED");
				e.printStackTrace();
				System.exit(-1);
			}
	}//eo renewLogFile

	public static void main(String[] args) throws IOException, InterruptedException {
		
		renewLogFile();//Recreates a file for browsers log
		
		String [] batchNm = {"browsers_list.bat", "brows_Lst_.bat"} ;
		
//		ProcessBuilder proBld = new ProcessBuilder("C:\\Users\\user\\eclipse-workspace\\Lesson11_ExtendReports\\"
//				+ "extReportExr\\src\\com\\new_Version\\ListBrowsers\\" + batchNm[0],"firstArg","secondArg");
		ProcessBuilder proBld = new ProcessBuilder(pathToDir + batchNm[0]);
		proBld.directory(new File(pathToDir));
		File browsLog = new File(pathToDir + "browsers_.txt");
		proBld.redirectErrorStream(true);
		proBld.redirectOutput(Redirect.appendTo(browsLog));
		
		Process prCss = proBld.start();
		prCss.waitFor();
		
		System.out.println("================ Batch process is done ================");
		
	}// eomain

}//eoclass
