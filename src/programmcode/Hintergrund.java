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
  private boolean laeuft = true;
  private ArrayList<Kreissteuerung> kreis = new ArrayList<Kreissteuerung>();
  private int kreise = 1;
  private double KreisX = Toolkit.getDefaultToolkit().getScreenSize().width/2;
  private Rechteck[][] spielfeld = new Rechteck[10][10];
  private boolean erstes = true;
  private boolean links,rechts,space;
  private KeyEvt keyevt;
  public static int FPS = 144;
  public static int TPS = 1280;
  private double mx = 0;
  private double my = -1;
  private int radius = 20;
  private boolean erster = true;
  private double nsPerFrame = 1000000000D/FPS;
  private double nsPerTick = 1000000000D/TPS;
  private boolean neueRunde = false;
  
  	
  public Hintergrund() {	  
	  keyevt = new KeyEvt(this);
	  screen = new Screen(this, keyevt);
  }
  
  public synchronized void start(){
    running = true;
    new Thread(this).start();
  }
  
  public void setErster(boolean pE) {
	  erster = pE;
  }
  
  public void tickMalZehn() {
	  if(erster){
	  TPS = TPS*10;
	  erster = false;
	  }
	  nsPerTick = 1000000000D/TPS;
	  System.out.println(TPS);
  }
 public void tickDurchZehn() {
	  TPS = TPS/10;
	  nsPerTick = 1000000000D/TPS;
	  System.out.println(TPS);
	  erster = true;
  }
 private void spielEnde() {
	 for(int i = 0; i<10; i++) {
		 for(int j = 0; j<10; j++) {
			 spielfeld[i][j]=null;
		 }
	 }
	 kreise = 1;
	 laeuft = false;
	 System.out.println("Spielende");
 }
 
 private void neueRunde() {
	 
	 for(int i = 0; i<10; i++) {
		 if(spielfeld[9][i]!=null) {
			 System.out.println("Ende");
			 spielEnde();
		 }
	 }
	 for(int i = 0; i<10; i++) {
		 for(int j = 9; j>=0; j--) {
			 if(spielfeld[j][i]!=null) {
				
				spielfeld[j+1][i]= new Rechteck((int)spielfeld[j][i].getX(),10+(j+1)*100,50,50,spielfeld[j][i].getLeben());
				spielfeld[j][i]=null;
			 }
		 }
	 }
	 
	 int anzahlRechtecke = (int)(Math.random()*10);
	 System.out.println(anzahlRechtecke);
	 for(int i = 0; i < anzahlRechtecke; i++) {
		 int posRechteck = (int)(Math.random()*10);
		 if(spielfeld[0][posRechteck]== null) {
		 spielfeld[0][posRechteck] = new Rechteck(100*posRechteck+(int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-500),10,50,50,kreise);
		 }else {
			 i--;
		 }
	 }
 }
  
  public void setLaeuft() {
	  if(!laeuft) {
	  laeuft = true;
	  erstes = false;
	  kreise();
	  System.out.println("Start");
	  kreise++;
	  neueRunde = false;
	  }
  }
  
  public synchronized void stop(){
    running = false;
  }
  
  public void init(){
    //rechtecke();
    
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
	  //neueRunde();

	  for(int i = 0; i<kreise; i++) {
		  kreis.add(new Kreissteuerung(new Kreis(KreisX,Toolkit.getDefaultToolkit().getScreenSize().height-100,radius,mx,my),this));
		  try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
	  }
	  
  }
  
  public void rechtecke() {  
	  
	  rechteck.add(new Rechteck(Toolkit.getDefaultToolkit().getScreenSize().width/2+9 ,Toolkit.getDefaultToolkit().getScreenSize().height-200,50,50,10));
	  rechteck.add(new Rechteck(Toolkit.getDefaultToolkit().getScreenSize().width/2+100,Toolkit.getDefaultToolkit().getScreenSize().height-155,50,50,10));
	  rechteck.add(new Rechteck(Toolkit.getDefaultToolkit().getScreenSize().width/2+100,10,50,50,10));
  }
  
  public void render() {
    BufferStrategy bs = screen.getBufferStrategy();
    if(bs == null){
      screen.createBufferStrategy(3);
      return;
      }
    Graphics g = bs.getDrawGraphics();
    g.clearRect(0,0,screen.getWidth(),screen.getHeight());
    //Rechtecke
    for(int i = 0; i < rechteck.size(); i++){
    	g.drawRect((int)rechteck.get(i).getX(),(int)rechteck.get(i).getY(),rechteck.get(i).getWidth(),rechteck.get(i).getHeight());
    	g.drawString(""+rechteck.get(i).getLeben(), (int)rechteck.get(i).getX()+17, (int)rechteck.get(i).getY()+29);
    }
    for(int i = 0; i<10; i++) {
    	for(int j = 0; j<10; j++) {
    		if(spielfeld[i][j] != null) {
    			g.drawRect((int)spielfeld[i][j].getX(),(int)spielfeld[i][j].getY(),(int)spielfeld[i][j].getWidth(), (int)spielfeld[i][j].getHeight());
    			g.drawString(""+(int)spielfeld[i][j].getLeben(), (int)spielfeld[i][j].getX()+17, (int)spielfeld[i][j].getY()+29);
    		}
    	}
    }
    //Spielferlbegrenzung
    g.drawLine((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-500), 0,(int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-500), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    g.drawLine((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2+500), 0,(int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2+500), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    //Kreis wenn Spiel zu ende
    if(!laeuft) {
    	g.drawLine((int)KreisX,(int) (Toolkit.getDefaultToolkit().getScreenSize().height-100),(int) (KreisX + mx * 30 ),(int) (Toolkit.getDefaultToolkit().getScreenSize().height-100 + my *30));
    	g.fillOval((int)KreisX-10,(int) (Toolkit.getDefaultToolkit().getScreenSize().height-110), radius , radius);
    	int[] x = {(int)(KreisX+3+mx*30),(int)(KreisX-3+mx*30),(int)(KreisX+mx*30)};
    	int[] y = {(int)(Toolkit.getDefaultToolkit().getScreenSize().height-100+my*30),(int)(Toolkit.getDefaultToolkit().getScreenSize().height-100+my*30),(int)(Toolkit.getDefaultToolkit().getScreenSize().height-105+my*30)};
    	g.drawPolygon(x, y, 3);
    }
    //Kreise
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
	  
	  if(laeuft) {
		  	System.out.println(TPS);
	  }
	  if(kreis.size()==0) {
		if(!space) {
		  laeuft = false;
		}
		if(!neueRunde) {
			neueRunde();
			neueRunde = true;
		}
	  }
	  
	if(laeuft) {
		System.out.println("Läuft");
	for(int i = 0; i< kreis.size(); i++) {
		if(kreis.get(i).getDelay()!=0){
			kreis.get(i).verminderDelay();
		}
	}
	boolean rechteckEx = false;
	
	for(int i = 0; i<kreis.size(); i++) {
		spielfeld = kreis.get(i).move(spielfeld);
		System.out.println("Move");
		try {
			if(!kreis.get(i).istDrin()) {
				if(!erstes) {
				KreisX = kreis.get(i).getKreis().getX();
				erstes = true;
				}
				kreis.remove(i);
				System.out.println("Ende");
			}
			}catch(Exception f) {

			}
	}
	
	
	for(int j = 0; j < kreis.size(); j++) {
	for(int i = 0; i < rechteck.size(); i++) { 
		try{
		Rechteck recht = kreis.get(j).kollision(rechteck.get(i));	
		rechteck.set(i, recht);
		}catch(Exception e) {
			System.out.println("Test");
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
			System.out.println("Ende");
		}
		}catch(Exception e) {
			//System.out.println("test");
		}
    }
    			if(!laeuft) {
    				System.out.println("Ende");
    			}
	
    
			}
		
	}
	for(int i = 0; i<kreis.size(); i++) {
		kreis.get(i).neuerTick();
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