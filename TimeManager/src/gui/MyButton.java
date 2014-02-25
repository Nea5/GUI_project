package gui;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MyButton extends JButton {
	
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
