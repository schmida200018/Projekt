package programmcode;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyEvt implements KeyListener{
	
	private Hintergrund hintergrund;
	public KeyEvt(Hintergrund pH) {
		hintergrund = pH;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hintergrund.setRechts();
			hintergrund.rechts();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hintergrund.setLinks();
			hintergrund.links();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			hintergrund.setSpace();
			hintergrund.setLaeuft();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			hintergrund.delRechts();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			hintergrund.delLinks();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			hintergrund.delSpace();
		}
		/*if(e.getKeyCode() == KeyEvent.VK_0){
			hintergrund.tickDurchZehn();
		}*/
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_0) {
			hintergrund.tickMalZehn();
			System.out.println("Test");
		}
		
		
	}

}
