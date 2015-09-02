import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GUI1 extends javax.swing.JFrame implements java.awt.event.KeyListener{


	private static final long serialVersionUID = 1L;
	Entity entity;
	public GUI1(Entity e){
		e = entity;
	}
	
	public GUI1(){

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}
	

	
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D)g.create();
		
	}
	public static void main(String [] args){
		(new GUI1()).setVisible(true);
	}


	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            entity.accelerate(1);
	            break;
	        case KeyEvent.VK_DOWN:
	            entity.accelerate(-1);
	            break;
	        case KeyEvent.VK_LEFT:
	            entity.rotate(1);
	            break;
	        case KeyEvent.VK_RIGHT :
	            entity.rotate(-1);
	            break;
	     }
	} 

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	private class Repainter extends Thread {
		JFrame frame;
        public Repainter(JFrame frame) {
        	this.frame = frame;
        }

        @Override
        public void run() {
            
                frame.repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                   // System.out.println("Thread Interupted");
                }
            }
        }
    }


