





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
		
		public void keyPressed(KeyEvent e) {
			pressed.add(e.getKeyCode());
		    for(int keyCode : pressed){
		        if(keyCode == KeyEvent.VK_UP) entity.addForce(new Point2D.Double(-MULT * Math.cos(Math.toRadians(entity.getShipAngle())), -MULT * Math.sin(Math.toRadians(entity.getShipAngle()))));
		        if(keyCode == KeyEvent.VK_DOWN) entity.applyBrake(1);
		        if(keyCode == KeyEvent.VK_LEFT) entity.rotate(-1);
		        if(keyCode == KeyEvent.VK_RIGHT) entity.rotate(1);
		    }
		    
		} 

		public void keyReleased(KeyEvent e) {
			while(pressed.indexOf(e.getKeyCode()) != -1){
				pressed.remove(pressed.indexOf(e.getKeyCode()));
			}
			
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
                
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                   // System.out.println("Thread Interupted");
                }
            	}
            }
        }
    }


