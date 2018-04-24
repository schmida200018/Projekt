package programmcode;

import java.awt.Toolkit;


public class Kreissteuerung {
	private Kreis kreis;
	private Intersect inter = new Intersect();
	private Rechteck rechteck;
	private double x,y;
	private boolean istDrin = true;
	private Hintergrund hintergrund;
	
	
	public Kreissteuerung( Kreis pKreis, Hintergrund pHintergrund) {
		kreis = pKreis;
		System.out.println("Test");
		hintergrund = pHintergrund;
	}
	public Rechteck kollision(Rechteck pRechteck) {
	rechteck = pRechteck;
	x = kreis.getX();
	y = kreis.getY();
	x = x+kreis.getMx();
	y = y+kreis.getMy();
	kreis.setX(x);
	kreis.setY(y);
    
	boolean colission = false;
    boolean colissionX =  false;
    boolean colissionY = false;
    boolean ende = false;
    if(hintergrund.getDelay()==0) {
    	System.out.println(hintergrund.getDelay());
    double distanceY = rechteck.getY()-kreis.getY(); 
    double distanceX = rechteck.getX()-kreis.getX();
    boolean colissionY1 = false;
    if(distanceY<kreis.getR()/2&&distanceY>-(rechteck.getHeight()+kreis.getR()/2)) {
    	colissionY1 = true;
    }	
    if(distanceX<kreis.getR()/2&&colissionY1&&distanceX > - (rechteck.getWidth()+kreis.getR()/2)) {
    	colission = true;
    	
    }
    if(colission) {
    	if(distanceX>0||distanceX<-rechteck.getWidth()) {
    		colissionX = true;
    	}
    	if(distanceY>0||distanceY<-rechteck.getHeight()) {
    		colissionY = true;
    		}
    	}
    
    if(colissionX&&colissionY) {
    	colissionX = false;
    	colissionY = false;
    	colission = false;
    	double distanceR1  = Math.pow(distanceX, 2)+Math.pow(distanceY, 2);
    	double distanceR2  = Math.pow(distanceX+50, 2)+Math.pow(distanceY, 2);
    	double distanceR3  = Math.pow(distanceX, 2)+Math.pow(distanceY+50, 2);
    	double distanceR4  = Math.pow(distanceX+50, 2)+Math.pow(distanceY+50, 2);
    	if(distanceR1 <(kreis.getR()/2)*(kreis.getR()/2)) {
    		//System.out.println(""+distanceY+";"+kreis.getR()/2);
    		//System.out.println("col1");
    		kreis = inter.handleIntersection(rechteck, kreis, 1);
    		colission = true;
    	}
    	if(distanceR2 <(kreis.getR()/2)*(kreis.getR()/2)) {
    		//System.out.println(""+distanceX+";"+distanceY);
    		//System.out.println("col2");
    		kreis = inter.handleIntersection(rechteck, new Kreis(kreis.getX()-rechteck.getWidth(),kreis.getY(),kreis.getR(),kreis.getMx(),kreis.getMy()),2);
    		kreis.setX(kreis.getX()+rechteck.getWidth());
    		colission = true;
    	}
    	if(distanceR3 <(kreis.getR()/2)*(kreis.getR()/2)) {
    		//System.out.println(""+distanceX+";"+distanceY);
    		//System.out.println("col3");
    		kreis = inter.handleIntersection(rechteck, new Kreis(kreis.getX(),kreis.getY()-rechteck.getHeight(),kreis.getR(),kreis.getMx(),kreis.getMy()),3);
    		kreis.setY(kreis.getY()+rechteck.getHeight());
    		colission = true;
    	}
    	if(distanceR4 <(kreis.getR()/2)*(kreis.getR()/2)) {
    		//System.out.println(""+distanceX+";"+distanceY);
    		//System.out.println("col4");
    		kreis = inter.handleIntersection(rechteck, new Kreis(kreis.getX()-rechteck.getWidth(),kreis.getY()-rechteck.getHeight(),kreis.getR(),kreis.getMx(),kreis.getMy()),4);
    		kreis.setY(kreis.getY()+rechteck.getHeight());
    		kreis.setX(kreis.getX()+rechteck.getWidth());
    		colission = true;
    	}
    	
    }
    if(colission) {
    	hintergrund.setDelay(1000);
    	rechteck.verliereLeben();
		System.out.println("verliere Leben");
    }
    
    
	if(kreis.getX() <=kreis.getR()/2) {
		colissionX = true;
	}
	if(kreis.getX() >= Toolkit.getDefaultToolkit().getScreenSize().getWidth()-kreis.getR()) {
		colissionX = true;
	}
	if(kreis.getY() <= kreis.getR()/2) {
		colissionY = true;
	}
	if(kreis.getY() >= Toolkit.getDefaultToolkit().getScreenSize().getHeight()-30) {
		ende = true;
		}
    if(colissionY) {
    	kreis.setMy(-kreis.getMy());
    	System.out.println("Colission Y");
    }
    if(colissionX) {
    	System.out.println("Colission X");
    	kreis.setMx(-kreis.getMx());
    }
	
    if(ende) {
    	istDrin = false;
    }
    }
    
    return rechteck;
    
    }

	public Kreis getKreis() {
		return kreis;
	}
	
	public boolean istDrin() {
		return istDrin;
	}
	
	
}
