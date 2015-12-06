import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * This class includes methods and variables that are constant and unchanging, and do not fit well in any one class of the program
 * Including math operators, and other static methods needed in multiple classes
 */
public class Constants {
	
	ExaClient e;
	
	public static BufferedImage[] images = new BufferedImage[20];
	
	/*
	 * 0 - Qufeb
	 * 1 - 
	 * 2 - 
	 */
	
	public static void initializeImages(){
		
		try{
		images[0] = ImageIO.read(new File(System.getProperty("user.home") + "/Desktop/Exa/background.png"));
		
		images[1] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/Qufeb.png")));
		images[2] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/bigBoii.png")));
		images[3] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/Cepily.png")));
		images[4] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/Zax.png")));
		images[5] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/Skimo.png")));
		images[6] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/laser_blue.png")));
		images[7] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/laser_green.png")));
		images[8] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/laser_red.png")));
		System.out.println("Through image loading");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void makeNewClient(){
		e = new ExaClient(new Spaceship(0,0,0,0));
	}
	
	public ExaClient getClient(){
		return e;
	}
	
	public static class Socket {
		public static final int UPDATE_TIME = 10;
		public static final int SERVER_REFRESH = 100;
		public static final int CLIENT_REFRESH = 100;
		public static final int CYCLES_TO_SERVER_UPDATE = SERVER_REFRESH / (2 * UPDATE_TIME); //The number of times the client refreshes the screen before the server sends an update
	}
	public static ArrayList<Message> entityToMessage(java.util.List<Entity> list){
		ArrayList<Message> retrn = new ArrayList<>();
		for(int i = 0; i < list.size(); i ++){
			retrn.add(Message.getNewMessage(list.get(i)));
		}
		return retrn;
	}
	
	public static ArrayList<Entity> messageToEntity(ArrayList<Message> list){
		ArrayList<Entity> retrn = new ArrayList<>();
		for(int i = 0; i < list.size(); i ++){
			retrn.add(Entity.getNewEntity(list.get(i)));
		}
		return retrn;
	}
}