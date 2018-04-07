package programmcode;

public class Rechteck {
	private double x,y;
	private int width,height;
	private int leben;
	public Rechteck(int pX,int pY, int pWidth, int pHeight, int pLeben) {
		x = pX;
		y = pY;
		width = pWidth;
		height = pHeight;
		leben = pLeben;
	}
	
	public void verliereLeben() {
		leben--;
	}
	
	public int getLeben() {
		return leben;
	}
	
	public boolean istLebenNull() {
		if(leben == 0) {
			return true;
		}else {
			return false;
		}
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
