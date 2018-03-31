package programmcode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Screen extends Canvas{
	private Hintergrund hintergrund;
	public Screen(Hintergrund h){
		hintergrund = h;
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    JFrame frame = new JFrame("Noah");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(screenSize);
	    JButton button = new JButton();
	    button.setBounds( Toolkit.getDefaultToolkit().getScreenSize().width-100, Toolkit.getDefaultToolkit().getScreenSize().height-100,50,40);
	    button.setText("Start");
	    button.setMargin(new Insets(2,2,2,2));
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		hintergrund.setLaeuft(true);
	    	}
	    });
	    JButton buttonEnde = new JButton();
	    buttonEnde.setBounds( Toolkit.getDefaultToolkit().getScreenSize().width-100, Toolkit.getDefaultToolkit().getScreenSize().height-160,50,40);
	    buttonEnde.setText("Ende");
	    buttonEnde.setMargin(new Insets(2,2,2,2));
	    buttonEnde.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    });
	    
	    
	    
	    frame.add(buttonEnde);
	    frame.add(button);
	    frame.setLayout(new BorderLayout());
	    frame.add(this,BorderLayout.CENTER);
	    
	    /*frame.setUndecorated(true);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);*/
	    frame.setUndecorated(true);
	    frame.setVisible(true);
}
}
