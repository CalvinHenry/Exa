
public class BulletMessage extends Message{

	int cycles;
	int maxCycles;
	
	public BulletMessage(Bullet b){
		super(b);
		cycles = b.cycles;
		maxCycles = b.maxCycles;
		
	}
}
