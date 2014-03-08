package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ClockLabel extends JLabel implements ActionListener {
	private SimpleDateFormat f;
	private String prefix;
	
	public ClockLabel(String prefix, String format) {
		this.prefix = prefix;
		this.setAlignmentX(JLabel.CENTER);
		this.setBorder(BorderFactory.createTitledBorder(prefix));
		this.f = new SimpleDateFormat(format);
		Timer t = new Timer(1000, this);
		t.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setText(f.format(new Date()));
	}

}
