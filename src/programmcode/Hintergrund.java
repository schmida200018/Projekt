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
  private ArrayList<Rechteck> rechteck = new ArrayList<Rechteck>();
  private boolean laeuft = false;
  private ArrayList<Kreissteuerung> kreis = new ArrayList<Kreissteuerung>();
  private int Kreise = 1;
  private double KreisX = Toolkit.getDefaultToolkit().getScreenSize().width/2;
  private boolean erstes = false;
  private long delay = 0;
  
  
  public static final int FPS = 144;
  public static final int TPS = 1280;

  public Hintergrund() {
	  screen = new Screen(this);
	  
  }
  
  public synchronized void start(){
    running = true;
    new Thread(this).start();
  }
  
  public void setDelay(int pDelay) {
	 delay = pDelay*128/1000;
  }
  
  public int getDelay() {
	  return (int)delay;
  }
  
  public void setLaeuft(boolean pLaeuft) {
	  laeuft = pLaeuft;
	  erstes = false;
	  kreise();
	  System.out.println("Start");
  }
  
  public synchronized void stop(){
    running = false;
  }
  
  public void init(){
    rechtecke();
  }
  
  public void kreise() {
	  
		  kreis.add(new Kreissteuerung(new Kreis(KreisX,Toolkit.getDefaultToolkit().getScreenSize().height-100,20,0,-1),this));
	  
	  
  }
  
  public void rechtecke() {  
	  
	  rechteck.add(new Rechteck(Toolkit.getDefaultToolkit().getScreenSize().width/2+9,Toolkit.getDefaultToolkit().getScreenSize().height-200,50,50,10));
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
    
    for(int i = 0; i<kreis.size();i++) {
    	Kreis pKreis = kreis.get(i).getKreis();
    	g.fillOval((int)(pKreis.getX()-pKreis.getRr()),(int)(pKreis.getY()-pKreis.getRr()), pKreis.getR(),pKreis.getR() );
    	//System.out.println(pKreis.getX()+";" + pKreis.getY()+";" + pKreis.getR());
    }
    if(erstes) {
    	g.fillOval((int)KreisX,(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100, 20, 20);
    }
    //Render ende
    g.setColor(Color.blue);
    g.dispose();
    bs.show();
    
  }
  
  public void tick() {
	if(laeuft) {
	if(delay != 0) {
		delay--;
	}
    for(int j = 0; j < kreis.size(); j++) {
	for(int i = 0; i < rechteck.size(); i++) { 
		Rechteck recht = kreis.get(j).kollision(rechteck.get(i));	
		rechteck.set(i, recht);
		if(rechteck.get(i).istLebenNull()) {
			rechteck.remove(i);
		}
		if(!kreis.get(j).istDrin()) {
			if(!erstes) {
			KreisX = kreis.get(j).getKreis().getX();
			erstes = true;
			}
			kreis.remove(j);
		}
    }
    			if(!laeuft) {
    				System.out.println("Ende");
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