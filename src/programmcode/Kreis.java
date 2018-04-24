package programmcode;

public class Kreis {
	private double x,y,mx,my;
	private int r;
		public Kreis(int pX, int pY, int pR, double pMx, double pMy) {
			x = pX;
			y = pY;
			r = pR;
			mx = pMx;
			my = pMy;
		}
	public Kreis(double pX, double pY, int pR, double pMx, double pMy) {
			x = pX;
			y = pY;	
			r = pR;
			mx = pMx;
			my = pMy;
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
	public double getMx() {
		return mx;
	}
	public double getMy() {
		return my;
	}
	public void setMx(double pMx) {
		mx = pMx;
	}
	public void setMy(double pMy) {
		my = pMy;
	}
	public double getRr() {
		return r/2;
	}
}
