/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionemploiv2;

import java.awt.*;

import java.awt.event.*;

import java.sql.*;

import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author hp
 */
public class AddSeance extends JFrame {
static String jr;
static String hd;
static String hf;
static int sal;
  public AddSeance() {
    setTitle("Gestion des seances");

    // Create the components
    JLabel label = new JLabel("L'ajout d'une seance", JLabel.CENTER);
    JLabel lblJrs=new JLabel("Choisire le jour ");
    JLabel lblHd=new JLabel("Choisire l heure de debut ");
    JLabel lblHf=new JLabel("Choisire l heure de fin ");

    String[] jrs = {"lundi", "mardi", "mercredi", "jeudi", "vendredi"};
    JComboBox jours = new JComboBox(jrs);

    String[] hDebut = {"08:30", "10:15", "12:00", "14:30", "16:15"};
    JComboBox heureD = new JComboBox(hDebut);

    String[] hFin = {"10:00", "11:45", "13:30", "16:00", "17:45"};
    JComboBox heureF = new JComboBox(hFin);

    JTextField salle = new JTextField("saisire la salle");
      salle.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
             char c = e.getKeyChar();
             if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                  e.consume();  // ignorer l'événement 
             }
         }
      });
    JButton valider = new JButton("Valider");
    valider.addActionListener(new ActionListener(){
    
    @Override
    public void actionPerformed(ActionEvent e){
       jr=(String) jours.getSelectedItem();
      
        hd=(String) heureD.getSelectedItem();
      
        
     hf=(String) heureF.getSelectedItem();
        
       sal=Integer.parseInt(salle.getText());
       
      
       // if(Tools.verifySeance(jr, hd, hf, sal)){
       if(Tools.verifySeance(jr,hd,hf,sal))
             JOptionPane.showMessageDialog(null, "La seance est deja reservee !!! " , "Error",
                    JOptionPane.ERROR_MESSAGE);
       
          
         else{
            Tools.insertSeance(hd, hf, sal, jr);
              seanceV2();   
               
         }
  

   }


    
    });
    
    
    
    
    JButton clear = new JButton("Clear");
    clear.addActionListener(new ActionListener(){
    
    @Override
    public void actionPerformed(ActionEvent e){
    jours.setSelectedIndex(0);
    heureD.setSelectedIndex(0);
    heureF.setSelectedIndex(0);
    salle.setText("");
    
    }
    
    });
    
    
    
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    topPanel.add(label);

    JPanel centerPanel = new JPanel(new GridLayout(7, 1, 10, 10));
    centerPanel.add(lblJrs);
    centerPanel.add(jours);
    centerPanel.add(lblHd);
    centerPanel.add(heureD);
    centerPanel.add(lblHf);
    centerPanel.add(heureF);
    centerPanel.add(salle);

    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(valider);
    bottomPanel.add(clear);

    // Add the panels to the frame
    add(topPanel, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(350, 350);
    setLocationRelativeTo(null);
    setVisible(true);
  }
 
 //================================================================================================ 
  
  public static void seanceV2() {
    
    JFrame addSeanceV2 = new JFrame("Ajout d'une séance");
    addSeanceV2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addSeanceV2.setSize(350, 400);
    addSeanceV2.setLocationRelativeTo(null);
    
    
    JLabel lbl1 = new JLabel("Ajout d'une séance", JLabel.CENTER);
    JLabel lblProf = new JLabel("Nom du professeur:");
    JTextField profField = new JTextField();
    JLabel lblSemestre = new JLabel("Semestre:");
    String[] semestre = {"5", "6"};
    JComboBox semestreField = new JComboBox(semestre);
    JLabel lblDep = new JLabel("Département:");
    JComboBox depField = new JComboBox(Tools.selectDep());
    JLabel lblFil = new JLabel("Filière:");
    JTextField filField = new JTextField();
    JLabel lblModule = new JLabel("Module:");
    JTextField moduleField = new JTextField();
    JButton btnConfirme = new JButton("Valider");
    btnConfirme.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
      String nomp=profField.getText();
      int sem=Integer.parseInt((String) semestreField.getSelectedItem()) ;
       String dep=(String)depField.getSelectedItem();
       String fil=filField.getText();
       String mod=moduleField.getText();
       if(Tools.isExistProf(nomp)){
           
       Tools.insertModule(Tools.idSemestre(sem), Tools.idProf(nomp), Tools.idSeance(hd,hf),mod );
       Tools.insertFiliere(fil, Tools.selectDep(dep));
       Tools.insertFilSem(Tools.idSemestre(sem),Tools.idFiliere(fil));
       JOptionPane.showMessageDialog(null, "Le module est ajoute avec succes !!! " , "Error",
                    JOptionPane.INFORMATION_MESSAGE);
       }
       else{
       
       JOptionPane.showMessageDialog(null, "Le prof que tu as saisie n existe pas !!! " , "Error",
                    JOptionPane.ERROR_MESSAGE);
       }
       
       
    
    }
    
    
    });
    
    
    JButton btnAnnule = new JButton("Clear");
    btnAnnule.addActionListener(new ActionListener(){
    
    @Override
    public void actionPerformed(ActionEvent e){
    profField.setText("");
    semestreField.setSelectedIndex(0);
     depField.setSelectedIndex(0);
     filField.setText("");
     moduleField.setText("");
     
    }
   
    });
    
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    topPanel.add(lbl1);
    
    JPanel centerPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    centerPanel.add(lblProf);
    centerPanel.add(profField);
    centerPanel.add(lblSemestre);
    centerPanel.add(semestreField);
    centerPanel.add(lblDep);
    centerPanel.add(depField);
    centerPanel.add(lblFil);
    centerPanel.add(filField);
    centerPanel.add(lblModule);
    centerPanel.add(moduleField);
    
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(btnConfirme);
    bottomPanel.add(btnAnnule);
    
   
    addSeanceV2.add(topPanel, BorderLayout.NORTH);
    addSeanceV2.add(centerPanel, BorderLayout.CENTER);
    addSeanceV2.add(bottomPanel, BorderLayout.SOUTH);
    
    // Display the frame
    addSeanceV2.setVisible(true);
    
    
}

  




 
}






