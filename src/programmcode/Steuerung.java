package programmcode;

public class Steuerung {
	public static void main(String[]Args){
		Hintergrund hintergrund = new Hintergrund();
		hintergrund.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hintergrund.stop();
	}
	
}
