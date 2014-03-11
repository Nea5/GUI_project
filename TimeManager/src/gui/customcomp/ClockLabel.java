package gui.customcomp;

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
/**
 * 
 * @author Johan Dahlkar
 * @author Markus Ebbesson
 * @author Marcus Enderskog
 * @author Jonas Rosenlind
 * @author Linnea Sandelin
 * @author Marcus Utter
 */
public class ClockLabel extends JLabel implements ActionListener {
	private SimpleDateFormat df, tf;
	private String prefix;
	private String time, date;
	/**
	 * Constructs a ClockLabel with a prefix, dateFormat and timeFormat
	 * @param prefix Prefix to be shown before time
	 * @param dateFormat Format of the date, must be in SimpleDateFormat date
	 * 					and time pattern
	 * @param timeFormat Format of the time, must be in SimpleDateFormat date
	 * 					and time pattern
	 */
	public ClockLabel(String prefix, String dateFormat, String timeFormat) {
		this.prefix = prefix;
		this.setPreferredSize(new Dimension(120,65));
		this.setMinimumSize(new Dimension(100,65));
		this.df = new SimpleDateFormat(dateFormat);
		this.tf = new SimpleDateFormat(timeFormat);
		Date d = new Date();
		time = tf.format(d);
		date = df.format(d);
		Timer t = new Timer(1000, this);
		t.start();
	}
	/**
	 * Updates the time and date to be shown by this label to current time.
	 */
	public void actionPerformed(ActionEvent e) {
		Date d = new Date();
		time = tf.format(d);
		date = df.format(d);
		this.repaint();
	}
	/**
	 * Paints this label
	 */
	public void paintComponent(Graphics g){
	    super.paintComponent(g);
	    g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
	    String fontName = "Arial Black"; //Font name to use
	    g.setColor(Color.BLACK);
	    Font font = new Font(fontName, Font.PLAIN, 14);
	    g.setFont(font);
	    g.drawString(prefix + ":", 10,15); //Draws the prefix string
	    
	    int y =  g.getFontMetrics().getHeight();
	    font = new Font(fontName, Font.PLAIN, 20); //Change font
	    g.setFont(font);
	    int y2 =  g.getFontMetrics().getHeight();
	    g.drawString(time, 15, 15 +y); //Draws the current time
	    font = new Font(fontName, Font.PLAIN, 14);
	    g.setFont(font);
	    g.drawString(date, 27, 4+ y2+y); //Draws the current date
	}

}
