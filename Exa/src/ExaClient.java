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
import javax.swing.JPanel;

public class ExaClient extends javax.swing.JFrame {
	static ArrayList<Entity> map = new ArrayList<>();
	static final String ADDRESS = "localhost";
	static final int PORT = 8520;
	public boolean upHeld, downHeld, rightHeld, leftHeld;
	private static final long serialVersionUID = 1L;
	java.util.List<Integer> pressed = new java.util.ArrayList<Integer>();
	static Entity entity;
	final double MULT = .35;
	Container pane;
	Screen paint;
	ArrayList<Point> points = new ArrayList<>();
	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	public ExaClient(Entity e){
		this();
		entity = new Entity();
		Repainter repaint = new Repainter(this);
		repaint.start();
	}
	
	public ExaClient(){
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        //main.setResizable(false);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        
        pane = getContentPane();
        paint = new Screen();

        pane.add(paint);
        paint.setFocusable(true);
        
        paint.requestFocus();
	}
	
	
	public static void main(String [] args){
		
		
		try {
			socket = new Socket(ADDRESS, PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			(new ExaClient(new Entity())).setVisible(true);
			while(true){
				map = Constants.messageToEntity((ArrayList<Message>)in.readObject());
				System.out.println("Reading in");
			}
		} catch (Exception e) {
			
		}
		
	}
	
	private class Screen extends JPanel implements KeyListener{
		public Screen(){
			addKeyListener(this);
		}
		
		public void paintComponent(Graphics g) {
			System.out.println("here");
			Graphics2D g2D = (Graphics2D)g.create();
			for(int i = 0; i < map.size(); i ++){
				Entity entity = map.get(i);
				g2D.drawImage(entity.getImage(), (int)entity.getYLocation(), (int)entity.getXLocation(), null);
			}
			
			
			//System.out.println("Entity Loc x:" + entity.getXLocation() + " Y: " + entity.getYLocation() + "Point: X:" + points.get(points.size()- 1).x + " Y: " + points.get(points.size() - 1).y);
		}
		
		public void listenToKeys(){
	        if(upHeld) entity.addForce(new Point2D.Double(-MULT * Math.cos(Math.toRadians(entity.getEntityAngle())), -MULT * Math.sin(Math.toRadians(entity.getEntityAngle()))));
	        if(downHeld) entity.applyBrake(.2);
	        if(leftHeld) entity.rotate(-1.5);
	        if(rightHeld) entity.rotate(1.5);
	        
	        try {
	        	if(entity != null){
	        		out.writeObject(new Message(entity));
	        	}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
		    if(keyCode == KeyEvent.VK_UP) upHeld = true;
		    if(keyCode == KeyEvent.VK_DOWN) downHeld = true;;
		    if(keyCode == KeyEvent.VK_LEFT) leftHeld = true;
		    if(keyCode == KeyEvent.VK_RIGHT) rightHeld = true;
		} 

		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
		    if(keyCode == KeyEvent.VK_UP) upHeld = false;
		    if(keyCode == KeyEvent.VK_DOWN) downHeld = false;
		    if(keyCode == KeyEvent.VK_LEFT) leftHeld = false;
		    if(keyCode == KeyEvent.VK_RIGHT) rightHeld = false;
			//pressed.remove((Integer)e.getKeyCode());
		}


		public void keyTyped(KeyEvent e) {
			
		}
		
	}
	
	 
	private class Repainter extends Thread {
		ExaClient frame;
        public Repainter(ExaClient frame) {
        	this.frame = frame;
        }

        @Override
        public void run() {
            	while(true){
                frame.repaint();
                frame.entity.updateLocation();
                frame.paint.listenToKeys();
                frame.points.add(new Point((int)frame.entity.getYLocation() + 105, (int)frame.entity.getXLocation() + 105));
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                   // System.out.println("Thread Interupted");
                }
            	}
            }
        }
    }


