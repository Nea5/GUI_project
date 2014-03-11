package gui;

import javax.swing.*;
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class MyButton extends JButton {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructs a JButton with the text and a imageicon
	 * @param text Button text
	 * @param icon Name of the image to be used.
	 */
	public MyButton(String text,String icon) {
		ClassLoader cl = getClass().getClassLoader();
		Icon i = new ImageIcon(cl.getResource("resources/" + icon +".gif"));
		this.setIcon(i);
		this.setText(text);
	}
}
