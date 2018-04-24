package programmcode;

public class Intersect {
	public Kreis handleIntersection(Rechteck rechteck, Kreis kreis, int fall)
    {

        final double dx = kreis.getMx();
        final double dy = kreis.getMy();
    
        double inverseRadius = 1.0 / kreis.getRr();
        double lineLength = Math.sqrt(dx*dx  + dy*dy );
        double cornerdx =  rechteck.getX()- kreis.getX();
        double cornerdy = rechteck.getY()-kreis.getY();
        double cornerDistance = Math.sqrt( cornerdx * cornerdx + cornerdy * cornerdy );
        double innerAngle = Math.acos( (cornerdx * dx + cornerdy * dy) / (lineLength * cornerDistance) );
        
        final float L = rechteck.getHeight();
        final float T = rechteck.getWidth();
        final float R = rechteck.getHeight();
        final float B = rechteck.getWidth();
        
        float cornerX = Float.MAX_VALUE;
        float cornerY = Float.MAX_VALUE;
        if(fall == 1) {
        	cornerX = L;
        }else if(fall == 2) {
        	cornerY = T;
        }else if(fall == 3) {
        	cornerX = R;
        }else if(fall == 4) {
        	cornerY = B;
        }
        
       
        // If the circle is too close, no intersection.
        System.out.println("test");
        // If inner angle is zero, it's going to hit the corner straight on.
        double innerAngleSin = Math.sin( innerAngle );
        double angle1Sin = innerAngleSin * cornerDistance * inverseRadius;

        // The angle is too large, there cannot be an intersection
        
        double angle1 = Math.PI - Math.asin( angle1Sin );
        double angle2 = Math.PI - innerAngle - angle1;
        double intersectionDistance = kreis.getRr() * Math.sin( angle2 ) / innerAngleSin;
        
        // Solve for time
        float time = (float)(intersectionDistance / lineLength);
        // If time is outside the boundaries, return null. This algorithm can
        // return a negative time which indicates a previous intersection, and
        // can also return a time > 1.0f which can predict a corner intersection.
       System.out.println(angle1+";"+angle2);
       System.out.println(innerAngle);
        
        if (innerAngle == 0.0f)
        {
           time = (float)((cornerDistance - kreis.getRr()) / lineLength);
           
           // If time is outside the boundaries, return null. This algorithm can
           // return a negative time which indicates a previous intersection, and
           // can also return a time > 1.0f which can predict a corner intersection.
           
           double ix = time * dx + kreis.getX();
           double iy = time * dy + kreis.getY();
           float nx = (float)(cornerdx / cornerDistance);
           float ny = (float)(cornerdy / cornerDistance);
           return new Kreis( ix, iy, kreis.getR(), nx, ny);
        }
        // Solve the intersection and normal
        double ix = time * dx + kreis.getX();
        double iy = time * dy + kreis.getY();
        double nx,ny;
        
       
        nx = (float)((ix - rechteck.getX()) *Math.sin(innerAngle*Math.PI/2.3)/*-Math.toDegrees(angle2)/240*/ );
        ny = (float)((iy - rechteck.getY()) *Math.cos(innerAngle*Math.PI/2.3)/*-Math.toDegrees(angle1)/240*/ );
        
        
        System.out.println(angle2+";"+angle1);
        while(true) {
        	if(nx*nx+ny*ny>1) {
        	if(Math.sqrt(((nx*nx)+(ny*ny)))>1){
        		
        			nx = nx/ 1.001;
        			ny = ny/1.001;
        		
        	}
        	}else {
        		break;
        	}
        }
        return new Kreis( ix, iy, kreis.getR(), nx, ny);
    }
}
