package gui;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.plaf.synth.SynthLookAndFeel;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import model.ToDoModel;
import gui.MainFrame;
import control.*;
/**
* 
* This is our Time Manager.
* 
* @version 0.1 
* @since 2014-02-07
* 
* @author Johan Dahlkar 
* @author Markus Ebbesson 
* @author Marcus Enderskog
* @author Jonas Rosenlind
* @author Linnea Sandelin
* @author Marcus Utter
*/

public class TimeManager {

	//public static final String LookAndFeel = null;
	public static ResourceBundle rb;
	public static String language;
	public static String look_and_feel;
	private ToDoModel model;
	private MainFrame view;

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	public TimeManager(){
		createProperties();
		loadSaved();
		loadLanguage();
		view = new MainFrame(model);
		
		view.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    storePositions(view);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
		
		new Controller(view);
	}	
	
	private void createProperties(){
		File settings = new File("settings.properties");
		if(!settings.exists()){
			try {
				settings.createNewFile();
				FileOutputStream out = new FileOutputStream("settings.properties");
                Properties props = new Properties();
             	props.setProperty("language", "English");
             	props.setProperty("look_and_feel", "Metal");
             	props.setProperty("windowwidth", "500");
             	props.setProperty("windowheight", "500");
             	props.setProperty("windowposx", "0");
             	props.setProperty("windowposy", "0");
                props.store(out, null);
                out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void loadLanguage(){
		FileInputStream in;
		try {
			in = new FileInputStream("settings.properties");
			Properties prop = new Properties();	
			try {
				prop.load(in);
				in.close();
				
				if(prop.containsKey("language")){
					language = prop.getProperty("language");
					if(language.equals("Svenska")){
					
						rb = ResourceBundle.getBundle("gui.resources.language_sv_SE");
					} else {
						rb = ResourceBundle.getBundle("gui.resources.language_en_GB");
					}
				} else {
					language = "English";
					rb = ResourceBundle.getBundle("gui.resources.language_en_GB");
				}
			
				if(prop.containsKey("look_and_feel")){
					look_and_feel = prop.getProperty("look_and_feel");
					if(look_and_feel.equals("Nimbus")){
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					} else {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.metal.MetalLookAndFeel");		
					}	
				} else {
					look_and_feel = "Metal";	
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.metal.MetalLookAndFeel");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void loadSaved(){
		try{
			ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("todos.srz")));
			model = (ToDoModel)in.readObject();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(model == null){
			model = new ToDoModel();
		}
	}
	private void storePositions(MainFrame view){
		Properties prop = new Properties();
			try {
				FileInputStream in = new FileInputStream("settings.properties");
				prop.load(in);
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		try {
			FileOutputStream out = new FileOutputStream("settings.properties");
         	prop.setProperty("windowposx", Integer.toString(view.getLocationOnScreen().x));
         	prop.setProperty("windowposy", Integer.toString(view.getLocationOnScreen().y));
         	prop.setProperty("windowwidth", Integer.toString(view.getWidth()));
         	prop.setProperty("windowheight", Integer.toString(view.getHeight()));
            prop.store(out, null);
            out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args){
		//try 
	    //{ 
	        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	    //} 
	    //catch(Exception e){ 
	    //}

		new TimeManager();
	}

}
