/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionemploiv2;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.TableModel;


/**
 *
 * @author hp
 */

public class PageExportProf extends JFrame {
    private static final long serialVersionUID = 1L;
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "EmploiTemps";
    private static String user = "root";
    private static String password = "zxcvbnm1234";
    private String[] columns = new String[] { "ID", "NOM", "EMAIL", "PASSWORD", "DEPARTEMENT" };
    private String[][] data = new String[10][5];
    private JTable table = new JTable(data, columns);
    private JPanel panel = new JPanel(new BorderLayout());
    private JButton btn = new JButton("Export");

    // =================================CONSTRUCTOR==================================

    public PageExportProf() {
        super("Gestion Emploi de Temps");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            Connection con = DriverManager.getConnection(url + dbName, user, password);
            Statement stmt = con.createStatement();
            String query="SELECT prof.idProf, prof.userName, prof.email, prof.password, departement.nomDep " +
                        "FROM prof, departement " +
                        "WHERE prof.idDep = departement.idDep;";
            ResultSet rs = stmt.executeQuery(query);

            // Populate the JTable with data from the ResultSet
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("idProf");
                data[i][1] = rs.getString("userName");
                data[i][2] = rs.getString("email");
                data[i][3] =Tools.hash(rs.getString("password")) ;
                data[i][4] = rs.getString("nomDep");
                i++;
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(700, 200);
        setLocationRelativeTo(null);
        panel.add(btn, BorderLayout.SOUTH);
        panel.add(new JScrollPane(table), BorderLayout.NORTH);
        add(panel);
        setVisible(true);
        btn.addActionListener(new MyListener());

    }

    // ==========================a listener de prof Abdoun==================================
    class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btn) {
                JFileChooser fchoose = new JFileChooser();
                int option = fchoose.showSaveDialog(PageExportProf.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String name = fchoose.getSelectedFile().getName();
                    String path = fchoose.getSelectedFile().getParentFile().getPath();
                    String file = path + "\\" + name + ".xls";
                    export(table, new File(file));
                }
            }
        }
    }

    // ==================================Method EXPORT==================================================
    public void export(JTable table, File file) {
        try {
            TableModel m = table.getModel();
            FileWriter fw = new FileWriter(file);

            for (int i = 0; i < m.getColumnCount(); i++) {
                fw.write(m.getColumnName(i) + "\t");
            }

            fw.write("\n");

            for (int i = 0; i < m.getRowCount(); i++) {
                for (int j = 0; j < m.getColumnCount(); j++) {
                    fw.write(m.getValueAt(i, j) + "\t");
                }
                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
           System.out.println(e); }
    }
}


























//public class PageExportProf extends JFrame {
//    static  String url = "jdbc:mysql://localhost:3306/";
//    static    String dbName = "EmploiTemps1";
//     static   String user = "root";
//     static   String password = "zxcvbnm1234";
//      String[] columns = new String[] {"ID", "NOM", "EMAIL", "PASSWORD","DEPARTEMENT"};
//      String data[][] = new String[10][5];
//      JTable table = new JTable(data, columns);
//  JPanel panel = new JPanel(new BorderLayout());
//  JButton btn = new JButton("Export");
//      
//      //=================================CONSTRUCTOR==================================
//      
//    public PageExportProf(){
//        JFrame f=new JFrame("Gestion Emploi de Temps");
//        
//        
//        
//      
//        
//    try{
//    Connection con = DriverManager.getConnection(url+dbName, user, password);
//    Statement stmt = con.createStatement();
//    
//    }catch(Exception e){}
//    f.setSize(700,200);
//    f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//    f.setLocationRelativeTo(null);
//    panel.add(btn, BorderLayout.SOUTH);
//    panel.add(new JScrollPane(table), BorderLayout.NORTH);
//    add(panel);
//    setVisible(true);
//    btn.addActionListener(new MyListener());
//    
//    }
//    
//  //========================== un lestener de prof Abdoun==================================
//    class MyListener implements ActionListener{
//      public void actionPerformed(ActionEvent e){
//         if(e.getSource() == btn){
//           JFileChooser fchoose = new JFileChooser();
//           int option = fchoose.showSaveDialog(this);
//           if(option == JFileChooser.APPROVE_OPTION){
//             String name = fchoose.getSelectedFile().getName(); 
//             String path = fchoose.getSelectedFile().getParentFile().getPath();
//             String file = path + "\\" + name + ".xls"; 
//             export(table, new File(file));
//           }
//         }
//      }}
//    //==================================Method EXPORT==================================================
//    public void export(JTable table, File file){
//    try
//    {
//      TableModel m = table.getModel();
//      FileWriter fw = new FileWriter(file);
//
//      for(int i = 0; i < m.getColumnCount(); i++){
//        fw.write(m.getColumnName(i) + "\t");
//      }
//
//      fw.write("\n");
//
//      for(int i=0; i < m.getRowCount(); i++) {
//        for(int j=0; j < m.getColumnCount(); j++) {
//          fw.write(m.getValueAt(i,j)+"\t");
//        }
//        fw.write("\n");
//      }
//
//      fw.close();
//    }
//    catch(IOException e){ System.out.println(e); }
//  }
//    
//    
//}
