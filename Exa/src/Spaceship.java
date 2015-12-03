public class Spaceship extends Entity {
	
	public int health; //current health
	public int shields; //current shields
	public int healthPotential; //upgradable stats
	public int shieldPotential; //regeneration rate
	public int weaponPotential; //damage impacted by bullets
	public int speedPotential; //top speed
		
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
	}
	
	public void setValues(int a, int b, int c, int d){
		healthPotential = a;
		shieldPotential = b;
		weaponPotential = c;
		speedPotential = d;
	}
	
	public void takeDamage(int damage){
		health -= damage;
	}
	public String getType(){
		return "Spaceship";
	}
	public Spaceship copy(){
		return new Spaceship(healthPotential, shieldPotential,weaponPotential, speedPotential, super.copy());
	}
	

}
