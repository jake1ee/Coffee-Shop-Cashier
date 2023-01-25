


import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Jee
 */
public class Registration extends javax.swing.JFrame {

    /**
     * Creates new form Registration
     */
    public Registration() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Fname = new javax.swing.JLabel();
        Phonenum = new javax.swing.JLabel();
        Lname = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        Email_txt = new javax.swing.JTextField();
        Phone_txt = new javax.swing.JTextField();
        Lname_txt = new javax.swing.JTextField();
        Fname_txt = new javax.swing.JTextField();
        reg_but = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Title.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        Title.setText("REGISTRATION FORM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addComponent(Title)
                .addGap(235, 235, 235))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Title)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Fname.setFont(new java.awt.Font("Trebuchet MS", 1, 32)); // NOI18N
        Fname.setText("First Name:");

        Phonenum.setFont(new java.awt.Font("Trebuchet MS", 1, 32)); // NOI18N
        Phonenum.setText("Phone Number:");

        Lname.setFont(new java.awt.Font("Trebuchet MS", 1, 32)); // NOI18N
        Lname.setText("Last Name:");

        Email.setFont(new java.awt.Font("Trebuchet MS", 1, 32)); // NOI18N
        Email.setText("Email:");

        Email_txt.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        Email_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Email_txtActionPerformed(evt);
            }
        });

        Phone_txt.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        Phone_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Phone_txtActionPerformed(evt);
            }
        });

        Lname_txt.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        Lname_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lname_txtActionPerformed(evt);
            }
        });

        Fname_txt.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        Fname_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Fname_txtActionPerformed(evt);
            }
        });

        reg_but.setBackground(new java.awt.Color(255, 204, 102));
        reg_but.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        reg_but.setText("Register");
        reg_but.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        reg_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reg_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Email)
                    .addComponent(Lname)
                    .addComponent(Fname)
                    .addComponent(Phonenum))
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reg_but, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Email_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(Lname_txt)
                    .addComponent(Fname_txt)
                    .addComponent(Phone_txt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Fname)
                    .addComponent(Fname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lname)
                    .addComponent(Lname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email)
                    .addComponent(Email_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Phonenum)
                    .addComponent(Phone_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(reg_but)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Email_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Email_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Email_txtActionPerformed

    private void Lname_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lname_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Lname_txtActionPerformed

    private void Fname_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Fname_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Fname_txtActionPerformed

    private void Phone_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Phone_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Phone_txtActionPerformed

    private void reg_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reg_butActionPerformed

        String fname = Fname_txt.getText();
        String lname = Lname_txt.getText();
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
             Connection cons = DriverManager.getConnection(  "jdbc:mysql://127.0.0.1:3306/coffeshop_cashier","root","Leeterqin1126"); 
            PreparedStatement pst;
            
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
    }//GEN-LAST:event_reg_butActionPerformed

    /**
     * @param args the command line arguments
     */

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//          java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Registration().setVisible(true);
//            }
//        });
//    }

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registration().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JTextField Email_txt;
    private javax.swing.JLabel Fname;
    private javax.swing.JTextField Fname_txt;
    private javax.swing.JLabel Lname;
    private javax.swing.JTextField Lname_txt;
    private javax.swing.JTextField Phone_txt;
    private javax.swing.JLabel Phonenum;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton reg_but;
    // End of variables declaration//GEN-END:variables
}
