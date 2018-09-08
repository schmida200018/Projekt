package programmcode;

import java.awt.Toolkit;

public class Kreissteuerung {
	private Kreis kreis;
	private Rechteck rechteck;
	private double x,y;
	private boolean istDrin = true;
	private Hintergrund hintergrund;
	private long delay = 0;
	private boolean anderesRechteck;
	private double pPX,pPY;
	private boolean move = false;
	
	public Kreissteuerung( Kreis pKreis, Hintergrund pHintergrund ) {
	kreis = pKreis;
	hintergrund = pHintergrund;
	}
	public long getDelay() {
		return delay;
	}
	public void verminderDelay() {
		delay--;
	}
	public void neuerTick() {
		move = false;
	}
	
	public Kreis getKreis() {
		return kreis;
	}
	
	public boolean istDrin() {
		return istDrin;
	}
	
	public Rechteck[][] move(Rechteck[][] pSpielfeld) {
		x = kreis.getX();
		x = kreis.getX();
		y = kreis.getY();
		x = x+kreis.getMx();
		y = y+kreis.getMy();
		kreis.setX(x);
		kreis.setY(y);
		boolean colissionX =  false;
	    boolean colissionY = false;
	    boolean ende = false;
		if(kreis.getX() <=kreis.getR()/2+Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-500) {
			colissionX = true;
		}
		if(kreis.getX() >= Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2+500-kreis.getRr()) {
			colissionX = true;
		}
		if(kreis.getY() <= kreis.getR()/2) {
			colissionY = true;
		}
		if(kreis.getY() >= Toolkit.getDefaultToolkit().getScreenSize().getHeight()-90) {
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
			for(int i = 0; i<10; i++) {
				for(int j = 0; j<10; j++) {
					try {
					if(pSpielfeld[i][j]!=null) {
						Rechteck recht = kollision(pSpielfeld[i][j]);
						pSpielfeld[i][j] = recht;
						//rechteckEx = true;
						if(pSpielfeld[i][j].istLebenNull()) {
							pSpielfeld[i][j] = null;
						}
					}
					}catch(Exception e) {
						
					}
						
				
			}
			}
			
	    return pSpielfeld;
	}
	
	public Rechteck kollision(Rechteck pRechteck) {
		rechteck = pRechteck; 
		boolean colission = false;
	    boolean colissionX =  false;
	    boolean colissionY = false;
	    boolean ende = false;
	    
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
	    		System.out.println("col1");
	    		kreis = handleIntersection(rechteck, kreis, 1);
	    		colission = true;
	    	}
	    	if(distanceR2 <(kreis.getR()/2)*(kreis.getR()/2)) {
	    		//System.out.println(""+distanceX+";"+distanceY);
	    		System.out.println("col2");
	    		kreis = handleIntersection(rechteck, new Kreis(kreis.getX()-rechteck.getWidth(),kreis.getY(),kreis.getR(),kreis.getMx(),kreis.getMy()),2);
	    		kreis.setX(kreis.getX()+rechteck.getWidth());
	    		colission = true;
	    	}
	    	if(distanceR3 <(kreis.getR()/2)*(kreis.getR()/2)) {
	    		//System.out.println(""+distanceX+";"+distanceY);
	    		System.out.println("col3");
	    		kreis = handleIntersection(rechteck, new Kreis(kreis.getX(),kreis.getY()-rechteck.getHeight(),kreis.getR(),kreis.getMx(),kreis.getMy()),3);
	    		kreis.setY(kreis.getY()+rechteck.getHeight());
	    		colission = true;
	    	}
	    	if(distanceR4 <(kreis.getR()/2)*(kreis.getR()/2)) {
	    		//System.out.println(""+distanceX+";"+distanceY);
	    		System.out.println("col4");
	    		kreis = handleIntersection(rechteck, new Kreis(kreis.getX()-rechteck.getWidth(),kreis.getY()-rechteck.getHeight(),kreis.getR(),kreis.getMx(),kreis.getMy()),4);
	    		kreis.setY(kreis.getY()+rechteck.getHeight());
	    		kreis.setX(kreis.getX()+rechteck.getWidth());
	    		colission = true;
	    	}
	    	
	    }
	    if(colission) {
	    	rechteck.verliereLeben();
			System.out.println("verliere Leben");
			pPX = rechteck.getX();
			pPY = rechteck.getY();
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
	    return rechteck;
	}
	public Kreis handleIntersection(Rechteck rechteck, Kreis kreis, int fall){

        final double dx = kreis.getMx()*100;
        final double dy = kreis.getMy()*100;
    
        double inverseRadius = 1.0 / (kreis.getRr());
        double lineLength = Math.sqrt(dx*dx  + dy*dy );
        double cornerdx =  rechteck.getX()- kreis.getX();
        double cornerdy = rechteck.getY()-kreis.getY();
        double cornerDistance = Math.sqrt( cornerdx * cornerdx + cornerdy * cornerdy );
        double innerAngle = Math.acos( (cornerdx * dx + cornerdy * dy) / (lineLength * cornerDistance) );
       
        
        // If inner angle is zero, it's going to hit the corner straight on.
        double innerAngleSin = Math.sin( innerAngle );
        double angle1Sin = innerAngleSin * cornerDistance * inverseRadius;
 
        double angle1 = Math.PI - Math.asin( angle1Sin );
        double angle2 = Math.PI - innerAngle - angle1;
        double intersectionDistance = kreis.getRr() * Math.sin( angle2 ) / innerAngleSin;
        
        // Solve for time
        float time = (float)(intersectionDistance / lineLength);
        
        // Solve the intersection and normal
        double ix = time * dx + kreis.getX();
        double iy = time * dy + kreis.getY();
       
        double nx,ny;
       
        nx = (float)((ix - rechteck.getX()) *inverseRadius);
        ny = (float)((iy - rechteck.getY()) *inverseRadius);
        double dot = dx-2*nx+dy*ny;
        double ndx = dx-2*dot*nx;
        double ndy = dy-2*dot*ny;
        
        System.out.println(rechteck.getX()+";"+rechteck.getY()+";"+kreis.getX()+";"+kreis.getY());
        
        while(true) {
        	if(ndx*ndx+ndy*ndy>1) {
        	if(Math.sqrt(((ndx*ndx)+(ndy*ndy)))>1){
        		
        			ndx = ndx/ 1.001;
        			ndy = ndy/1.001;
        		
        	}
        	}else if(ndx*ndx+ndy*ndy<0.95){
        			ndx = ndx*1.001;
        			ndy = ndy*1.001;
        	}else{
        		break;
        	}
        }while(true) {
        	if(nx*nx+ny*ny>1) {
        	if(Math.sqrt(((nx*nx)+(ny*ny)))>1){
        		
        			nx = nx/ 1.001;
        			ny = ny/1.001;
        		
        	}
        	}else if(nx*nx+ny*ny<0.95){
        			nx = nx*1.001;
        			ny = ny*1.001;
        	}else{
        		break;
        	}
        }
        
        
        System.out.println(nx +";"+ny + ";" + "KreisInter");

        return new Kreis( ix, iy, kreis.getR(), nx, ny);
    }	
}
