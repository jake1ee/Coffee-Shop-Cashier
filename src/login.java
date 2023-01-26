
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.sql.*;

public class login extends javax.swing.JFrame {
    
    enum Login_status {
    SUCCESS,
    PASS_FAIL,
    FAIL
  }

    public login() throws SQLException {
        
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Login_lbl = new javax.swing.JLabel();
        Username_lbl = new javax.swing.JLabel();
        Username_txt = new javax.swing.JTextField();
        Password_lbl = new javax.swing.JLabel();
        Pass_txt = new javax.swing.JPasswordField();
        Login_but = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cashsier Login");

        Login_lbl.setText("Login");

        Username_lbl.setText("Username");

        Password_lbl.setText("Password");

        Pass_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pass_txtKeyPressed(evt);
            }
        });

        Login_but.setText("Log In");
        Login_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Login_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Password_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Username_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Login_but)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Pass_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                .addComponent(Username_txt)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Login_lbl)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Username_lbl)
                    .addComponent(Username_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Password_lbl)
                    .addComponent(Pass_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(Login_but))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Login_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_butActionPerformed

            Connection cons = null;
        try
        {
             cons=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/coffeshop_cashier","root","huixin64"); 
             PreparedStatement pst;
             ResultSet rs;
             Login_status log = Login_status.FAIL;

            pst = cons.prepareStatement("Select Username, Password, Employee_name  from adminlogin_table");
            rs = pst.executeQuery();
            
            String user=Username_txt.getText();
            String pass= new String (Pass_txt.getPassword());
            String emp_name = null;
            while(rs.next())
            {
                String uname = rs.getString("Username");
                String pword = rs.getString("Password");
                
                
                if(user.equals(uname) && pass.equals(pword))
                {
                    log = Login_status.SUCCESS;
                    emp_name = rs.getString("Employee_name");
                    break;
                }
                else if(user.equals(uname) && !pass.equals(pword))
                {
                    log = Login_status.PASS_FAIL;
                }
 
            }
            
                   if(log == Login_status.SUCCESS) {
                    JOptionPane.showMessageDialog(this,"Login Success.\n Welcome " + emp_name );
                    new Homepage().setVisible(true);
                    setVisible(false);
                    }
                   else if (log ==Login_status.PASS_FAIL){
                    JOptionPane.showMessageDialog(null, "Wrong Password");
                    Pass_txt.setText("");
                    }
                   else if(log == Login_status.FAIL ){ 
                    JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
                    Username_txt.setText("");
                    Pass_txt.setText("");
                   }
            } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
          
           
    }//GEN-LAST:event_Login_butActionPerformed

    private void Pass_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pass_txtKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            Connection cons = null;
        try
        {
             cons=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/coffeshop_cashier","root","huixin64"); 
            // cons=DriverManager.getConnection(  "jdbc:mysql://127.0.0.1:3306/coffeshop_cashier","root","goodmorning"); 
             PreparedStatement pst;
             ResultSet rs;
             Login_status log = Login_status.FAIL;

            pst = cons.prepareStatement("Select Username, Password, Employee_name  from adminlogin_table");
            rs = pst.executeQuery();
            
            String user=Username_txt.getText();
            String pass= new String (Pass_txt.getPassword());
            String emp_name = null;
            while(rs.next())
            {
                String uname = rs.getString("Username");
                String pword = rs.getString("Password");
                
                
                if(user.equals(uname) && pass.equals(pword))
                {
                    log = Login_status.SUCCESS;
                    emp_name = rs.getString("Employee_name");
                    break;
                }
                else if(user.equals(uname) && !pass.equals(pword))
                {
                    log = Login_status.PASS_FAIL;
                }
 
            }
            
                   if(log == Login_status.SUCCESS) {
                    JOptionPane.showMessageDialog(this,"Login Success.\n Welcome " + emp_name );
                    new Homepage().setVisible(true);
                    setVisible(false);
                    }
                   else if (log ==Login_status.PASS_FAIL){
                    JOptionPane.showMessageDialog(null, "Wrong Password");
                    Pass_txt.setText("");
                    }
                   else if(log == Login_status.FAIL ){ 
                    JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
                    Username_txt.setText("");
                    Pass_txt.setText("");
                   }
            } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
    
    }//GEN-LAST:event_Pass_txtKeyPressed

    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new login().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login_but;
    private javax.swing.JLabel Login_lbl;
    private javax.swing.JPasswordField Pass_txt;
    private javax.swing.JLabel Password_lbl;
    private javax.swing.JLabel Username_lbl;
    private javax.swing.JTextField Username_txt;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
