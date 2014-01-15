package com.dovgreenwood.ubuntu.cad;

import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * Name: Dov Greenwood
 * Date: 9/3/13 (date of commenting)
 * 
 * This frame is used as a display container for CADPanel.  It sets the frame in the middle of the
 * screen, and removes decorations (x, -, <>).  It also sets the look and feel to the default system
 * look and feel: GTK.
 * */

public class CADFrame extends JFrame {
	
	public CADFrame() throws UnknownHostException {
		setSize(600,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}
		JPanel panel = new CADPanel();
		this.add(panel);
	}
	
	public static void main(String [] args) throws UnknownHostException {
		JFrame frame = new CADFrame();
		frame.setVisible(true);
	}
	
}
