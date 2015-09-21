import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ExaServer {
	
	
	static ArrayList<Player> players;
	static ArrayList<Entity> map;
	public static void main(String[] args) {
		
		new ExaServer();
	}
	public ExaServer(){
		players = new ArrayList<>();
		map = new ArrayList<>();
		new Listener().start();
		runGame();
	}
	public void runGame(){
		System.out.println("Server Running");
		while(true){
			try {
				Thread.sleep(25);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Players connected: " + players.size());
			for(int i = 0; i < map.size(); i ++){
				map.get(i).updateLocation();
				System.out.println("suck here");
			}
			for(int i = 0; i < players.size(); i ++){
				try {
					players.get(i).getOutput().writeObject(Constants.convertArrayListToMessage(map));
					System.out.println("here");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	private class Listener extends Thread{
		ServerSocket socket;
		public Listener(){
			try{
				socket = new ServerSocket(8520);
				
			}catch(Exception e){
				
			}
		}
		public void run(){
			while(true){
			try {
				new Player(socket.accept()).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
		}

	}
	private class Player extends Thread{
		private Entity entity;
		private Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		public Player(Socket socket){
			System.out.println("Player added");
			
			this.socket = socket;
			try{
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
			}catch(Exception e){
				
			}
			players.add(this);
		}
		public ObjectOutputStream getOutput(){
			return output;
		}
		public Entity getEntity(){
			return entity;
		}
		public void run(){
			Entity temp;
			while(true){
				try {
					
					temp = new Entity((Message)input.readObject());
					if(map.indexOf(entity) != -1)
						map.remove(map.indexOf(entity));
					map.add(temp);
					entity = temp;
					
					System.out.println(map.size());
					
				} catch (ClassNotFoundException e) {
					System.out.println("YOU HAVE NO CLASS");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Io exception");
					//e.printStackTrace();
				}
			}
		}
	}

}
