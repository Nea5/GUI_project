package gui;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.MainFrame;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale Svenska = new Locale("sv", "SE");
		
		rb = ResourceBundle.getBundle("gui.resources.language", Svenska);
		new MainFrame();
	}

}
