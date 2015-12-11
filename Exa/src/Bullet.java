import java.awt.geom.Point2D;
public class Bullet extends Entity{
	public String color;
	int damage;
	int maxCycles;
	int cycles;
	
	public final double DAMAGE_MULTIPLIER = .05;
	public final double DISTANCE_MULTIPLIER = .2;
	public final static double BULLET_SPEED = 10;
	
	public Bullet(int attackPotential, Point2D.Double location, double angle, String color){
		super(location,new Point2D.Double(BULLET_SPEED * -Math.cos(angle * Math.PI / 180), BULLET_SPEED * Math.sin(angle * Math.PI / 180)),angle);
		this.color = color;
		damage = (int) (attackPotential * DAMAGE_MULTIPLIER);
		maxCycles = 75 + (int)(attackPotential * DISTANCE_MULTIPLIER);
	}
	public void takeDamage(int damage){
		System.out.println("Taking Damage");
		remove = true;
	}
	public int getDamage(){
		return damage;
	}
	public void updateLocation(){
		super.updateLocation();
		cycles ++;
		if(cycles >= maxCycles){
			remove = true;
		}
		
	}
	
	public Bullet(BulletMessage bull){
		super(bull);
		cycles = bull.cycles;
		maxCycles = bull.maxCycles;
		
	}
	public void setID(int i){
		super.ID = i;
	}
	public void addForce(Point2D.Double p){
		//Overriding this method to make it empty, bullets don't change direction
	}
	public void rotate(double angle){
		//Overriding to do nothing because bullets don't rotate
	}
	public String getType(){
		return "Bullet";
	}
	public void loadImage(){
		image = Constants.images[6];
	}
	public boolean isBullet(){
		return true;
	}
}
