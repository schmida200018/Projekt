package programmcode;

public class Circle {
	private double x,y;
	private int r;
		public Circle(int pX, int pY, int pR) {
			x = pX;
			y = pY;
			r = pR;
		}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getR() {
		return r;
	}
	public void setX(double pX) {
		x = pX;
	}
	public void setY(double pY) {
		y = pY;
	}
}
