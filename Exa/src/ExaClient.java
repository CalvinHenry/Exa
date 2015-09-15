





import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExaClient extends javax.swing.JFrame {

	
	public boolean upHeld, downHeld, rightHeld, leftHeld;
	private static final long serialVersionUID = 1L;
	java.util.List<Integer> pressed = new java.util.ArrayList<Integer>();
	static Entity entity;
	final double MULT = .25;
	Container pane;
	Screen paint;
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
		(new ExaClient(new Entity())).setVisible(true);
		
	}
	
	private class Screen extends JPanel implements KeyListener{
		public Screen(){
			addKeyListener(this);
		}
		
		public void paintComponent(Graphics g) {
			
			Graphics2D g2D = (Graphics2D)g.create();
			g2D.drawImage(entity.getImage(), (int)entity.getYLocation(), (int)entity.getXLocation(), null);
		}
		
		public void listenToKeys(){
	        if(upHeld) entity.addForce(new Point2D.Double(-MULT * Math.cos(Math.toRadians(entity.getShipAngle())), -MULT * Math.sin(Math.toRadians(entity.getShipAngle()))));
	        if(downHeld) entity.applyBrake(.2);
	        if(leftHeld) entity.rotate(-1.5);
	        if(rightHeld) entity.rotate(1.5);
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
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                   // System.out.println("Thread Interupted");
                }
            	}
            }
        }
    }


