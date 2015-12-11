import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Spaceship extends Entity {
	
	public int health; //current health
	public int shields; //current shields
	public int healthPotential; //upgradable stats
	public int shieldPotential; //regeneration rate
	public int weaponPotential; //damage impacted by bullets
	public int speedPotential; //top speed
	public int fireRate;//How many ticks the ship pauses before shooting another laser
	public final int FIRE_RATE_MULTIPLIER = 100;//Multiplier for fire rate.
	public int currentCycles;
	public boolean fireButtonHeld;
		
	public Spaceship(int h, int s, int w, int speed){
		this(h,s,w,speed, new Entity());
	}
	public Spaceship(){
		this(0,0,0,0, new Entity());
	}
	public Spaceship(int h, int s, int w, int speed, Entity e){
		super(e);
		this.healthPotential = h;
		this.shieldPotential = s;
		this.weaponPotential = w;
		this.speedPotential = speed;
		setFireRate();
		fireButtonHeld = false;
		setTopSpeed(speed);
		
	}
	public Spaceship(SpaceshipMessage message){
		super(message);
		health = message.health;
		shields = message.shields;
		healthPotential = message.healthPotential;
		shieldPotential = message.shieldPotential;
		weaponPotential = message.weaponPotential;
		speedPotential = message.speedPotential;
		fireButtonHeld = message.fireButtonHeld;
		currentCycles = message.cycles;
		setFireRate();
	}
	public void set(Spaceship s){
		super.set(s);
		health = s.health;
		shields = s.shields;
		healthPotential = s.healthPotential;
		shieldPotential = s.shieldPotential;
		weaponPotential = s.weaponPotential;
		speedPotential = s.speedPotential;
		fireButtonHeld = s.fireButtonHeld;
		setFireRate();
		
	}
	public void setFireRate(){
		fireRate = 10 + (int)((1/(weaponPotential+ 1)) * FIRE_RATE_MULTIPLIER);
	}
	
	public void setFireButton(boolean b){
		fireButtonHeld = b;
	}
	public void updateLocation(){
		super.updateLocation();
		currentCycles ++;
	}
	public boolean fire(){
		return fireButtonHeld && currentCycles >= fireRate;
	}
	public void resetFireCount(){
		currentCycles = 0;
	}
	
	
	public void setValues(int a, int b, int c, int d){
		healthPotential = a;
		shieldPotential = b;
		weaponPotential = c;
		speedPotential = d;
	}
	
	public void takeDamage(int damage){
		health -= damage;
		if(health < 0){
			remove = true;
		}
	}
	public int getDamage(){
		return (int)(healthPotential * .2);
	}
	public String getType(){
		return "Spaceship";
	}
	public Spaceship copy(){
		return new Spaceship(healthPotential, shieldPotential,weaponPotential, speedPotential, super.copy());
	}
	public int getWeaponPotential(){
		return weaponPotential;
	}
	public Bullet getNewBullet(){
		return new Bullet(weaponPotential, new Point2D.Double(location.x, location.y), entityAngle, "blue");//At some point we may want to adjust this so that the laser appears at the front of the ship not the middle
	}
	public boolean isShip(){
		return true;
	}
	

}
