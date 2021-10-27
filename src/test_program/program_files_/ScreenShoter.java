//Makes screenshots of the pages tested
//using aShot library

package test_program.program_files_;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import test_program.Default_StartBrowser;

public class ScreenShoter extends Thread implements Runnable {
	
	static String pathToFl = System.getProperty("user.dir") + "\\src\\test_program\\screen_shots\\"; 
	public static WebDriver webDriver;
	private static Screenshot pageShoter;
	public static boolean vLinksFlg_Shoter;
	
	public static void pageShot(final String pageTitle_, final int cnt2_) throws InvocationTargetException, InterruptedException {
		
		webDriver = Default_StartBrowser.webDriver;
		vLinksFlg_Shoter = false;
		
			pageShoter = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(Default_StartBrowser.webDriver);

			try {
					ImageIO.write(pageShoter.getImage(), "png", new File(pathToFl + cnt2_ + ".png"));
					System.out.println("::::::::::::::::::: Screenshot made was::: " + pathToFl + cnt2_ + ".png :::::::::::::::::::" );
					TimeUnit.SECONDS.sleep((long)0.5);
					vLinksFlg_Shoter = true;
				} catch (IOException | InterruptedException ioe) {
					System.out.println("<<<<< Screen shot failed on page " + pageTitle_ + ", session " + cnt2_ + " >>>>>");
					vLinksFlg_Shoter = true;
					ioe.printStackTrace();
				} //eotry
//		return pgShot;
	}// eomethod

}//eoclass	