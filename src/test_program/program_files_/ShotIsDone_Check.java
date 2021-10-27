// The VisitLinks.java class uses this class
// to check if the page shot was done or attempted during 100 ms:
// screen shot marks the end of the page testing.
// The VisitLinks.java stops running till the page test is done
// or attempted; then resumes the test on the next page.

package test_program.program_files_;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ShotIsDone_Check {
	
	public static boolean testFlag_;
	public static int waitCounter = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		String shotsPath = ScreenShoter.pathToFl + VisitLinks.linksCounter + ".png";
		File controlFl = new File(shotsPath.trim());
		String controlFlPath = controlFl.toString();

		for (int i = 0; i < 100; i++) {
			TimeUnit.SECONDS.sleep(1);
			
			if (controlFl.exists()) {
				testFlag_ = true;
				// For console check:
				System.out.println(
						"--- ShotIsDone_Check.java ---\n((((((((((((((((((( File exists: " + controlFlPath + ")))))))))))))))))))");
				break;
			} else {
				testFlag_ = false;
				waitCounter++;
				// For console check:
				System.out.println(
						"((((((((((((((((((( ShotIsDone_Check.java is running for " + waitCounter + " sec. )))))))))))))))))))");
				System.out.println("((((((((((((((((((( currentTime => " + java.time.LocalTime.now()
						+ ")))))))))))))))))))");
			}
			if (i >= 100) {
				System.out.println("[ShotIsDone_Check.java]\n Failed to create a shot: " + controlFlPath);
			}
		} //eofor
		
	}//eomain
}//eoclass
