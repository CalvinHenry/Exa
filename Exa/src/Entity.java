import java.awt.Point;

public abstract class Entity {
	
	private Point location;
	private Point momentum;
	
	public void updateLocation(){ //moves object according to momentum vector
		location.x += momentum.x *Math.cos(momentum.y);
		location.y += momentum.x *Math.sin(momentum.y);
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
	}
	
	
}
