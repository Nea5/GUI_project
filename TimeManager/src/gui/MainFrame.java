package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.*;
/**
 * 
 */
public class MainFrame extends JFrame {
	private TimeMenuBar menubar;
	private TabPanel tabbedPane;
	
	public MainFrame(){
		createMenuBar(System.getProperty("os.name"));
		createTabbedPane();
		
		this.add(tabbedPane);
		
		this.setJMenuBar(menubar);
		this.setMinimumSize(new Dimension(485,560));
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
	}
	
	private void createMenuBar(String osName){
		if(osName.equals("Mac OS X")){
			System.setProperty("apple.laf.useScreenMenuBar", "true"); 
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "TimeManager");	
		}
		menubar = new TimeMenuBar();
	}
	
	private void createTabbedPane(){
		tabbedPane = new TabPanel();
	}
	
	public TabPanel getTabPanel(){
		return tabbedPane;
	}
	
	public TimeMenuBar getMenu(){
		return menubar;
	}
	public static void main(String [] args){
		new MainFrame();
	}
}
