package com.dovgreenwood.ubuntu.cad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * Name: Dov Greenwood
 * Date: 9/3/13 (date of commenting)
 * 
 * This class is a panel to be activated by Ubuntu on pressing Ctrl + alt + del.
 * It is meant to be similar in structure to the windows CAD panel, and has similar options:
 * system monitor, terminal, logout, lock, restart, and shut down. At the top of the panel is displayed
 * the name of the machine, and the username of the person currently logged in.
 * */

public class CADPanel extends JPanel implements ActionListener {

	JButton cancel;
	JButton systemMonitor;
	JButton terminal;
	JButton logout;
	JButton lock;
	JButton restart;
	JButton shutDown;
	
	public CADPanel() throws UnknownHostException {
		this.setLayout(new BorderLayout());
		
		JPanel optionsPanel = createOptionsPanel();
		JPanel exitPanel = createExitPanel();
		
		JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		userPanel.add(new JLabel(InetAddress.getLocalHost().getHostName() + " is logged on as " + System.getProperty("user.name")));
		
		this.add(optionsPanel, BorderLayout.CENTER);
		this.add(exitPanel, BorderLayout.SOUTH);
		this.add(userPanel, BorderLayout.NORTH);
	}
	
	private JPanel createExitPanel() {
		JPanel exitPanel = new JPanel();
		exitPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		cancel = new JButton("CANCEL");
		cancel.addActionListener(this);
		
		exitPanel.add(cancel);
		
		return exitPanel;
	}

	private JPanel createOptionsPanel() {
		JPanel optionsPanel = new JPanel();
		//                                    r  c
		optionsPanel.setLayout(new GridLayout(2, 3));
		//move icons to com.dovgreenwood.ubuntu.cad
		systemMonitor = new JButton("System Monitor", new ImageIcon(getClass().getResource("images/systemMonitor.png")));
		systemMonitor.addActionListener(new ButtonListener());
		terminal = new JButton("Terminal", new ImageIcon(getClass().getResource("images/terminal.png")));
		terminal.addActionListener(new ButtonListener());
		logout = new JButton("Logout", new ImageIcon(getClass().getResource("images/logout.png")));
		logout.addActionListener(new ButtonListener());
		lock = new JButton("Lock Screen", new ImageIcon(getClass().getResource("images/lock.png")));
		lock.addActionListener(new ButtonListener());
		restart = new JButton("Restart", new ImageIcon(getClass().getResource("images/restart.png")));
		restart.addActionListener(new ButtonListener());
		shutDown = new JButton("Shut Down", new ImageIcon(getClass().getResource("images/shutDown.png")));
		shutDown.addActionListener(new ButtonListener());
		
		systemMonitor.setHorizontalTextPosition(SwingConstants.CENTER);
		systemMonitor.setVerticalTextPosition(SwingConstants.BOTTOM);
		terminal.setHorizontalTextPosition(SwingConstants.CENTER);
		terminal.setVerticalTextPosition(SwingConstants.BOTTOM);
		logout.setHorizontalTextPosition(SwingConstants.CENTER);
		logout.setVerticalTextPosition(SwingConstants.BOTTOM);
		lock.setHorizontalTextPosition(SwingConstants.CENTER);
		lock.setVerticalTextPosition(SwingConstants.BOTTOM);
		restart.setHorizontalTextPosition(SwingConstants.CENTER);
		restart.setVerticalTextPosition(SwingConstants.BOTTOM);
		shutDown.setHorizontalTextPosition(SwingConstants.CENTER);
		shutDown.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		optionsPanel.add(systemMonitor);
		optionsPanel.add(terminal);
		optionsPanel.add(logout);
		optionsPanel.add(lock);
		optionsPanel.add(restart);
		optionsPanel.add(shutDown);
		
		return optionsPanel;
	}
	
	

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String command = "";
			
			if(e.getSource() == systemMonitor) {
				command = "gnome-system-monitor";
			}
			if(e.getSource() == terminal) {
				command = "gnome-terminal --working-directory=";
			}
			if(e.getSource() == logout) {
				command = "/usr/lib/indicator-session/gtk-logout-helper --logout";
			}
			if(e.getSource() == lock) {
				command = "gnome-screensaver-command -l";
			}
			if(e.getSource() == restart) {
				command = "/usr/lib/indicator-session/gtk-logout-helper --restart";
			}
			if(e.getSource() == shutDown) {
				command = "/usr/lib/indicator-session/gtk-logout-helper --shutdown";
			}
			
			try {
				Runtime.getRuntime().exec(command);
			} 
			catch (IOException ex) {
			}
			System.exit(0);
		}
		
	}

}
