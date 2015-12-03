
public class Bullet extends Entity{
	
	public Bullet(int damage){
		
	}
	public Bullet(BulletMessage bull){
		super(bull);
		
	}
	public void addForce(){
		//Nothing here, bullets don't change direction
	}
	public String getType(){
		return "Bullet";
	}
	
}
