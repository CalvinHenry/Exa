import java.awt.Window;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class UpgradeGUI extends javax.swing.JFrame{
	
	private final int TOTAL_POINTS = 200;
	private JSlider[] sliders;
	private JLabel[] labels;
	private JSlider moving = new JSlider();
	private static Constants c;

	    public UpgradeGUI() {
	        initComponents();
	        this.setVisible(true);
	        while(true){
	        	checkSliders();
	        }
	    }
	    
	    public static void main(String [] args){
	    	(c = new Constants()).makeNewClient();
	    	new UpgradeGUI();
	    }
                         
	    private void initComponents() {
	    	
	        jSlider1 = new javax.swing.JSlider();
	        jSlider2 = new javax.swing.JSlider();
	        jSlider3 = new javax.swing.JSlider();
	        jSlider4 = new javax.swing.JSlider();
	        
	        jSlider1.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        jSlider2.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        jSlider3.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        jSlider4.setModel(new DefaultBoundedRangeModel(0, 0, 0, 100));
	        
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        jLabel5 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        jLabel10 = new javax.swing.JLabel();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        jLabel13 = new javax.swing.JLabel();
	        jLabel14 = new javax.swing.JLabel();
	        jLabel15 = new javax.swing.JLabel();
	        jButton1 = new javax.swing.JButton();
	        
	        initStuff();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
	        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel1.setText("EXA Upgrader - Choose how to distribute your points to customize your own ship");
	        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

	        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel2.setText("Health:");

	        jLabel3.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel3.setText("Weapons:");

	        jLabel4.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel4.setText("Shields:");

	        jLabel5.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel5.setText("Speed:");

	        jLabel6.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	        jLabel7.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	        jLabel8.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	        jLabel9.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	        jLabel10.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel10.setText("Points Remaining:");

	        jLabel11.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
	        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel11.setText("" + TOTAL_POINTS);

	        jLabel12.setText("The raw ability of your ship to take damage");

	        jLabel13.setText("The ability of your ship to regenerate after taking damage");

	        jLabel14.setText("The damage your ship's bulltets inflict on other ships");

	        jLabel15.setText("How fast your ship can potentially go");

	        jButton1.setLabel("Continue to Game");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton1ActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(18, 18, 18)
	                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                .addGap(18, 18, 18)
	                                .addComponent(jLabel12))
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addGap(18, 18, 18)
	                                        .addComponent(jLabel14))
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                            .addGap(18, 18, 18)
	                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                                .addGroup(layout.createSequentialGroup()
	                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addGap(18, 18, 18)
	                                                    .addComponent(jLabel15))
	                                                .addGroup(layout.createSequentialGroup()
	                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                                    .addGap(0, 0, Short.MAX_VALUE))))
	                                        .addGroup(layout.createSequentialGroup()
	                                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addGap(18, 18, 18)
	                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                            .addGap(18, 18, 18)
	                                            .addComponent(jLabel13))))))
	                        .addContainerGap(16, Short.MAX_VALUE))))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel2)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel7)
	                        .addComponent(jLabel12))
	                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel6)
	                        .addComponent(jLabel14))
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                        .addComponent(jLabel13))
	                                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jLabel4)
	                                .addGap(18, 18, 18)))
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jSlider3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLabel9)
	                        .addComponent(jLabel15))
	                    .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel5))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel10)
	                    .addComponent(jLabel11)
	                    .addComponent(jButton1))
	                .addContainerGap(14, Short.MAX_VALUE))
	        );

	        pack();
	    }// </editor-fold>                        

	    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	        //Continue to Game
	    	this.dispose();
	    	
	    	c.getClient().setVisible(true);
	    	
	    	/*ExaClient ex = new ExaClient(new Spaceship(Integer.parseInt(labels[0].getText()), 
	    			Integer.parseInt(labels[1].getText()), Integer.parseInt(labels[2].getText()), 
	    			Integer.parseInt(labels[3].getText())));
	    	ex.setVisible(true);
	    	*/
	    	
	    }                 
	    
	    private void initStuff(){
	    	sliders = new JSlider[4];
	    	sliders[0] = jSlider1;
	    	sliders[1] = jSlider2;
	    	sliders[2] = jSlider3;
	    	sliders[3] = jSlider4;
	    	labels = new JLabel[4];
	    	labels[0] = jLabel7;
	    	labels[1] = jLabel8;
	    	labels[2] = jLabel6;
	    	labels[3] = jLabel9;
	    	for(JLabel j : labels) j.setText("0");
	    }
	    
	    private void checkSliders(){
	    	int total; //overall total
	    	int totalWithoutMovingOne; //total of static ones
	    	for(JSlider j : sliders){
	    		total = 0;
	    		totalWithoutMovingOne = 0;
	    		if((! j.getValueIsAdjusting()) && moving.equals(j)){
	    			
	    			for(JSlider k : sliders){
	    				total += k.getValue();
	    				totalWithoutMovingOne += k.getValue();
	    				if(k.equals(j)) totalWithoutMovingOne -= k.getValue();
	    			}
	    			if(TOTAL_POINTS < total){
	    				j.setValue(TOTAL_POINTS - totalWithoutMovingOne);
	    				getLabel(j).setText("" + (TOTAL_POINTS - totalWithoutMovingOne));
	    				jLabel11.setText("0");
	    			}else{
	    				getLabel(j).setText("" + j.getValue());
	    				jLabel11.setText("" + (TOTAL_POINTS - total));
	    			}
	    			moving = new JSlider();
	    		}
	    		if(j.getValueIsAdjusting()) moving = j;
	    	}
	    }
	    
	    private JLabel getLabel(JSlider j){
	    	for(int i = 0; i < sliders.length; i++){
	    		if(sliders[i].equals(j)) return labels[i];
	    	}
	    	return null;
	    }

	    // Variables declaration - do not modify                     
	    private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel10;
	    private javax.swing.JLabel jLabel11;
	    private javax.swing.JLabel jLabel12;
	    private javax.swing.JLabel jLabel13;
	    private javax.swing.JLabel jLabel14;
	    private javax.swing.JLabel jLabel15;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JLabel jLabel7;
	    private javax.swing.JLabel jLabel8;
	    private javax.swing.JLabel jLabel9;
	    private javax.swing.JSlider jSlider1;
	    private javax.swing.JSlider jSlider2;
	    private javax.swing.JSlider jSlider3;
	    private javax.swing.JSlider jSlider4;
	    // End of variables declaration                   
	}
