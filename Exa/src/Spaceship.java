public class Spaceship extends Entity {
	
	private int health; //current health
	private int shields; //current shields
	private int healthPotential; //upgradable stats
	private int shieldPotential; //regeneration rate
	private int weaponPotential; //damage impacted by bullets
	private int speedPotential; //top speed
		
	public Spaceship(int h, int s, int w, int speed){
		super();
		this.healthPotential = h;
		this.shieldPotential = s;
		this.weaponPotential = w;
		this.speedPotential = speed;
		setTopSpeed(speed);
	}
	
	public void takeDamage(int damage){
		health -= damage;
	}
	

}
