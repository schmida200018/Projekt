package programmcode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Screen extends Canvas{
	private Hintergrund hintergrund;
	public Screen(Hintergrund h, KeyEvt e){
		
		hintergrund = h;
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    JFrame frame = new JFrame("Noah");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(screenSize);
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
	    frame.setLayout(new BorderLayout());
	    frame.add(this,BorderLayout.CENTER);
	    this.addKeyListener(e);
	    buttonEnde.setFocusable(false);
	    frame.setUndecorated(true);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setUndecorated(true);
	    frame.setVisible(true);
}
}
