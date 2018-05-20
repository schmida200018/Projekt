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
  private int kreise = 1;
  private double KreisX = Toolkit.getDefaultToolkit().getScreenSize().width/2;
  private boolean erstes = true;
  private boolean links,rechts,space;
  private KeyEvt keyevt;
  public static int FPS = 144;
  public static int TPS = 128;
  private double mx = 0;
  private double my = -1;
  private int radius = 20;

  private double nsPerFrame = 1000000000D/FPS;
  private double nsPerTick = 1000000000D/TPS;

  public Hintergrund() {	  
	  keyevt = new KeyEvt(this);
	  screen = new Screen(this, keyevt);
  }
  
  public synchronized void start(){
    running = true;
    new Thread(this).start();
  }
  
  public void tickMalZehn() {
	  TPS = TPS+100;
	  nsPerTick = 1000000000D/TPS;
  }
  public void tickDurchZehn() {
	  TPS = TPS-100;
	  nsPerTick = 1000000000D/TPS;
  }
  
  
  public void setLaeuft(boolean pLaeuft) {
	  if(!laeuft) {
	  laeuft = pLaeuft;
	  erstes = false;
	  kreise();
	  System.out.println("Start");
	  kreise++;
	  }
  }
  
  public synchronized void stop(){
    running = false;
  }
  
  public void init(){
    rechtecke();
  }
  
  public void links() {
	  if(my<-0.35) {
	  mx = mx - 0.05;
	  System.out.println(my);
	  while(true) {
		  if((mx*mx+my*my)>1) {
			  my = my/1.01;
		  }else if(mx*mx+my*my<0.96) {
			  my = my*1.01;
		  }else {
			  break;
		  }
	  }
	  }else if(mx>0.15) {
		  mx = mx - 0.05;
		  my = my - 0.05;
		  while(true) {
			  if((mx*mx+my*my)>1) {
				  my = my/1.01;
			  }else if(mx*mx+my*my<0.96) {
				  my = my*1.01;
				  System.out.println("1;"+(mx*mx+my*my));
				  System.out.println(mx+";"+my);
			  }else {
				  break;
			  }
		  }
		  }
	  System.out.println(mx+";"+my);
  }
  public void rechts() {
	  if(my<-0.35) {
	  mx = mx + 0.05;
	  System.out.println(my);
	  while(true) {
		  if(mx*mx+my*my>1) {
			  my = my/1.01;
		  }else if(mx*mx+my*my <0.96) {
			  my = my*1.01;
		  }else {
			  break;
		  }
	  }
	  }else if(mx<0.15) {
		  mx = mx + 0.05;
		  my = my -0.05;
		  while(true) {
			  if((mx*mx+my*my)>1) {
				  my = my/1.01;
				  System.out.println("2;"+(mx*mx+my*my));
				  System.out.println(mx+";"+my);
			  }else if(mx*mx+my*my<0.96) {
				  my = my*1.01;
				  System.out.println("1;"+(mx*mx+my*my));
				  System.out.println(mx+";"+my);
			  }else {
				  break;
			  }
		  }
		  }
	  System.out.println(mx+";"+my);
  }
  
  public void kreise() {
	  for(int i = 0; i<kreise; i++) {
		  kreis.add(new Kreissteuerung(new Kreis(KreisX,Toolkit.getDefaultToolkit().getScreenSize().height-100,radius,mx,my),this));
		  try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
	  }
	  
  }
  
  public void rechtecke() {  
	  
	  rechteck.add(new Rechteck(Toolkit.getDefaultToolkit().getScreenSize().width/2+9 ,Toolkit.getDefaultToolkit().getScreenSize().height-200,50,50,10));
	  rechteck.add(new Rechteck(Toolkit.getDefaultToolkit().getScreenSize().width/2+100,Toolkit.getDefaultToolkit().getScreenSize().height-155,50,50,10));
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
    if(!laeuft) {
    	g.drawLine((int)KreisX,(int) (Toolkit.getDefaultToolkit().getScreenSize().height-100),(int) (KreisX + mx * 30 ),(int) (Toolkit.getDefaultToolkit().getScreenSize().height-100 + my *30));
    	g.fillOval((int)KreisX-10,(int) (Toolkit.getDefaultToolkit().getScreenSize().height-110), radius , radius);
    	int[] x = {(int)(KreisX+3+mx*30),(int)(KreisX-3+mx*30),(int)(KreisX+mx*30)};
    	int[] y = {(int)(Toolkit.getDefaultToolkit().getScreenSize().height-100+my*30),(int)(Toolkit.getDefaultToolkit().getScreenSize().height-100+my*30),(int)(Toolkit.getDefaultToolkit().getScreenSize().height-105+my*30)};
    	g.drawPolygon(x, y, 3);
    }
    for(int i = 0; i<kreis.size();i++) {
    	Kreis pKreis = kreis.get(i).getKreis();
    	g.fillOval((int)(pKreis.getX()-pKreis.getRr()),(int)(pKreis.getY()-pKreis.getRr()), pKreis.getR(),pKreis.getR() );
    	//System.out.println(pKreis.getX()+";" + pKreis.getY()+";" + pKreis.getR());
    }
    //if(erstes) {
    //	g.fillOval((int)KreisX,(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100, 20, 20);
    //}
    //Render ende
    g.setColor(Color.blue);
    g.dispose();
    bs.show();
    
  }
  
  public void tick() {
	if(space) {
		System.out.println("space");
	}
	if(kreis.size()==0) {
		laeuft = false;
	}
	if(laeuft) {
	for(int i = 0; i< kreis.size(); i++) {
		if(kreis.get(i).getDelay()!=0){
			kreis.get(i).verminderDelay();
		}
	}
    for(int j = 0; j < kreis.size(); j++) {
	for(int i = 0; i < rechteck.size(); i++) { 
		try{
		Rechteck recht = kreis.get(j).kollision(rechteck.get(i));	
		rechteck.set(i, recht);
		}catch(Exception e) {
			System.out.println("test");
		}
		if(rechteck.get(i).istLebenNull()) {
			rechteck.remove(i);
		}
		try {
		if(!kreis.get(j).istDrin()) {
			if(!erstes) {
			KreisX = kreis.get(j).getKreis().getX();
			erstes = true;
			}
			kreis.remove(j);
		}
		}catch(Exception e) {
			System.out.println("test");
		}
    }
    			if(!laeuft) {
    				System.out.println("Ende");
    			}
	
    
			}
		
	}
  }
  public void setLinks() {
  	links = true;
  }
  public void setRechts() {
  	rechts = true;
  }
  public void setSpace() {
  	space = true;
  }
  public void delLinks() {
	  links = false;
  }
  public void delRechts() {
	  rechts = false;
  }
  public void delSpace() {
	  space = false;
  }
  
  public void run(){
    init();
    long lastTime = System.nanoTime();
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