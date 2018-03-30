package programmcode;

import java.awt.*;

import javax.swing.JFrame;

public class Screen extends Canvas{
	public Screen(){
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    JFrame frame = new JFrame("Noah");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(screenSize);
	    frame.setLayout(new BorderLayout());
	    frame.add(this,BorderLayout.CENTER);
	    /*frame.setUndecorated(true);
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);*/
	    
	    frame.setVisible(true);
}
}
