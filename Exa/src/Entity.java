import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {
	
	private Point2D.Double location = new Point2D.Double(500, 500); //(x, y)
	public double shipAngle = 0; //angle the ship faces
	private double maxVelocity = 5;
	private AffineTransform transform;
	private BufferedImage image;
	private Point2D.Double resultant = new Point2D.Double(0,0);
	
	public Entity(){
		try {
			image = ImageIO.read(new File((getClass().getResource("Recourses/Qufeb.png").getPath())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addForce(Point2D.Double force){
		
		resultant.x += force.getX();
		resultant.y -= force.getY();
		while(Math.abs(getResultant("")) >= maxVelocity){
			resultant.x = approachZero(resultant.x, .1);
			resultant.y = approachZero(resultant.y, .1);
			
		}
	}
	public void applyBrake(double magnitude){
		resultant.x = approachZero(resultant.x, magnitude);
		resultant.y = approachZero(resultant.y, magnitude);
		
	}
	
	void updateLocation(){
		location.x += resultant.x;
		location.y += resultant.y;
		System.out.print("x velocity: " + resultant.x);
		System.out.println("    y velocity: " + resultant.y);
		System.out.print("X Loc: " + location.x);
		System.out.println("    Y Loc: " + location.y);
		
		updateTransform();
	}
	
	public void rotate(double change){
		shipAngle += change;
		while(shipAngle >= 360)
			shipAngle -= 360;
		while(shipAngle <= 0)
			shipAngle += 360;
		
		System.out.println("Angle: " + shipAngle);
		updateTransform();
		
	}
	public double getXLocation(){
		return location.getX();
	}
	public double getYLocation(){
		return location.getY();
	}
	public double getResultant(String param){
		if(param.equals("X")) return resultant.getX();
		if(param.equals("Y")) return resultant.getY();
		return Math.sqrt(Math.pow(resultant.getX(), 2) + Math.pow(resultant.getY(), 2));
	}
	public double getShipAngle(){
		return shipAngle;
	}
	private void updateTransform(){
		transform = AffineTransform.getRotateInstance(Math.toRadians(shipAngle), image.getWidth()/2,image.getHeight()/2);
	}
	public BufferedImage getImage(){
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return  op.filter(image, null);
	}
	/*
	 * Returns -1 if x is positive, and positive 1 if x is negative
	 */
	public static double getSign(double x){
		return Math.abs(x)/ x;
	}
	public static double approachZero(double num, double magnitude){
		System.out.println("here");
		if(Math.abs(num) < magnitude){
			num = 0;	
		}else{
			num -= magnitude * getSign(num);
		}
		return num;
	}
	
}

