/*
 * A simple application demonstrating how to setup a single button with a 
 * Actionlistener attached. 
 */
package demoapps;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author Lars Oestreicher
 */
public class CloseButtonDemo { // This is still a type of JFrame. 
                               // However, in this case we have chosen not
                               // too extend the superclass.

    // This is the window that will be used to work on. 
    //
    JFrame demoWindow;
    
    // This varaiable keeps track on whether the decorations are visble or not.
    //
    boolean decorated = true;

    /**
     * Constructor Creates a window without decorations. 
     */
    public CloseButtonDemo() {
        
        demoWindow = new JFrame("CloseButtonDemo");
        // Creating a content panel
        //
        JPanel testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(400, 400)); // Setting the size
                                                             // of the JPanel and
                                                             // the Window 
                                                             // Setting up the window content 
        //
        demoWindow.getContentPane().add(testPanel);
        testPanel.setLayout(new BorderLayout()); // A BorderLayout is enough for
                                                 // this Demo
        
        JPanel eastPanel = new JPanel();         // A panel to put the Button on
                                                 // In the file shown on the lecture
                                                 // the button was placed in the 
                                                 // Layout frame, which caused it 
                                                 // to fill the whole frame. 

        //demoWindow.setUndecorated(true); // remove all decorations
                                         // incl. the title bar 
                                         // Comment to show a standard window
        
        
        // Create a JButton to close the application
        //
        JButton closeButton = new JButton("Close");
        testPanel.add(eastPanel, BorderLayout.EAST);
        
        eastPanel.add(closeButton);       // The default layout on a panel is a
                                          // FlowLayout.

        
        // This is the preferred way to add actionlisteners.
        // Using an inner, anonymous class. 
        //
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demoWindow.setVisible(false);           // We simulate the closing
                                                        // by making it invisible.
            }
        });

 
        demoWindow.pack();               // Finalize showing the JFrame
        demoWindow.setVisible(true);     // A Window is hidden by default. 
        // Make sure it is visible.

        demoWindow.setDefaultCloseOperation(EXIT_ON_CLOSE); // Make sure we can 
        // quit using the 

    }
    // Use the main method to run the application
    //

    public static void main(String[] args) {

        // Create a Window 
        //
        CloseButtonDemo demo = new CloseButtonDemo();  // The variable can be of a 
                                                       // parent class  

    }
}
