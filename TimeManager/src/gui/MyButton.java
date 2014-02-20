package gui;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MyButton extends JButton {

	public MyButton(String text,String icon) {
		ClassLoader cl = getClass().getClassLoader();
		Icon i = new ImageIcon(cl.getResource("resources/" + icon +".gif"));
		this.setIcon(i);
		this.setText(text);
	}

}
