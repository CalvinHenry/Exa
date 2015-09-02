import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Entity {
	
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
	}
	
	public void updateLocation(){ //moves object according to momentum vector
		location.x += momentum.x *Math.cos(momentum.y);
		location.y += momentum.x *Math.sin(momentum.y);
		updateTransform();
	}
	public void accelerate(double change){
		momentum.x += change;
		if(momentum.x < 0){
			momentum.x = 0;
		}
	}
	public void rotate(double change){
		momentum.y += change;
		while(momentum.y >= 360)
			momentum.y -= 360;
		updateTransform();
	}
	
	private void updateTransform(){
		transform = AffineTransform.getRotateInstance(momentum.y,location.x,location.y);
	}
	public BufferedImage getImage(){
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return  op.filter(image, null);
	}
	
}
