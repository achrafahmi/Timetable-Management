/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class Root {
  static  String url = "jdbc:mysql://localhost:3306/";
    static    String dbName = "EmploiTemps";
     static   String user = "root";
     static   String password = "zxcvbnm1234";
  static  JFrame frame; 
    public Root(){
    
    frame = new JFrame("Root-Page");
        JPanel panel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu insertion = new JMenu("Gestion des profs");
        JMenu modification = new JMenu("Gestion DB");
        JMenuItem pInsertion = new JMenuItem("ajoute les profs");
        pInsertion.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e){
            new AddProf2();
        }
        });
        
        
        JMenuItem pExport = new JMenuItem("Export-List des profs");
        pExport.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
        new PageExportProf();
        }
                    
        });
        JMenuItem prSuppression = new JMenuItem("Supprime prof");
        prSuppression.addActionListener(new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e){
        
        new DeleteProf();
        }
        
        });
        JMenuItem pCreateDb = new JMenuItem("Create BD");
        JMenuItem pDeleteDb = new JMenuItem("Suprimer BD");
       pDeleteDb.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
       deleteDB();
       
       
       }
       });
       
        //JMenuItem pModification = new JMenuItem("Modification");
        insertion.add(pInsertion);
       // insertion.add(pModification);
        insertion.add(prSuppression);
        insertion.add(pExport);
        modification.add(pCreateDb);
         modification.add(pDeleteDb);
        //modification.add(prCreateTb);
        //modification.add(prDeleteTb);
        frame.setLayout(new GridLayout(7, 1));
        menuBar.add(insertion);
        menuBar.add(modification);
        frame.setJMenuBar(menuBar);
        //=====================Background image 
        JPanel backgroundPanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image image = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\GestionEmploiV2\\src\\gestionemploiv2\\fs-tetouan.png").getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    };
        frame.add(panel);
frame.setContentPane(backgroundPanel);
        //list of action listeners
        pCreateDb.addActionListener((ActionEvent e) -> {
            createDatabase();
        });
      
          pDeleteDb.addActionListener((ActionEvent e) -> {
            deleteDatabase();
        });
         
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void createDatabase() {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {
                String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
                stmt.executeUpdate(sql);
                createDepTab();
                createProfTable();
                createSeanceTab();
                createSemTab();
                createFilTab();
                createFSAssoTab();
                createModTab();
                
            }
            JOptionPane.showMessageDialog(null, "Database created successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Failed to load JDBC driver: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to create database: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void deleteDB(){
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
        String query="drop database if exists EmploiTemps;";
        Statement st = con.createStatement();
        st.executeUpdate(query);
        con.close();
     }catch(Exception e){}
    
    }
    
    
    
    //===================================departement table =============================================
    static void createDepTab(){

        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url+dbName, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS DEPARTEMENT (" +
                          " idDep INTEGER AUTO_INCREMENT,"+
                          " nomDep VARCHAR(30)," +
                          " PRIMARY KEY(idDep)" +
                          ");";
                stmt.executeUpdate(sql1);
                
            }
            

        } catch (ClassNotFoundException | SQLException ex) {
           
        }
    
    }
    
    public static void dropDepTab(){
        try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection(url+dbName, user, password);
     Statement stmt = conn.createStatement();
     String sql="DROP TABLE IF EXISTS DEPARTEMENT ;";
     
     stmt.executeUpdate(sql);
     
        }
        catch(ClassNotFoundException | SQLException ex){
        
        }
    
    
    
    }
    
    
    //===================================prof table======================================================
    static void createProfTable() {

        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url+dbName, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS prof ("
                         +"idProf INTEGER AUTO_INCREMENT,"
                         +"idDep INTEGER NOT NULL,"
                         +"userName VARCHAR(30),"
                         +"email VARCHAR(50),"
                         +"password VARCHAR(30),"
                         +"PRIMARY KEY(idProf),"
                         +" FOREIGN KEY(idDep) REFERENCES DEPARTEMENT(idDep)"
                         + ");";
                stmt.executeUpdate(sql1);
                
            }
            
           
        } catch (ClassNotFoundException | SQLException ex) {
          
        }
    }
    
     public static void dropProfTab(){
        try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection(url+dbName, user, password);
     Statement stmt = conn.createStatement();
     String sql="DROP TABLE IF EXISTS prof ;";
     
     stmt.executeUpdate(sql);
     
        }
        catch(ClassNotFoundException | SQLException ex){
        
        }
    
    
    
    }
    
   
    
    //========================================SEANCE table=====================================================================
    static void createSeanceTab(){
   
        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url+dbName, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS SEANCE (" +
                            "    idSeance INTEGER AUTO_INCREMENT," +
                            "    heured VARCHAR(5)," +
                            "    heureF VARCHAR(5)," +
                            "    salle INTEGER," +
                            "    jour VARCHAR(15)," +
                            "    PRIMARY KEY(idSeance)" +
                            ");";
                stmt.executeUpdate(sql1);
                
            }
            
          
        } catch (ClassNotFoundException | SQLException ex) {
          
        }
    
    
    }
    
     public static void dropSeanceTab(){
        try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection(url+dbName, user, password);
     Statement stmt = conn.createStatement();
     String sql="DROP TABLE IF EXISTS SEANCE  ;";
     
     stmt.executeUpdate(sql);
     
        }
        catch(ClassNotFoundException | SQLException ex){
        
        }
    
    
    
    }
//===================================  SEMESTRE table=================================================================  
    public static void createSemTab(){

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url+dbName, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS SEMESTRE (" +
                         "    idSem INTEGER AUTO_INCREMENT," +
                        "    semestre INTEGER," +
                        "    PRIMARY KEY(idSem)" +
                        ");" ;
                stmt.executeUpdate(sql1);
                
            }
            
          
        } catch (ClassNotFoundException | SQLException ex) {
          
        }
    
    }
    public static void dropSemTab(){
        try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection(url+dbName, user, password);
     Statement stmt = conn.createStatement();
     String sql="DROP TABLE IF EXISTS SEMESTRE  ;";
     
     stmt.executeUpdate(sql);
     
        }
        catch(ClassNotFoundException | SQLException ex){
        
        }
    
   
    }
    //======================================FILIERE tab==================================================
      public static void createFilTab(){

        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url+dbName, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS FILIERE (" +
                            "    idFil INTEGER AUTO_INCREMENT," +
                            "    idDep INTEGER," +
                            "    nomFil VARCHAR(30)," +
                            "    PRIMARY KEY(idFil)," +
                            "    FOREIGN KEY(idDep) REFERENCES DEPARTEMENT(idDep)" +
                            ");" ;
                stmt.executeUpdate(sql1);
                
            }
          
        } catch (ClassNotFoundException | SQLException ex) {
          
        }
    }
      public static void dropFilTab(){
        try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection(url+dbName, user, password);
     Statement stmt = conn.createStatement();
     String sql="DROP TABLE IF EXISTS FILIERE  ;";
     
     stmt.executeUpdate(sql);
     
        }
        catch(ClassNotFoundException | SQLException ex){
        
        }
    
   
    }
    //=====================================FIL_SEM_ASSO Table =======================================================
    
    public static void createFSAssoTab(){
    
   
        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url+dbName, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS FIL_SEM_ASSO (" +
                            "    idSem INTEGER," +
                            "    idFil INTEGER," +
                            "    PRIMARY KEY(idSem, idFil)," +
                            "    FOREIGN KEY(idSem) REFERENCES SEMESTRE(idSem)," +
                            "    FOREIGN KEY(idFil) REFERENCES FILIERE(idFil) " +
                            ");" ;
                stmt.executeUpdate(sql1);
                
            }
            
         
        } catch (ClassNotFoundException | SQLException ex) {

        }
    
    
    
    }
     public static void dropFSAssoTab(){
        try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection(url+dbName, user, password);
     Statement stmt = conn.createStatement();
     String sql="DROP TABLE IF EXISTS FIL_SEM_ASSO  ;";
     
     stmt.executeUpdate(sql);
     
        }
        catch(ClassNotFoundException | SQLException ex){
        
        }
    
   
    }
    //=====================================MODULE Table========================================================
    public static void createModTab(){
     
        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url+dbName, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS MODULE (" +
                            "    idMod INTEGER AUTO_INCREMENT," +
                            "    idSem INTEGER," +
                            "    idProf INTEGER," +
                            "    idSeance INTEGER," +
                            "    nomMod VARCHAR(30)," +
                            "    PRIMARY KEY(idMod)," +
                            "    FOREIGN KEY(idSem) REFERENCES SEMESTRE(idSem)," +
                            "    FOREIGN KEY(idProf) REFERENCES prof(idProf)," +
                            "    FOREIGN KEY(idSeance) REFERENCES SEANCE(idSeance)" +
                            ");" ;
                stmt.executeUpdate(sql1);
                
            }
            
          
        } catch (ClassNotFoundException | SQLException ex) {
          
        }
    
    }
     public static void dropModTab(){
        try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection conn = DriverManager.getConnection(url+dbName, user, password);
     Statement stmt = conn.createStatement();
     String sql="DROP TABLE IF EXISTS MODULE  ;";
     
     stmt.executeUpdate(sql);
     
        }
        catch(ClassNotFoundException | SQLException ex){
        
        }
    
   
    }
    
     //===================================delete db=================================================================
      static void deleteDatabase() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {
                String sql = "DROP DATABASE IF  EXISTS " + dbName;
                stmt.executeUpdate(sql);
            }
            JOptionPane.showMessageDialog(null, "Database Deleted successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Failed to load JDBC driver: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to Deleted Database: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
