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
public class Affichage extends JFrame{
  public Affichage(String fil ,String sem){
        int semestre;
        if(sem.equals("S5")){
        semestre=5;
        }
        else{semestre=6;}
        String[] columns = new String[] { "Jours", "08:30=>10:00", "10:15=>11:45", "12:00=>13:30", "14:30=>16:00","16:15=>17:45" };
        String[] jrs = {"lundi", "mardi", "mercredi", "jeudi", "vendredi"};
        String[][] data = new String[5][6];
        
        for (int i = 0; i < jrs.length; i++) {
            data[i][0] = jrs[i];
        }
      
        String[][] seance = Tools.selectSeance(fil, semestre);
        if(seance!=null){
        for(int i = 0; i < data.length; i++) {
            for(int j = 1; j < data[i].length; j++) {
                String val = "";
                for(int k = 0; k < seance.length; k++)  {
                    if(seance[k][0].equalsIgnoreCase(data[i][0]) && seance[k][1].equalsIgnoreCase(columns[j])) {
                        if(!val.equals("")) val += "<br>";
                        val += seance[k][2] + " " + seance[k][4] + " " + seance[k][3];
                    }
                }
                if(!val.equals("")) data[i][j] = val;
            }
        }
      }
        // create the JTable and set its data
        JTable table = new JTable(data, columns);
        
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lbl1 = new JLabel("Emploi de Temps de SMI S5 ");
        setTitle("Affichage des emplis ");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(lbl1, BorderLayout.NORTH);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
    

    

