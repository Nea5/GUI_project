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

import javax.swing.SwingUtilities;

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

	public static ResourceBundle rb;
	public static String language;
	private ToDoModel model;
	private MainFrame view;

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	public TimeManager(){
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
	private void loadLanguage(){
		FileInputStream in;
		File settings = new File("settings.properties");
		if(!settings.exists()){
			try {
				settings.createNewFile();
				FileOutputStream out = new FileOutputStream("settings.properties");
                Properties props = new Properties();
             	props.setProperty("language", "English");
                props.store(out, null);
                out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
				
			} catch (IOException e) {
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
		File settings = new File("settings.properties");
		Properties prop = new Properties();
		
		if(!settings.exists()){
			try {
				settings.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
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
		new TimeManager();
	}

}
