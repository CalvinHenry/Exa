import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ExaClient extends javax.swing.JFrame {
	static int updateCount = 0;
	static int inputCount = 0;
	static int ID;
	static ExaClient client;
	static ArrayList<Entity> localMap = new ArrayList<>();
	static ArrayList<Entity> serverMap = new ArrayList<>();
	static final String ADDRESS = "localhost";
	static final int PORT = 8520;
	static final int WINDOW_X = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	static final int WINDOW_Y = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	public boolean upHeld, downHeld, rightHeld, leftHeld;
	private static final long serialVersionUID = 1L;
	java.util.List<Integer> pressed = new java.util.ArrayList<Integer>();
	static Spaceship playerShip;
	final double MULT = .05;
	Container pane;
	Screen paint;
	ArrayList<Point> points = new ArrayList<>();
	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	static BufferedImage background;

	public ExaClient(Spaceship e) {
		this();
		playerShip = e;

	}

	public void start() {
		Repainter repaint = new Repainter(this);
		Talker talk = new Talker();
		repaint.start();
		talk.start();
	}

	private static String getServerAddress() {
		return JOptionPane.showInputDialog(client, "Enter IP Address of the Server:", "Welcome to EXA",
				JOptionPane.QUESTION_MESSAGE);
	}

	public ExaClient() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// main.setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);

		pane = getContentPane();
		paint = new Screen();

		pane.add(paint);
		paint.setFocusable(true);

		paint.requestFocus();
	}

	public static void main (String [] args) {
		System.out.println("Howdy");
		UpgradeGUI ug = new UpgradeGUI();
		System.out.println("Hi");
		while(! ug.isFinished()){//wait until upgradeGUI is finished
			if(ug.isFinished()) break;
			else System.out.println("Still not done");
		}
		try {
			Constants.initializeImages();
			background = ImageIO.read(new File(System.getProperty("user.home") + "/Desktop/Exa/background.png"));
			(client = new ExaClient(new Spaceship(ug.getNumbers()[0], ug.getNumbers()[1], ug.getNumbers()[2], ug.getNumbers()[3]))).setVisible(true);
			socket = new Socket(getServerAddress(), PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			client.start();
			ID = in.readInt();
			playerShip.setID(ID);
			playerShip.setTopSpeed(2);
			while (true) {
				serverMap = Constants.messageToEntity((ArrayList<Message>) in.readObject());
				System.out.println(serverMap);
				initialClientUpdate();
				inputCount++;
				//System.out.println(inputCount + "Reading in");
			}
		} catch (Exception e) {

		}

	}

	public static void initialClientUpdate() {
		boolean foundEntity = false;
		for (int i = 0; i < serverMap.size(); i++) {
			foundEntity = false;
			for (int j = 0; j < localMap.size(); j++) {
				if (serverMap.get(i).entityEquals(localMap.get(j))) {
					localMap.get(j).setResultant(serverMap.get(i).getResultant());
					localMap.get(j).setDifference(new Point2D.Double(localMap.get(j).getLocation().x - serverMap.get(i).getLocation().x, localMap.get(j).getLocation().y - serverMap.get(i).getLocation().y));
					localMap.get(j).setAngleDifference(localMap.get(j).getEntityAngle() - serverMap.get(i).getEntityAngle());
					localMap.get(j).inSync = false;
					foundEntity = true;
				}
			}
			if (!foundEntity) {
				localMap.add(serverMap.get(i));
			} // Adds new Entities from the server into the client
		}
	}

	public void updateMap() {
		
		
		updateCount++;
		// System.out.println(updateCount);
		localMap = updateMap(localMap);
		serverMap = updateMap(serverMap);
		
		for (int i = 0; i < serverMap.size(); i++) {
			
			for (int j = 0; j < localMap.size(); j++) {
				if (serverMap.get(i).entityEquals(localMap.get(j))) {
					localMap.get(j).setDifference(new Point2D.Double(localMap.get(j).getLocation().x - serverMap.get(i).getLocation().x, localMap.get(j).getLocation().y - serverMap.get(i).getLocation().y));
					localMap.get(j).setAngleDifference(localMap.get(j).getEntityAngle() - serverMap.get(i).getEntityAngle());
				}
			}
			
		}
		playerShip.updateLocation();
	}

	public ArrayList<Entity> updateMap(ArrayList<Entity> e) {
		for (int i = 0; i < e.size(); i++) {
			e.get(i).updateLocation();
		}
		return e;
	}

	private class Screen extends JPanel implements KeyListener {
		public Screen() {
			addKeyListener(this);
		}
		
		public BufferedImage getImageSelection(){		
			return background.getSubimage(
					(int)(-1 * (playerShip.getLocation().getX() - (WINDOW_X)/2)), (int)(-1 * (playerShip.getLocation().getY() - (WINDOW_Y)/2)), 
					WINDOW_X, WINDOW_Y);
			}

		public void paintComponent(Graphics g) {
			updateMap();
			Graphics2D g2D = (Graphics2D) g.create();
			//g2D.drawImage(getImageSelection(), 0, 0, null);
			for (int i = 0; i < localMap.size(); i++) {
				Entity theEntity = localMap.get(i);
				if (playerShip.entityEquals(theEntity)) {
					// playerShip = theEntity.copy();
					System.out.println("Drawing playership");
					g2D.drawImage(playerShip.getImage(), WINDOW_Y / 2 - playerShip.getImageHeight()/2, WINDOW_X / 2 - playerShip.getImageWidth()/2, null);

				} else {
					System.out.println("Drawing non playership");
					g2D.drawImage(theEntity.getImage(),
							(int) (theEntity.getLocation().getY() - playerShip.getLocation().getY() + (WINDOW_Y / 2) - theEntity.getImageHeight()/2),
							(int) (theEntity.getLocation().getX() - playerShip.getLocation().getX() + (WINDOW_X / 2) - theEntity.getImageHeight()/2),
							null);
				}
			}
		}

		public void listenToKeys() {
			if (upHeld)
				playerShip.addForce(new Point2D.Double(-MULT * Math.cos(Math.toRadians(playerShip.getEntityAngle())),
						-MULT * Math.sin(Math.toRadians(playerShip.getEntityAngle()))));
			if (downHeld)
				playerShip.applyBrake(.01);
			if (leftHeld)
				playerShip.rotate(-1);
			if (rightHeld)
				playerShip.rotate(1);

		}

		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_UP)
				upHeld = true;
			if (keyCode == KeyEvent.VK_DOWN)
				downHeld = true;
			;
			if (keyCode == KeyEvent.VK_LEFT)
				leftHeld = true;
			if (keyCode == KeyEvent.VK_RIGHT)
				rightHeld = true;
		}

		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_UP)
				upHeld = false;
			if (keyCode == KeyEvent.VK_DOWN)
				downHeld = false;
			if (keyCode == KeyEvent.VK_LEFT)
				leftHeld = false;
			if (keyCode == KeyEvent.VK_RIGHT)
				rightHeld = false;

		}

		public void keyTyped(KeyEvent e) {

		}

	}

	private class Talker extends Thread {
		public Talker() {

		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(Constants.Socket.CLIENT_REFRESH);
					if (playerShip != null) {
						out.writeObject(new SpaceshipMessage(playerShip));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private class Repainter extends Thread {
		ExaClient frame;

		public Repainter(ExaClient frame) {
			this.frame = frame;
		}

		@Override
		public void run() {
			while (true) {
				frame.repaint();
				// frame.playerShip.updateLocation();
				frame.paint.listenToKeys();
				frame.points.add(new Point((int) frame.playerShip.getYLocation() + 105,
						(int) frame.playerShip.getXLocation() + 105));
				try {
					Thread.sleep(Constants.Socket.UPDATE_TIME);
				} catch (InterruptedException e) {
					// //System.out.println("Thread Interupted");
				}
			}
		}
	}
}