package gui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

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

	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
	
	public static void main(String[] args){
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
				language = prop.getProperty("language");
				if(language.equals("Svenska")){
					System.out.println("Svenskt sprak valt");
					rb = ResourceBundle.getBundle("gui.resources.language_sv_SE");
				} else {
					System.out.println("English sprak valt");
					rb = ResourceBundle.getBundle("gui.resources.language_en_GB");
				}
				new Controller(new MainFrame());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//rb = ResourceBundle.getBundle("gui.resources.language_en_GB");
				//language = "English";
				//new Controller(new MainFrame());
				e.printStackTrace();
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//rb = ResourceBundle.getBundle("gui.resources.language_en_GB");
			//language = "English";
			//new Controller(new MainFrame());
			e.printStackTrace();
		}
		
		//ResourceBundle settings = ResourceBundle.getBundle("settings");
		//language = settings.getString("language");
		
	}

}
