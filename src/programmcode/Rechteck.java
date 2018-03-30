package programmcode;

public class Rechteck {
	private int x,y;
	private int width,height;
	public Rechteck(int pX,int pY, int pWidth, int pHeight) {
		x = pX;
		y = pY;
		width = pWidth;
		height = pHeight;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int pX) {
		x = pX;
	}
	
	public void setY(int pY) {
		y= pY;
	}
}
