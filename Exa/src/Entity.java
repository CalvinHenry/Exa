import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Entity {
	
	private Point location;
	private Point momentum;
	private AffineTransform transform;
	private BufferedImage image;
	
	public Entity(){
		
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
