import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/*
 * This class includes methods and variables that are constant and unchanging, and do not fit well in any one class of the program
 * Including math operators, and other static methods needed in multiple classes
 */
public class Constants {
	

	public static BufferedImage[] images = new BufferedImage[5]; //5 images to load
	
	public Constants() throws IOException{
		images[0] = ImageIO.read(new File((System.getProperty("user.home") + "/Desktop/Exa/Qufeb.png")));
	}
	public static class Socket {
		public static final int UPDATE_TIME = 10;
		public static final int SERVER_REFRESH = 150;
		public static final int CLIENT_REFRESH = 140;
		public static final int CYCLES_TO_SERVER_UPDATE = SERVER_REFRESH / UPDATE_TIME; //The number of times the client refreshes the screen before the server sends an update
		
	}

	public static ArrayList<Message> entityToMessage(java.util.List<Entity> list){
		ArrayList<Message> retrn = new ArrayList<>();
		for(int i = 0; i < list.size(); i ++){
			retrn.add(new Message(list.get(i)));
		}
		return retrn;
	}
	
	public static ArrayList<Entity> messageToEntity(ArrayList<Message> list){
		ArrayList<Entity> retrn = new ArrayList<>();
		for(int i = 0; i < list.size(); i ++){
			retrn.add(new Entity(list.get(i)));
		}
		return retrn;
	}
}
