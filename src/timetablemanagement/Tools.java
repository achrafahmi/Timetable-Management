/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionemploiv2;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 *
 * @author hp
 */
public class Tools {
 
 public static String getPassword(String userName) {
    String pwd = "";
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
        PreparedStatement stmt = con.prepareStatement("SELECT password FROM Prof WHERE username = ?");
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            pwd = rs.getString("password");
        } else {
            throw new Exception("No password found for the given username: " + userName);
        }
        rs.close();
        stmt.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return pwd;
}  
  //===========================================update password==========================================
  public static void updtPassword(String oldPass,String newPass){
  try{
   Class.forName("com.mysql.cj.jdbc.Driver");
        String protocole = "jdbc:mysql";
        String ip = "localhost";
        String user = "root";
        String password = "zxcvbnm1234";
        String port = "3306";
        String dbName = "EmploiTemps";
        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
        
        Connection con= DriverManager.getConnection(conString, user, password);
        String query ="UPDATE prof set password=? where password=?;";
        String query1="SELECT * FROM prof WHERE password=?";
       PreparedStatement stmt=con.prepareStatement(query);
        PreparedStatement stmt2=con.prepareStatement(query1);
        stmt2.setString(1,oldPass);
        ResultSet r=stmt2.executeQuery();
        if(r.next()){
          stmt.setString(1, newPass);
          stmt.setString(2, oldPass);
          
          stmt.executeUpdate();
          JOptionPane.showMessageDialog(null, "Mot de est modifiee avec succes!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
          
        }
        else{
        JOptionPane.showMessageDialog(null, "Le old mot de passe n est n existe pas : ","Error",
                    JOptionPane.ERROR_MESSAGE);
        
        }
        con.close();
  }catch(Exception e){}
  
  } 


//=====================================fonction hash ======================================
    
    public static String hash(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            return null;
        }
    }
 
  //=============================fonction de selection de deppartement================================== 
    
    public static String[] selectDep() {
    String[] res = null;
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

        String query = "SELECT COUNT(*) AS count FROM departement";
        Statement st = con.createStatement();
        ResultSet r = st.executeQuery(query);

        if (r.next()) {
            int numRows = r.getInt("count");
            res = new String[numRows];

            query = "SELECT nomDep FROM departement";
            r = st.executeQuery(query);

            int i = 0;
            while (r.next()) {
                res[i] = r.getString("nomDep");
                i++;
            }
        }

        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res;
}
//====================================surcharge de selectDep ===============================================
   public static int selectDep(String nomd) {
   int res =0;
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

        String query = "SELECT idDep from departement where nomDep=?;";
        PreparedStatement stmt=con.prepareStatement(query);
        stmt.setString(1, nomd);
        ResultSet r=stmt.executeQuery();
        if(r.next())res=r.getInt("idDep");
        

        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return res;
} 
    
    
    
//================================Verification de disponibilite de seance ===========================================
   
 


    
    public static boolean verifySeance( String jour, String heured, String heureF, int salle) {
        boolean seanceExists = false;

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
                
                
             PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM seance WHERE   jour=? AND heured=? AND heureF=? AND salle=?") ;
            
           
            
            stmt.setString(1, jour);
            stmt.setString(2, heured);
            stmt.setString(3, heureF);
            stmt.setInt(4, salle);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        seanceExists = true;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return seanceExists;
    }

 
   
   
//public static boolean verifySeance(String jr, String hd, String hf, int sal) {
//    try {
//        
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String protocole = "jdbc:mysql";
//        String ip = "localhost";
//        String user = "root";
//        String password = "zxcvbnm1234";
//        String port = "3306";
//        String dbName = "EmploiTemps";
//        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
//        Connection con = DriverManager.getConnection(conString, user, password);
//        PreparedStatement stmt = con.prepareStatement("SELECT * FROM SEANCE WHERE heured=? AND heureF=? AND salle=? AND jour=?;");
//        stmt.setString(1, hd);
//        stmt.setString(2, hf);
//        stmt.setInt(3, sal);
//        stmt.setString(4, jr);
//        ResultSet r = stmt.executeQuery();
//        boolean a = r.next();
//        con.close();
//        return a;
//    } catch (ClassNotFoundException | SQLException e) {
//        e.printStackTrace();
//        return false;
//    }
//}
//



    
 //========================================L insertion d une seance ==============================================

public static void insertSeance(String hd,String hf,int sal,String jr){
 try{
     Class.forName("com.mysql.cj.jdbc.Driver");
        String protocole = "jdbc:mysql";
        String ip = "localhost";
        String user = "root";
        String password = "zxcvbnm1234";
        String port = "3306";
        String dbName = "EmploiTemps";
        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
        Connection con = DriverManager.getConnection(conString, user, password);
        String query="insert into Seance(heured,heureF,salle,jour) values(?,?,?,?);";
       PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, hd);
        stmt.setString(2, hf);
        stmt.setInt(3, sal);
        stmt.setString(4, jr);
        stmt.executeUpdate();
        
     con.close();
 }catch(Exception e ){}


}

//==================================insertion d un module ======================================

public static void insertModule(int idSem,int idProf,int idSeance,String nomMod){
 try{
     Class.forName("com.mysql.cj.jdbc.Driver");
        String protocole = "jdbc:mysql";
        String ip = "localhost";
        String user = "root";
        String password = "zxcvbnm1234";
        String port = "3306";
        String dbName = "EmploiTemps";
        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
        Connection con = DriverManager.getConnection(conString, user, password);
        PreparedStatement stmt=con.prepareStatement("insert into Module(idSem,idProf,idSeance,nomMod) values(?,?,?,?);");
        stmt.setInt(1, idSem);
        stmt.setInt(2, idProf);
        stmt.setInt(3, idSeance);
        stmt.setString(4, nomMod);
        stmt.executeUpdate();
        
      con.close();  
        
 }catch(Exception e){}

}

//=======================================selection d un prof============================================

public static boolean isExistProf(String nomp){
    
    
try{
    boolean etat=false;
     Class.forName("com.mysql.cj.jdbc.Driver");
        String protocole = "jdbc:mysql";
        String ip = "localhost";
        String user = "root";
        String password = "zxcvbnm1234";
        String port = "3306";
        String dbName = "EmploiTemps";
        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
        Connection con = DriverManager.getConnection(conString, user, password);
        PreparedStatement stmt =con.prepareStatement("select * from prof where userName=?; ");
        stmt.setString(1, nomp);
        ResultSet r=stmt.executeQuery();
        if(r.next())etat=true;
        
        con.close();
        return etat;
}catch(Exception e){
return false;}




}

//======================================== insertion de filiere =================================

public static void insertFiliere(String fil,int dep){

try{
     Class.forName("com.mysql.cj.jdbc.Driver");
        String protocole = "jdbc:mysql";
        String ip = "localhost";
        String user = "root";
        String password = "zxcvbnm1234";
        String port = "3306";
        String dbName = "EmploiTemps";
        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
        Connection con = DriverManager.getConnection(conString, user, password);
        PreparedStatement stmt =con.prepareStatement("insert into filiere(idDep,nomFil) values(?,?); ");
        stmt.setString(2, fil);
        stmt.setInt(1,dep);
        
        stmt.executeUpdate();
       
        con.close();
}catch(Exception e){}



}
//================================get idSemestre=============================================

public static int idSemestre(int sem){

int id =0;
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

        String query = "SELECT idSem from semestre where semestre=?;";
        PreparedStatement stmt=con.prepareStatement(query);
        stmt.setInt(1, sem);
        ResultSet r =stmt.executeQuery();

        if(r.next()) id=r.getInt("idSem");
        
        
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return id;


}
 //======================================idProf==================================================   
  public static int idProf(String nomp){
  int id =0;
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

        String query = "SELECT idProf from prof where userName=?;";
        PreparedStatement stmt=con.prepareStatement(query);
        stmt.setString(1, nomp);
        ResultSet r =stmt.executeQuery();

        if(r.next()) id=r.getInt("idProf");
        
        
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return id;
  
  
  
  
  
  
  
  }
//==============================================idSeance==========================================

public static int idSeance(String hd,String hf){

  int id =0;
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

        String query = "SELECT idSeance from seance where heureD=? and heureF=?;";
        PreparedStatement stmt=con.prepareStatement(query);
        stmt.setString(1, hd);
        stmt.setString(2, hf);
        ResultSet r =stmt.executeQuery();

        if(r.next()) id=r.getInt("idSeance");
        
        
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return id;
  
  


}
//================================insert into fil-sem-asso===========================================

public static void insertFilSem(int idSem,int idFil){

try{
     Class.forName("com.mysql.cj.jdbc.Driver");
        String protocole = "jdbc:mysql";
        String ip = "localhost";
        String user = "root";
        String password = "zxcvbnm1234";
        String port = "3306";
        String dbName = "EmploiTemps";
        String conString = protocole + "://" + ip + ":" + port + "/" + dbName;
        Connection con = DriverManager.getConnection(conString, user, password);
        PreparedStatement stmt=con.prepareStatement("insert into fil_sem_asso(idSem,idFil) values(?,?);");
       stmt.setInt(1, idSem);
       stmt.setInt(2, idFil);
        stmt.executeUpdate();
        
      con.close();  
        
 }catch(Exception e){}




}

//================================idFiliere===================================
public static int idFiliere(String fil){
 int id =0;
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

        String query = "SELECT idFil from filiere where nomFil=? ;";
        PreparedStatement stmt=con.prepareStatement(query);
        stmt.setString(1, fil);
        
        ResultSet r =stmt.executeQuery();

        if(r.next()) id=r.getInt("idFil");
        
        
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return id;
  

}

//===========================================select des seances =========================================
public static String[][] selectSeance(String fil, int semestre){
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
        String query1="select count(*) as count "
                + "from module, seance, prof, filiere, semestre, fil_sem_asso "
             + "where filiere.nomFil = ? and semestre.semestre = ? and module.idSem = semestre.idSem and module.idProf = prof.idProf " 
             + "and seance.idSeance = module.idSeance and filiere.idFil = fil_sem_asso.idFil and fil_sem_asso.idSem = semestre.idSem;";
   String query = "select seance.jour as jr, seance.heured as hd, module.nomMod as `mod`, seance.salle as sal, prof.userName as nom " 
             + "from module, seance, prof, filiere, semestre, fil_sem_asso "
             + "where filiere.nomFil = ? and semestre.semestre = ? and module.idSem = semestre.idSem and module.idProf = prof.idProf " 
             + "and seance.idSeance = module.idSeance and filiere.idFil = fil_sem_asso.idFil and fil_sem_asso.idSem = semestre.idSem;";

        PreparedStatement stmt1 = con.prepareStatement(query1);   
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, fil);
        stmt.setInt(2, semestre);
        stmt1.setString(1, fil);
        stmt1.setInt(2, semestre);
        ResultSet rs1=stmt1.executeQuery();
        if(rs1.next()){
            int a=rs1.getInt("count");
            String[][] result=new String[a][5];
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                int i = 0;
                do {
                    result[i][0]=rs.getString("jr");
                    result[i][1]=rs.getString("hd");
                    result[i][2]=rs.getString("mod");
                    result[i][3]=rs.getString("sal");
                    result[i][4]=rs.getString("nom");
                    i++;
                } while (rs.next());
                return result;
            } else{
                return null;
            }
        } else{
            return null;
        }
    } catch(Exception e){
        e.printStackTrace();
        return null;
    }
}











}




 

