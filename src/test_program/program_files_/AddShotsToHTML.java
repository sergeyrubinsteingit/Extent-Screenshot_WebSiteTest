//Writes screen shot images into HTML file
package test_program.program_files_;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AddShotsToHTML {

	private static String shotsDir;

	public static void main(String[] args) throws IOException {
				
		File shotsFile = new File(System.getProperty("user.dir") + "\\src\\test_program\\logFile\\ScreenShotsRep.html");
			if (shotsFile.exists()) {
				try {
					shotsFile.delete();
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e1) {
					System.out.println("Failed to delete " + shotsFile.toString());
					e1.printStackTrace();
				}//eotry
			}//eoif
			
			try {
				shotsFile.createNewFile();
				TimeUnit.SECONDS.sleep((long)0.3);
			} catch (IOException | InterruptedException e2) {
				System.out.println("Failed to create " + shotsFile.toString());
				e2.printStackTrace();
			}

		String css_js_src = "file:///" + System.getProperty("user.dir") + "\\src\\test_program\\logFile_css_js_\\";
		BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(shotsFile));
		
		bufferWriter.write("<!DOCTYPE html>");
		bufferWriter.write("<html lang=\"en\"><head>");
		bufferWriter.write(" <meta charset=\"UTF-8\">");
		bufferWriter.write("<script src=" + css_js_src + "shot_rep_js.js" + "></script>");
		bufferWriter.write("<link rel=\"stylesheet\" href=" + css_js_src + "shot_rep_styles.css" + " type=\"text/css\">");
		bufferWriter.write("<title>The Screen Shots Log</title>");
		bufferWriter.write("</head>");
		bufferWriter.write("<body onload=\"size_functions()\">");
		bufferWriter.write("<div id=\"enlarged_img_div\">");
		bufferWriter.write("<button id=\"enlarged_img_butt\">Close this shot</button><br>");
		
		bufferWriter.write("<div id=\"enlarged_img_link_div\"></div>");
			
		bufferWriter.write("<img id=\"enlarged_img_img\" />");
		bufferWriter.write("</div>");
		
		bufferWriter.write("<center>");
		bufferWriter.write("<div id=\"upper_div\">");
		bufferWriter.write("<p id=\"header_1_\">Screen shots summary</p>");
		bufferWriter.write("</div>");
		bufferWriter.write("<div id=\"upper_inside_div\">");
		bufferWriter.write("<p id=\"header_2_\">Click the image to see it enlarged<p>");
		bufferWriter.write("</div>");

		bufferWriter.write("<div id=\"container_div\">");
				
		if (VisitLinks.linksCounter <= 0) {
			VisitLinks.linksCounter = 26;
		}//eoif
		
		String imageString = new String();
		for (int i = 0; i < VisitLinks.linksCounter; i++) {
			try {
				shotsDir = "file:///" + System.getProperty("user.dir") + "\\src\\test_program\\screen_shots\\" + i + ".png";					
				imageString = System.getProperty("user.dir") + "\\src\\test_program\\screen_shots\\" + i + ".png";
				File shotFile = new File(imageString);
				TimeUnit.SECONDS.sleep(1);
				
				if (shotFile.exists() && shotFile.isFile() && VisitLinks.allURLs.size() != 0) {
					bufferWriter.write("<div class=\"img_Div\" id=\"img_Div_" + i + "\" width=\"18%\" height=\"18%\""
							+ " style=\"overflow-x:hidden; overflow-y:auto;\" onclick=\"selected_Img(this.id)\">"
							+ "<img src="+ shotsDir + " id=\"shot_"+ i +"\" class=\"allShots\" width=\"18%\" height=\"auto\"/>"
							+ "<span class=\"pageURL_\">" + VisitLinks.allURLs.get(i) + "</span>"
							+ "</div>");
				} else {
					System.out.println("(((( Links array is empty!!! ))))");
					bufferWriter.write("<div class=\"no_img_Div_\" width=\"18%\" height=\"18%\">"
							+ "<span id=\"fl_not_exists\">This shot is not exists</span></div>");
				}//eoif
				
				
			} catch (IOException e) {
				bufferWriter.write("Screen shot image " + shotsDir + " was not found.");
				System.out.println("Screen shot image " + shotsDir + " was not found.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//eofor
		
		bufferWriter.write("</div><!-- end of container div -->");
		bufferWriter.write("</center>");
		
		bufferWriter.write("<script src=" + css_js_src + "shot_rep_js.js" + "></script>");

		bufferWriter.write("</body>");
		
		bufferWriter.close();
	}//eomain

}//eoclass
