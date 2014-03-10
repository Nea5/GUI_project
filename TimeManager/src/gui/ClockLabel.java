package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ClockLabel extends JLabel implements ActionListener {
	private SimpleDateFormat df, tf;
	private String prefix;
	private String time, date;
	/**
	 * 
	 * @param prefix
	 * @param dateFormat
	 * @param timeFormat
	 */
	public ClockLabel(String prefix, String dateFormat, String timeFormat) {
		this.prefix = prefix;
		this.setPreferredSize(new Dimension(25,65));
		this.df = new SimpleDateFormat(dateFormat);
		this.tf = new SimpleDateFormat(timeFormat);
		
		Date d = new Date();
		time = tf.format(d);
		date = df.format(d);
		Timer t = new Timer(1000, this);
		t.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Date d = new Date();
		time = tf.format(d);
		date = df.format(d);
		this.repaint();
	}
	public void paintComponent(Graphics g){
	    super.paintComponent(g);
	    g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
	    String fontName = "Arial Black";
	    g.setColor(Color.BLACK);
	    Font font = new Font(fontName, Font.PLAIN, 14);
	    g.setFont(font);
	    g.drawString(prefix + ":", 10,15);
	    
	    int y =  g.getFontMetrics().getHeight();
	    font = new Font(fontName, Font.PLAIN, 20);
	    g.setFont(font);
	    int y2 =  g.getFontMetrics().getHeight();
	    g.drawString(time, 15, 15 +y);
	    font = new Font(fontName, Font.PLAIN, 14);
	    g.setFont(font);
	    g.drawString(date, 27, 4+ y2+y);
	}

}
