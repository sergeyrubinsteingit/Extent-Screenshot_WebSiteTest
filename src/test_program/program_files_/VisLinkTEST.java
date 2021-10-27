package test_program.program_files_;

import java.util.concurrent.TimeUnit;

public class VisLinkTEST {
	
	public static boolean testFlag_;

	public static int testCnt_ = 0;
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			testFlag_ = false;
			testCnt_++;
			TimeUnit.SECONDS.sleep(1);
			System.out.println("#################### VisLinkTEST: VisLinks is SUSPENDED ####################");
		}
		testFlag_ = true;
	}

}
