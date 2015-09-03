import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {
	
	private Point location; //(x, y)
	private Point momentum; //(velocity, r[degrees])
	private AffineTransform transform;
	private BufferedImage image;
	
	public Entity(){
		try {
			image = ImageIO.read(new File((getClass().getResource("Recourses/Qufeb.png").getPath())));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		location = new Point(500,500);
		momentum = new Point(0,0);
	}
	
	public void updateLocation(){ //moves object according to momentum vector
		location.x += (momentum.x * Math.cos(Math.toRadians(momentum.y)));
		location.y -= momentum.x * Math.sin(Math.toRadians(momentum.y));
		updateTransform();
		System.out.println(location);
		System.out.println("Momentum: " + momentum);
	}
	public void accelerate(double change){
		momentum.x += change;
		if(momentum.x > 0){
			momentum.x = 0;
		}
	}
	public void rotate(double change){
		momentum.y += change;
		while(momentum.y >= 360)
			momentum.y -= 360;
		updateTransform();
	}
	public int getXLocation(){
		return location.x;
	}
	public int getYLocation(){
		return location.y;
	}
	private void updateTransform(){
		transform = AffineTransform.getRotateInstance(Math.toRadians(momentum.y), image.getWidth()/2,image.getHeight()/2);
	}
	public BufferedImage getImage(){
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return  op.filter(image, null);
	}
	
}
