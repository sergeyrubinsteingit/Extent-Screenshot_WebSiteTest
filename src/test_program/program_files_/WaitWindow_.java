//Opens GUI window. This window's target is to explain
//to user the pause on the screen while a test is running
//on the background. Shows up testing time passed, in seconds.
//The window has a button to abort the process 
// while exiting the system

package test_program.program_files_;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class WaitWindow_ extends JFrame {
	
	private int frameWdth = 300;
	private int frameHgth = 300;
	
  public WaitWindow_() {
    super("Test is running...");
    this.setVisible(true);
    this.setSize(frameWdth, frameHgth);
    this.setLayout(new BorderLayout(2, 1));
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
    this.setLocationRelativeTo(null);
    
    WaitLabel wLabel = new WaitLabel();
    wLabel.setHorizontalAlignment(JLabel.CENTER);
    wLabel.setVerticalAlignment(JLabel.CENTER);
    wLabel.setPreferredSize(new Dimension(300,180));
    
    WaitButton wButton = new WaitButton();
    wButton.setText("Double-click to stop the test");
//    wButton.addMouseListener((MouseListener) wButton);
    
    WaitPanel wPanel = new WaitPanel();
    wPanel.add(wLabel, BorderLayout.NORTH);
    wPanel.add(wButton, BorderLayout.SOUTH);
    
    this.getContentPane().add(wPanel);
  }//eoconstructor
  
 
  public static void main(String args[]) {
	  final WaitWindow_ wWindowSettings = new WaitWindow_();
	  if (!wWindowSettings.isVisible()) {
		  wWindowSettings.setVisible(true);
	  }//eoif
	  
	  	wWindowSettings.addWindowFocusListener(new WindowAdapter() {
	    	@Override
			public void windowLostFocus(WindowEvent e) {
				wWindowSettings.setVisible(true);
				wWindowSettings.toFront();
				wWindowSettings.requestFocus();
	    	}
		});// eo addWindowFocusListener
  } //eomain

}//eoclass

class WaitLabel extends JLabel implements ActionListener {

	private String countString;
	private String testStatus;
	public static int testNmb;
	public static int secondsCounter;
	public static String pageName;
	
	public WaitLabel() {
	    Timer timerSettings = new Timer(1000, this);
	    timerSettings.start();
	    this.setOpaque(true);
	    this.setBackground(Color.ORANGE);
	    this.setBorder(new EmptyBorder(0, 10, 0, 10));
//	    secondsCounter = -1;
	}//eometh
	
	public void actionPerformed(ActionEvent ae) {
	  	try {
			secondsCounter++;
			
				if (testNmb > 0) {
					testStatus = "Now test # <b>"+ testNmb + "</b><br/>"
							+ "is running for <b>"+ secondsCounter +" sec.</b><br/>"
							+ "Now test is running on page:<br /> <b>"+ pageName +"</b><br/><br/>";
				} else {
					testStatus = "Preparing for test.<br />";
				}//eoif
			
			countString = "<html><body>"
				+ "<font size='20px' family='Helvetica, sans-serif'>"
				+ testStatus + "</font>"
				+ "<font size='15px' family='Helvetica, sans-serif'>"
				+ "<i>Please make your coffee and wait...</i>"
				+ "</font></body></html>";
			TimeUnit.SECONDS.sleep(1);
			setText(countString);
		} catch (InterruptedException e) {
			System.out.println("+++++ wLabel() failed +++++");
			e.printStackTrace();
		}
	}//eometh
	
}//eoclass


class WaitButton extends JButton {
	
	public WaitButton() {

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					QuitTest.quitTest();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
	    this.setPreferredSize(new Dimension(200, 50));
	} //eoconstructor

}//eoclass


class WaitPanel extends JPanel {
	public WaitPanel() {
		this.setSize(WaitWindow_.WIDTH, WaitWindow_.HEIGHT);
	    this.setOpaque(true);
	    this.setBackground(Color.ORANGE);
	}//eoconstructor
} //eoclass WaitPanel
