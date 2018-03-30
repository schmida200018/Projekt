package programmcode;

public class Circle {
	private int x,y,r;
		public Circle(int pX, int pY, int pR) {
			x = pX;
			y = pY;
			r = pR;
		}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getR() {
		return r;
	}
	public void setX(int pX) {
		x = pX;
	}
	public void setY(int pY) {
		y = pY;
	}
}
