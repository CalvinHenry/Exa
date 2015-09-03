import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ExaClient extends javax.swing.JFrame {


	private static final long serialVersionUID = 1L;
	Entity entity;
	Container pane;
	Screen paint;
	public ExaClient(Entity e){
		this();
		this.entity = new Entity();
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
			g2D.drawImage(entity.getImage(), entity.getYLocation(), entity.getXLocation(), null);
		}
		
		public void keyPressed(KeyEvent e) {
		    int keyCode = e.getKeyCode();
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		        	System.out.println("aaaay");
		            entity.accelerate(-1);
		            break;
		        case KeyEvent.VK_DOWN:
		            entity.accelerate(1);
		            break;
		        case KeyEvent.VK_LEFT:
		            entity.rotate(-4);
		            break;
		        case KeyEvent.VK_RIGHT :
		            entity.rotate(4);
		            break;
		     }
		} 

		public void keyReleased(KeyEvent e) {
			
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


