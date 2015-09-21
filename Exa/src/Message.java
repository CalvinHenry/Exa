import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Point2D.Double location = new Point2D.Double(500, 500); //(x, y)
	public double shipAngle = 0; //angle the ship faces
	public Point2D.Double resultant = new Point2D.Double(0,0);
	public String entityType;
	
	public Message(Entity e){
		location = e.getLocation();
		resultant = e.getResultant();
		shipAngle = e.getEntityAngle();
	}
}
