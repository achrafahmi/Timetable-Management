/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionemploiv2;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author hp
 */
public class LoginPage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
JFrame f =new JFrame("Home-Login page");
JPanel panel=new JPanel(new FlowLayout()); // modified
JMenuBar menuBar=new JMenuBar();
JMenu smi=new JMenu("SMI");
JMenu sma=new JMenu("SMA"); 
JMenu smpc=new JMenu("SMPC");
JMenuItem s5smi =new JMenuItem("S5");
s5smi.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
new Affichage("smi","S5");

}


});

JMenuItem s6smi =new JMenuItem("S6");
s6smi.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
new Affichage("smi","S6");

}


});
JMenuItem s5sma =new JMenuItem("S5");
s5sma.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
new Affichage("sma","S5");

}


});
JMenuItem s6sma =new JMenuItem("S6");
s6sma.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
new Affichage("sma","S6");

}

});
JMenuItem s5smpc =new JMenuItem("S5");
s5smpc.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
new Affichage("smpc","S5");

}


});
JMenuItem s6smpc =new JMenuItem("S6");
s6smpc.addActionListener(new ActionListener(){
@Override
public void actionPerformed(ActionEvent e){
new Affichage("smpc","S6");

}
});
smi.add(s5smi);
smi.add(s6smi);
sma.add(s5sma);
sma.add(s6sma);
smpc.add(s5smpc);
smpc.add(s6smpc);
menuBar.add(smi);
menuBar.add(sma);
menuBar.add(smpc);

JPanel pnl=new JPanel();
JLabel lbl1=new JLabel("Email :");
lbl1.setPreferredSize(new Dimension(100, 20));
JTextField emailField=new JTextField();
// pour interdire les caracteres speciaux pour eviter les injections sql
emailField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
    char c = e.getKeyChar();
    if (c == '\'' || c == '/' || c == '-') {
        e.consume();
    }
        }
         
      });


JLabel lbl2=new JLabel("Password :");
lbl2.setPreferredSize(new Dimension(100, 30));
JPasswordField txtPass=new JPasswordField();
JButton btnLog =new JButton("Login");



btnLog.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
      String a = emailField.getText();
      if(!a.contains("@") && !a.contains(".com") && !a.contains(".ma") ){
      JOptionPane.showMessageDialog(null, "Le Format d email est invalide : ","Error",
                    JOptionPane.ERROR_MESSAGE);
      
      }
      String c = new String(txtPass.getPassword());
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String protocole = "jdbc:mysql";
        String ip = "localhost";
        String user = "root";
        String password = "zxcvbnm1234";
        String port = "3306";
        String dbName = "EmploiTemps";
        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
        Connection con = DriverManager.getConnection(conString, user, password);
        PreparedStatement stmt = con.prepareStatement("select * from Prof where email=? and password=?");
        stmt.setString(1, a);
        stmt.setString(2, c);
        ResultSet r = stmt.executeQuery();
        if (r.next()) {
            String email = r.getString("email");
            String pwd = r.getString("password");
            String pwdAdmin;
            try{
                //getimg the 
            pwdAdmin=Tools.getPassword("Admin");
           if(email.equals("admin@gmail.com") && pwd.equals(pwdAdmin)){
              new Root(); 
           }
           else{
             new UserPage();

           }
           }catch(Exception ee){
           
           }
        }
        else{
           JOptionPane.showMessageDialog(null, "Mot de passe ou email non valide !!! " , "Error",
                    JOptionPane.ERROR_MESSAGE);  
        } 
   
        }
        catch(ClassNotFoundException | SQLException ee){}
   
   
   }      
});


pnl.add(lbl1);
pnl.add(emailField);
pnl.add(lbl2);
pnl.add(txtPass);
pnl.add(btnLog);
pnl.setLayout(new BoxLayout(pnl,BoxLayout.PAGE_AXIS));

f.setLayout(new BorderLayout());
f.add(menuBar, BorderLayout.NORTH);
f.add(panel, BorderLayout.CENTER); 
panel.add(pnl);

f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.pack();
f.setSize(500, 300);
f.setVisible(true);
f.setLocationRelativeTo(null);

    }
    
}
