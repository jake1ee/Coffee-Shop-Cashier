import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registration extends javax.swing.JFrame {

    /**
     * Creates new form Registration
     */
    public Registration() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Label_lbl = new javax.swing.JLabel();
        Fname_lbl = new javax.swing.JLabel();
        Lname_lbl = new javax.swing.JLabel();
        Email_lbl = new javax.swing.JLabel();
        Phone_lbl = new javax.swing.JLabel();
        Fname_txt = new javax.swing.JTextField();
        Lname_txt = new javax.swing.JTextField();
        Email_txt = new javax.swing.JTextField();
        Phone_txt = new javax.swing.JTextField();
        Reg_but = new javax.swing.JButton();
        Cancel_but = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Member Registration");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 140, 0), 2));

        Label_lbl.setBackground(new java.awt.Color(255, 255, 255));
        Label_lbl.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        Label_lbl.setForeground(new java.awt.Color(204, 255, 255));
        Label_lbl.setText("Registration Form");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(Label_lbl)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Label_lbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Fname_lbl.setBackground(new java.awt.Color(0, 0, 0));
        Fname_lbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Fname_lbl.setForeground(new java.awt.Color(0, 0, 255));
        Fname_lbl.setText("First Name");

        Lname_lbl.setBackground(new java.awt.Color(0, 0, 0));
        Lname_lbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Lname_lbl.setForeground(new java.awt.Color(0, 0, 255));
        Lname_lbl.setText("Last Name");

        Email_lbl.setBackground(new java.awt.Color(0, 0, 0));
        Email_lbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Email_lbl.setForeground(new java.awt.Color(0, 0, 255));
        Email_lbl.setText("Email");

        Phone_lbl.setBackground(new java.awt.Color(0, 0, 0));
        Phone_lbl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Phone_lbl.setForeground(new java.awt.Color(0, 0, 255));
        Phone_lbl.setText("Phone");

        Fname_txt.setToolTipText("Enter First Name");
        Fname_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Fname_txtActionPerformed(evt);
            }
        });

        Lname_txt.setToolTipText("Enter Last Name");

        Email_txt.setToolTipText("Enter Personal Email");

        Phone_txt.setToolTipText("Enter Your Phone number");

        Reg_but.setText("Register");
        Reg_but.setToolTipText("Click to register");
        Reg_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reg_butActionPerformed(evt);
            }
        });

        Cancel_but.setText("Cancel");
        Cancel_but.setToolTipText("Click to cancel registration");
        Cancel_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Fname_lbl)
                            .addComponent(Lname_lbl)
                            .addComponent(Email_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Phone_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Phone_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(Cancel_but, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(Reg_but, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fname_lbl)
                    .addComponent(Fname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lname_lbl)
                    .addComponent(Lname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email_lbl)
                    .addComponent(Email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Phone_lbl)
                    .addComponent(Phone_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel_but)
                    .addComponent(Reg_but))
                .addGap(0, 42, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Reg_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reg_butActionPerformed
        // TODO add your handling code here:
        
        String lname = Lname_txt.getText();
        String fname = Fname_txt.getText();
        String email = Email_txt.getText();
        String phone = Phone_txt.getText();
        
        if(lname.equals(""))
            JOptionPane.showMessageDialog(null, "Add Last Name");
        else if(fname.equals(""))
            JOptionPane.showMessageDialog(null, "Add First Name");
        else if(email.equals(""))
            JOptionPane.showMessageDialog(null, "Add Email");
        else if(phone.equals(""))
            JOptionPane.showMessageDialog(null, "Add Phone number");
        
        try
        {
             Connection cons = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/coffeshop_cashier","root","Leeterqin1126"); 
            PreparedStatement pst;
            ResultSet rs;
            
            pst = cons.prepareStatement("insert into member_table (member_FNAME, member_LNAME, member_EMAIL, member_PHONE) value (?, ?, ?, ?)");
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, email);
            pst.setString(4, phone);
            
              if(pst.executeUpdate() > 0)
            {
                
                JOptionPane.showMessageDialog(null, "New Member Add");
                int result = JOptionPane.showConfirmDialog(null,"Do you want make a new Registration", "Comfirmation",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
                 if(result == JOptionPane.NO_OPTION)
                 {
                     dispose();
                 }
                 else if(result == JOptionPane.YES_OPTION)
                 {
                     Lname_txt.setText("");
                     Fname_txt.setText("");
                     Email_txt.setText("");
                     Phone_txt.setText("");
                 }
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_Reg_butActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Registration().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel_but;
    private javax.swing.JLabel Email_lbl;
    private javax.swing.JTextField Email_txt;
    private javax.swing.JLabel Fname_lbl;
    private javax.swing.JTextField Fname_txt;
    private javax.swing.JLabel Label_lbl;
    private javax.swing.JLabel Lname_lbl;
    private javax.swing.JTextField Lname_txt;
    private javax.swing.JLabel Phone_lbl;
    private javax.swing.JTextField Phone_txt;
    private javax.swing.JButton Reg_but;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
