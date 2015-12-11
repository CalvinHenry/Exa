public class ImagePanel extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private java.awt.image.BufferedImage image;

    public ImagePanel(int a) {
       image = Constants.images[a];
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if(! image.equals(null)){
        	g.drawImage(image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        }
    }

}