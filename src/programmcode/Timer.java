package programmcode;



public class Timer implements Runnable{
	private boolean running;
	
	public static final int FPS = 144;
	public static final int TPS = 128;
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void tick() {
		
	}
	
	public void render() {
		System.out.println("Test");
	}
	
	@Override
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerFrame = 1000000000D/FPS;
		double nsPerTick = 1000000000D/TPS;
		double deltaF = 0;
		double deltaT = 0;
		while(running) {
			long now = System.nanoTime();
			deltaF += (now-lastTime);
			deltaT += (now-lastTime);
			while(deltaF>1) {
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
