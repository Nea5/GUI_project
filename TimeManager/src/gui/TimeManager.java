package gui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	
	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream("settings.properties");
		Properties prop = new Properties();
		prop.load(in);
		language = prop.getProperty("language");
		//ResourceBundle settings = ResourceBundle.getBundle("settings");
		//language = settings.getString("language");
		if(language.equals("Svenska")){
			System.out.println("Svenskt sprak valt");
			rb = ResourceBundle.getBundle("gui.resources.language_sv_SE");
		} else {
			System.out.println("English sprak valt");
			rb = ResourceBundle.getBundle("gui.resources.language_en_GB");
		}
		new Controller(new MainFrame());
	}

}
