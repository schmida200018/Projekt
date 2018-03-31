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
  private ArrayList<Rectangle> rechteck = new ArrayList<Rectangle>();
  private boolean laeuft = false;
  private Circle circle = new Circle(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height-100,20);
  
  
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
	  mx = -1;
	  my = -0.1;
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
	  rechteck.add(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().width/2,1020,50,50));
	  rechteck.add(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().width/2,500,50,50));
	  rechteck.add(new Rectangle(700,Toolkit.getDefaultToolkit().getScreenSize().height-100,50,50));
	  rechteck.add(new Rectangle(1200,Toolkit.getDefaultToolkit().getScreenSize().height-100,50,50));
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
    	g.fillRect((int)rechteck.get(i).x,(int)rechteck.get(i).y,rechteck.get(i).width,rechteck.get(i).height);
    }
    
    //Render start
    
    g.fillOval((int)x,(int)y,(int) circle.getR(),(int) circle.getR());
    
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
    	double distance = (Math.abs(rechteck.get(i).getX()+14-circle.getX())+Math.abs(rechteck.get(i).getY()+14-circle.getY()));
    	System.out.println(""+distance);
    	if(distance < circle.getR()+25 ) {
    		colission = true;
    		double distanceX = (Math.abs(rechteck.get(i).getX()+14-circle.getX()));
    		double distanceY = (Math.abs(rechteck.get(i).getY()+14-circle.getY()));
    		if(distanceX < circle.getR()+25&&circle.getY()+14>rechteck.get(i).getY()) {
    			colissionX = true;
    		}else if(distanceY < circle.getR()+25&&circle.getY()+14>rechteck.get(i).getY()) {
    			colissionY = true;
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
    	my = my*(-1);
    }
    if(colissionX) {
    	System.out.println("Colission X");
    	mx = mx*(-1);
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