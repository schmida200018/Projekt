package programmcode;

import java.util.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 09.02.2018
  * @author 
  */

public class Hintergrund implements Runnable{
  private boolean running;
  private Screen screen;
  private double x,y; 
  private double mx, my;
  private ArrayList<Rechteck> rechteck = new ArrayList<Rechteck>();
  private boolean laeuft = false;
  private Circle circle = new Circle(500,Toolkit.getDefaultToolkit().getScreenSize().height-105,20);
  
  
  public static final int FPS = 144;
  public static final int TPS = 128;

  public Hintergrund() {
	  screen = new Screen(this);
  }
  
  public synchronized void start(){
    running = true;
    new Thread(this).start();
  }
  
  public void setLaeuft(boolean pLaeuft) {
	  laeuft = pLaeuft;
	  mx = 0;
	  my = -1;
	  System.out.println("Start");
  }
  
  public synchronized void stop(){
    running = false;
  }
  
  public void init(){
    x=Toolkit.getDefaultToolkit().getScreenSize().width/2;
    y=Toolkit.getDefaultToolkit().getScreenSize().height-100;
    rechtecke();
  }
  
  public void rechtecke() {  
	  rechteck.add(new Rechteck(470,1020,50,50,10));
	  rechteck.add(new Rechteck(470,500,50,50,10));
	  rechteck.add(new Rechteck(700,Toolkit.getDefaultToolkit().getScreenSize().height-100,50,50,10));
	  rechteck.add(new Rechteck(1200,Toolkit.getDefaultToolkit().getScreenSize().height-100,50,50,10));
	  System.out.println(""+rechteck.get(0).getHeight());
  }
  
  public void render() {
    BufferStrategy bs = screen.getBufferStrategy();
    if(bs == null){
      screen.createBufferStrategy(3);
      return;
      }
    Graphics g = bs.getDrawGraphics();
    g.clearRect(0,0,screen.getWidth(),screen.getHeight());
    for(int i = 0; i < rechteck.size(); i++){
    	g.drawRect((int)rechteck.get(i).getX(),(int)rechteck.get(i).getY(),rechteck.get(i).getWidth(),rechteck.get(i).getHeight());
    	g.drawString(""+rechteck.get(i).getLeben(), (int)rechteck.get(i).getX()+17, (int)rechteck.get(i).getY()+29);
    }
    
    //Render start
    
    g.fillOval((int)circle.getX()-circle.getR()/2,(int) circle.getY()-circle.getR()/2, circle.getR(), circle.getR());
    
    //Render ende
    g.setColor(Color.blue);
    g.dispose();
    bs.show();
    
  }
  
  public void tick() {
    if(laeuft) {
	x = circle.getX();
	y = circle.getY();
	x = x+mx;
	y = y+my;
	circle.setX(x);
	circle.setY(y);
    boolean colission = false;
    boolean colissionX =  false;
    boolean colissionY = false;
    boolean ende = false;
	for(int i = 0; i < rechteck.size(); i++) {
    	double distanceY = rechteck.get(i).getY()-circle.getY(); 
    	double distanceX = rechteck.get(i).getX()-circle.getX();
    boolean colissionY1 = false;
    if(distanceY<circle.getR()/2&&distanceY>-(rechteck.get(i).getHeight()+circle.getR()/2)) {
    	colissionY1 = true;
    }	
    if(distanceX<circle.getR()/2&&colissionY1&&distanceX > - (rechteck.get(i).getWidth()+circle.getR()/2)) {
    	colission = true;
    	rechteck.get(i).verliereLeben();
		boolean loeschen = rechteck.get(i).istLebenNull();
		if(loeschen) {
			rechteck.remove(i);
    }
    if(colission) {
    	if(distanceX>0||distanceX<-rechteck.get(i).getWidth()) {
    		colissionX = true;
    	}
    	if(distanceY>0||distanceY<-rechteck.get(i).getHeight()) {
    		colissionY = true;
    		}
    	}
    }

    }
	if(circle.getX() <=0) {
		colissionX = true;
	}
	if(circle.getX() >= Toolkit.getDefaultToolkit().getScreenSize().getWidth()-circle.getR()) {
		colissionX = true;
	}
	if(circle.getY() <= 0) {
		colissionY = true;
	}
	if(circle.getY() >= Toolkit.getDefaultToolkit().getScreenSize().getHeight()-30) {
		ende = true;
		}
    if(colissionY) {
    	my = -my;
    	System.out.println("Colission Y");
    }
    if(colissionX) {
    	System.out.println("Colission X");
    	mx = -mx;
    }
    if(ende) {
    	mx = 0;
    	my = 0;
    	x=Toolkit.getDefaultToolkit().getScreenSize().width/2;
    	y=Toolkit.getDefaultToolkit().getScreenSize().height-100;
    	circle.setX(x);
    	circle.setY(y);
    	laeuft = false;
    	ende = false;
    	System.out.println("Ende");
    }
    if(!laeuft) {
    	System.out.println("Ende");
    }
    }
    
  }
  
  public void run(){
    init();
    long lastTime = System.nanoTime();
    double nsPerFrame = 1000000000D/FPS;
    double nsPerTick = 1000000000D/TPS;
    double deltaF = 0;
    double deltaT = 0;
    
    while(running){
      long now = System.nanoTime();
      deltaF += (now-lastTime)/nsPerFrame;
      deltaT += (now-lastTime)/nsPerTick;
      lastTime = now;
      while(deltaF>1){
        deltaF--;
        render();
      }
      
      while(deltaT>1){
        deltaT--;
        tick();
      }
    }
  }
  
}