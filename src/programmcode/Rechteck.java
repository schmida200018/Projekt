package programmcode;

public class Rechteck {
	private double x,y;
	private int width,height;
	public Rechteck(int pX,int pY, int pWidth, int pHeight) {
		x = pX;
		y = pY;
		width = pWidth;
		height = pHeight;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(double pX) {
		x = pX;
	}
	
	public void setY(double pY) {
		y= pY;
	}
}
