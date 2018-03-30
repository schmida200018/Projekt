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

  public static final int FPS = 144;
  public static final int TPS = 128;

  public synchronized void start(){
    running = true;
    new Thread(this).start();
  }
  
  public synchronized void stop(){
    running = false;
  }
  
  public void init(){
    screen = new Screen();
    x=0;
    y=0;
  }
  
  public void render() {
    BufferStrategy bs = screen.getBufferStrategy();
    if(bs == null){
      screen.createBufferStrategy(3);
      return;
      }
    Graphics g = bs.getDrawGraphics();
    g.clearRect(0,0,screen.getWidth(),screen.getHeight());
    
    //Render start
    g.fillRect(x,y,50,50);
    //Render ende
    
    g.dispose();
    bs.show();
    
  }
  
  public void tick() {
    x++;
    y++;
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