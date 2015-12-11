import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ExaServer {

	static int updateCount = 0;
	static ArrayList<Player> players;
	static java.util.List<Entity> map;
	final int SLEEP_TIME = 70;
	final int MAX_PLAYERS = 30;
	static int counter = 0;

	public static void main(String[] args) {
		Constants.initializeImages();
		new ExaServer();
	}

	public ExaServer() {
		players = new ArrayList<Player>();
		map = Collections.synchronizedList(new ArrayList<Entity>());
		new Listener().start();
		new Updater().start();
		runGame();
	}

	public void runGame() {
		// System.out.println("Server Running");
		while (true) {
			try {
				Thread.sleep(Constants.Socket.SERVER_REFRESH);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			// System.out.println("Players connected: " + players.size());

			for (int i = 0; i < players.size(); i++) {
				try {
					players.get(i).getOutput().writeObject(Constants.entityToMessage(map));
					// System.out.println("here");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private class Listener extends Thread {
		ServerSocket socket;

		public Listener() {
			try {
				socket = new ServerSocket(8520);

			} catch (Exception e) {

			}
		}

		public void run() {
			while (true) {
				try {
					System.out.println("player added");
					new Player(socket.accept()).start();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

	}

	private class Updater extends Thread {
		public void run() {
		ArrayList<Point2D.Double> locations = new ArrayList<Point2D.Double>();
			while (true) {
				updateCount++;
				try {
					Thread.sleep(Constants.Socket.UPDATE_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (int i = 0; i < map.size(); i++) {
					
					try {
						((Entity) (map.get(i))).updateLocation();
						/*locations.add(((Entity)(map.get(i))).getLocation());
						if(hasDuplicate(locations)){
							for(int x = 0; x < locations.size(); x ++){
								
							}
						}*/
						if(map.get(i).remove()){
							System.out.println("Removing entity");
							map.remove(i);
						}
						
						// System.out.println(((Entity)
						// (map.get(i))).getResultant());
						// System.out.println(((Entity)
						// (map.get(i))).getLocation());
					} catch (Exception e) {
						e.printStackTrace();
						// System.out.println("There as been a small blip in
						// program productivity. Please hold, and your space
						// journey should resume shortly. In fact, it probably
						// resumed before you had time to process that these
						// words appeard on the screen.");
					}
				}
				checkCollisions();
			}
		}
		public void checkCollisions(){
			
			for(int i = 0; i < map.size(); i ++){
				for(int j = 0; j < map.size(); j ++){
					
					if(i != j && map.get(i).collidesWith(map.get(j))){
						map.get(i).collide(map.get(j));
						System.out.println("Collided");
					}
				}
			}
		}
		
		public boolean hasDuplicate(ArrayList<Point2D.Double> c){
	        java.util.Set inputSet = new java.util.HashSet(c);
	        if(inputSet.size()< c.size())
	        return true;
	        return false;
	    }

	}

	private class Player extends Thread {
		private Spaceship entity;
		private Socket socket;
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private int ID;

		public Player(Socket socket) {
			// System.out.println("Player added");

			this.ID = getID();
			this.socket = socket;
			try {
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
				output.writeInt(ID);
			} catch (Exception e) {

			}

			players.add(this);
		}

		public ObjectOutputStream getOutput() {
			return output;
		}

		public int getID() {
			return counter++;
		}

		public void run() {
			Spaceship temp;
			while (true) {
				try {

					temp = new Spaceship((SpaceshipMessage) input.readObject());
					if (map.indexOf(entity) != -1){
						entity.set(temp);
						//System.out.println(entity != null && entity.fire());
						
						if(entity != null && entity.fire()){
							Bullet b = entity.getNewBullet();
							b.setID(getID());
							map.add(b);
							entity.resetFireCount();
						}
						
					}else{
						System.out.println("adding");
						entity = temp.copy();
						map.add(entity);
						}
						
					//	map.remove(map.indexOf(entity));
					
					//map.add(temp);
					//entity = temp;

					//System.out.println(map.size());

				} catch (ClassNotFoundException e) {
					//System.out.println("YOU HAVE NO CLASS");
					e.printStackTrace();
				} catch (IOException e) {
					//System.out.println("Io exception");
				}
			}
		}
	}

}