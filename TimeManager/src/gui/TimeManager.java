package gui;
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
	 */
	
	public static void main(String[] args) {
		ResourceBundle settings = ResourceBundle.getBundle("gui.resources.settings");
		language = settings.getString("language");
		if(language.equals("Svenska")){
			System.out.println("Svenskt språk valt");
			rb = ResourceBundle.getBundle("gui.resources.language_sv_SE");
		} else {
			System.out.println("English språk valt");
			rb = ResourceBundle.getBundle("gui.resources.language_en_GB");
		}
		new Controller(new MainFrame());
	}

}
