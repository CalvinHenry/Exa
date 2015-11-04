import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Entity{
	
	private Point2D.Double location = new Point2D.Double(5000, 5000); //(x, y)
	public double entityAngle = 0; //angle the ship faces
	private double maxVelocity = 7;
	private AffineTransform transform;
	private String imageType = "Qufeb";
	private Point2D.Double resultant = new Point2D.Double(0,0);
	private Point2D.Double difference = new Point2D.Double(0,0); // The distance separating the client and server entity, only stored in the client, not used in server
	private double shipAngleDifference = 0; //Same as above
	public int ID;
	public boolean inSync = false;
	public BufferedImage image;
	
	
	public void loadImage(){
		if(image != null){
		
		}
		image = Constants.images[4];
	}
	
	public void setLocation(double x, double y){
		this.location.x = x;
		this.location.y = y;
	}
	public void setID(int i){
		ID = i;
	}
	public boolean entityEquals(Entity e){
		return ID == e.ID;
	}
	
	public void setResultant(Point2D.Double r){
		this.resultant.setLocation(r);
	}
	public Point2D.Double getLocation(){
		return location;
	}
	public Point2D.Double getResultant(){
		return resultant;
	}
	
	public Entity(Message m){
		location = new Point2D.Double(m.location.getX(), m.location.getY());
		resultant = new Point2D.Double(m.resultant.getX(), m.resultant.getY());
		entityAngle = m.shipAngle;
		ID = m.ID;
		loadImage();
	}
	public void set(Entity e){
		location.setLocation(e.getLocation());
		resultant.setLocation(e.getResultant());
		entityAngle = e.getEntityAngle();
	}
	
	public Entity(){
		loadImage();
	}
	
	public String getImageType(){
		return imageType;
	}
	public void setDifference(Point2D.Double differnce){
		difference = differnce;
	}
	
	public void addForce(Point2D.Double force){
		
		resultant.x += force.getX();
		resultant.y -= force.getY();
		if (Math.abs(getMagnitude()) > maxVelocity){
			resultant.x = resultant.x / getMagnitude() * maxVelocity;
			resultant.y = resultant.y / getMagnitude() * maxVelocity;
		}
	}
	public void applyBrake(double amount){
		resultant.x = approachZero(resultant.x, amount);
		resultant.y = approachZero(resultant.y, amount);
		
	}
	
	void updateLocation(){
		location.x += resultant.x;
		location.y += resultant.y;
		
		location.x -= difference.x / Constants.Socket.CYCLES_TO_SERVER_UPDATE;
		location.y -= difference.y / Constants.Socket.CYCLES_TO_SERVER_UPDATE;
		
		entityAngle -= shipAngleDifference /Constants.Socket.CYCLES_TO_SERVER_UPDATE;
		
		updateTransform();
	}
	
	public void rotate(double change){
		entityAngle += change;
		/*while(entityAngle >= 360)
			entityAngle -= 360;
		while(entityAngle <= 0)
			entityAngle += 360;
			*/
		
		System.out.println("Angle: " + entityAngle);
		updateTransform();
		
	}
	public void setAngleDifference(double diff){
		shipAngleDifference = diff;
	}
	public double getXLocation(){
		return location.getX();
	}
	public double getYLocation(){
		return location.getY();
	}
	public int getImageHeight(){
		loadImage();
		return image.getHeight();
	}
	public int getImageWidth(){
		loadImage();
		return image.getWidth();
	}
	
	public double getMagnitude(){
		return Math.sqrt(Math.pow(resultant.getX(), 2) + Math.pow(resultant.getY(), 2));
	}
	
	public double getResultant(String param){
		if(param.equals("X")) return resultant.getX();
		if(param.equals("Y")) return resultant.getY();
		return 0.00;
	}
	public double getEntityAngle(){
		return entityAngle;
	}
	private void updateTransform(){
		
		
			loadImage();
			
		transform = AffineTransform.getRotateInstance(Math.toRadians(entityAngle), image.getWidth()/2,image.getHeight()/2);
		
	}
	public BufferedImage getImage(){
		loadImage();
		updateTransform();
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		
		return  op.filter(image, null);
	}
	/*
	 * Returns -1 if x is positive, and positive 1 if x is negative
	 */
	public static double getSign(double x){
		return Math.abs(x)/ x;
	}
	public static double approachZero(double num, double increment){
		if(Math.abs(num) < increment){
			num = 0;	
		}else{
			num -= increment * getSign(num);
		}
		return num;
	}
	public Entity copy(){
		Entity temp = new Entity();
		temp.entityAngle = entityAngle;
		temp.location = location;
		temp.resultant = resultant;
		temp.imageType = imageType;
		temp.ID = ID;
		return temp;
	}
	
	
}