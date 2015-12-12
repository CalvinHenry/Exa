import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerGUI extends javax.swing.JDialog {
	
	private boolean isFinished = false;

    public ServerGUI() {
        initComponents();
        System.out.println("COMPONENTS DONE");
        this.setVisible(true);
        System.out.println("YO");
        while(! isFinished()){
        	try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	if(isFinished()) break;
        }
    }
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jLabel1.setFont(new java.awt.Font("Perpetua", 1, 48)); // NOI18N
        jLabel1.setText("Your IP:");

        jLabel3.setFont(new java.awt.Font("Perpetua", 1, 48)); // NOI18N
        jLabel3.setText(getIP());

        jButton1.setText("Launch Server");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel3)))
                .addContainerGap(110, Short.MAX_VALUE))
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
        );
        this.setSize(400, 300);
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration                                          
    private void jButton1ActionPerformed(java.awt.event.ActionEvent e){
    	isFinished = true;
    	jButton1.setEnabled(false);
    }
    
    private String getIP(){
    	java.util.ArrayList<String> addresses = new java.util.ArrayList<String>();
    	java.util.Enumeration e = null;
		try {
			e = java.net.NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	while(e.hasMoreElements())
    	{
    	    java.net.NetworkInterface n = (java.net.NetworkInterface) e.nextElement();
    	    java.util.Enumeration ee = n.getInetAddresses();
    	    while (ee.hasMoreElements())
    	    {
    	        java.net.InetAddress i = (java.net.InetAddress) ee.nextElement();
    	        if(! i.isLoopbackAddress() && ! i.getHostAddress().contains(":")&&! 
    	        		  i.getHostAddress().equals(i.getHostName()) &&!  i.isMulticastAddress() ){
    	        		//&&! i.isLinkLocalAddress() &&! i.isSiteLocalAddress()){
    	        	addresses.add(i.getHostAddress());
    	        	System.out.println(i.getHostName());
    	        }
    	    }
    	}
    	System.out.println(addresses);
    	return addresses.get(0);
    }

    private boolean isFinished(){
    	return isFinished;
    }               
}
