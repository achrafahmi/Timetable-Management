/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionemploiv2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RootPage {

    private static JFrame frame; 

    public static void main(String arg[]) {

        frame = new JFrame("Root-Page");
        JPanel panel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu insertion = new JMenu("Gestion des profs");
        JMenu modification = new JMenu("Gestion DB");
        JMenuItem pInsertion = new JMenuItem("ajoute les profs");
        JMenuItem pExport = new JMenuItem("Export-List des profs");
        JMenuItem prSuppression = new JMenuItem("Supprime prof");
        JMenuItem pCreateDb = new JMenuItem("Create BD");
        JMenuItem pDeleteDb = new JMenuItem("Suprimer BD");
        JMenuItem prCreateTb = new JMenuItem("Create table proffesors");
        JMenuItem prDeleteTb = new JMenuItem("Delete table proffesors");
        JMenuItem pModification = new JMenuItem("Modification");
        insertion.add(pInsertion);
        insertion.add(pModification);
        insertion.add(prSuppression);
        insertion.add(pExport);
        modification.add(pCreateDb);
         modification.add(pDeleteDb);
        modification.add(prCreateTb);
        modification.add(prDeleteTb);
        frame.setLayout(new GridLayout(7, 1));
        menuBar.add(insertion);
        menuBar.add(modification);
        frame.setJMenuBar(menuBar);
        frame.add(panel);

        //list of action listeners
        pCreateDb.addActionListener((ActionEvent e) -> {
            createDatabase();
        });
        prCreateTb.addActionListener((ActionEvent e) -> {
            createTable();
        });
         prDeleteTb.addActionListener((ActionEvent e) -> {
            deleteTable();
        });
          pDeleteDb.addActionListener((ActionEvent e) -> {
            deleteDatabase();
        });
         
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    static void createDatabase() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "EmploiTemps1";
        String user = "root";
        String password = "zxcvbnm1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {
                String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
                stmt.executeUpdate(sql);
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

    static void createTable() {
        String url = "jdbc:mysql://localhost:3306/EmploiTemps1";
        String user = "root";
        String password = "zxcvbnm1234";
        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "CREATE TABLE IF NOT EXISTS professors ("
                        + "id INT PRIMARY KEY AUTO_INCREMENT,"
                        + "name VARCHAR(100),"
                        + "department VARCHAR(100),"
                        + "password VARCHAR(100)"
                        + ")";
                stmt.executeUpdate(sql1);
                
            }
            
            JOptionPane.showMessageDialog(frame, "Table created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Failed to create table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     static void deleteTable() {
        String url = "jdbc:mysql://localhost:3306/EmploiTemps1";
        String user = "root";
        String password = "zxcvbnm1234";
        
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {
                String sql1 = "DROP TABLE IF  EXISTS professors";
                stmt.executeUpdate(sql1);
                
            }
            
            JOptionPane.showMessageDialog(frame, "Table Deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Failed to Delete table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
      static void deleteDatabase() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "EmploiTemps1";
        String user = "root";
        String password = "zxcvbnm1234";

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



















/*package gestionemploiv2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RootPage {
    
    

    public static void main(String arg[]) {

        JFrame frame = new JFrame("Root-Page");
        JPanel panel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu insertion = new JMenu("Gestion des profs");
        JMenu modification = new JMenu("Gestion DB");
        JMenuItem pInsertion = new JMenuItem("ajoute les profs");
        JMenuItem pExport = new JMenuItem("Export-List des profs");
        JMenuItem prSuppression = new JMenuItem("Supprime prof");
        JMenuItem pCreateDb = new JMenuItem("Create BD");
         JMenuItem prCreateTb = new JMenuItem("Create table proffesors");
        JMenuItem pSuppression = new JMenuItem("Suppression de DB");
        JMenuItem pModification = new JMenuItem("Modification");
        insertion.add(pInsertion);
        insertion.add(pModification);
        insertion.add(prSuppression);
        insertion.add(pExport);
        modification.add(pCreateDb);
        modification.add(prCreateTb);
         modification.add(pSuppression);
        frame.setLayout(new GridLayout(7, 1));
        menuBar.add(insertion);
        menuBar.add(modification);
        frame.setJMenuBar(menuBar);
        frame.add(panel);

        pCreateDb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createDatabase();
            }
        });
        prCreateTb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createTable();
            }
        });

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    static void createDatabase() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "EmploiTemps1";
        String user = "root";
        String password = "Emmanuel2325";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Database created successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load JDBC driver: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to create database: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    static void createTable() {
        // Add your MySQL database connection details here
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Emmanuel2325";
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // For MySQL Connector/J version 8.x and above
            // OR
            // Class.forName("com.mysql.jdbc.Driver"); // For older versions
            
            // Connect to MySQL database
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            
            // Execute SQL query to create table
            String sql1 = "CREATE TABLE IF NOT EXISTS professors ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100),"
                    + "department VARCHAR(100),"
                    + "password VARCHAR(100)"
                    + ")";
            stmt.executeUpdate(sql1);
            
            // Close database connection
            stmt.close();
            conn.close();
            
            JOptionPane.showMessageDialog(frame, "Table created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to create table: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
            }*/
