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
  private int x,y; 
  private int mx, my;
  private ArrayList<Rectangle> rechteck = new ArrayList<Rectangle>();
  private boolean laeuft = false;
  private Circle circle = new Circle(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height-100,20);
  
  
  public static final int FPS = 144;
  public static final int TPS = 32;

  public Hintergrund() {
	  screen = new Screen(this);
  }
  
  public synchronized void start(){
    running = true;
    new Thread(this).start();
    mx = 0;
    my = -1;
  }
  
  public void setLaeuft(boolean pLaeuft) {
	  laeuft = pLaeuft;
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
    	g.fillRect(rechteck.get(i).x,rechteck.get(i).y,rechteck.get(i).width,rechteck.get(i).height);
    }
    
    //Render start
    
    g.fillOval(x,y, circle.getR(), circle.getR());
    
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
	for(int i = 0; i < rechteck.size(); i++) {
    	double distance = (Math.abs(rechteck.get(i).getX()-circle.getX())+Math.abs(rechteck.get(i).getY()+14-circle.getY()));
    	System.out.println(""+distance);
    	if(distance < circle.getR()+14 ) {
    		colission = true;
    		System.out.println("Colission");
    	}

    }
    if(colission) {
    	if(my == -1) {
    		my = 1;
    		colission = false;
    	}else if(my == 1) {
    		my = -1;
    		colission = false;
    	}
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