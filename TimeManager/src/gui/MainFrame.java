package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
/**
 * 
 */
public class MainFrame extends JFrame {
	private JMenuBar menubar;
	private JTabbedPane tabbedPane;
	
	public MainFrame(){
		createMenuBar(System.getProperty("os.name"));
		createTabbedPane();
		
		this.add(tabbedPane);
		
		this.setJMenuBar(menubar);
		this.setMinimumSize(new Dimension(485,560));
		this.setVisible(true);
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
	
	public static void main(String [] args){
		new MainFrame();
	}
}
