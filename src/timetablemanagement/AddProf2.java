/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionemploiv2;

/**
 *
 */ 
 import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class AddProf2 {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameTextField;
    private JTextField departmentTextField;
    private JPasswordField passwordTextField;
  Connection connection;
    private final JTextField emailTextField;

    public AddProf2() {
                try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EmploiTemps1";
        String user = "root";
        String password1 = "passwd";
           Connection conn = DriverManager.getConnection(url, user, password1); 
        } catch (ClassNotFoundException | SQLException e) {
                    }

        frame = new JFrame("Ajoute les prof");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Department");
        tableModel.addColumn("Email");
        tableModel.addColumn("Password");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        JPanel formPanel = new JPanel(new GridLayout(5, 3));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       formPanel.add(new JLabel("Name:"));
        nameTextField = new JTextField();
        formPanel.add(nameTextField);
        
        formPanel.add(new JLabel("Departement:"));
        departmentTextField = new JTextField();
        formPanel.add(departmentTextField);
        
        formPanel.add(new JLabel("Email"));
        emailTextField = new JTextField();
        formPanel.add(emailTextField);
        
        formPanel.add(new JLabel("Password:"));
        passwordTextField = new JPasswordField();
        formPanel.add(passwordTextField);
       formPanel.add(new JLabel());
        formPanel.add(new JLabel());
       frame.add(formPanel, BorderLayout.NORTH);
       JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
                    private Object s;
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String department = departmentTextField.getText();
                String email = emailTextField.getText();
                String password = new String(passwordTextField.getPassword());
                if (!name.isEmpty() && !department.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    tableModel.addRow(new Object[]{name, department,email, password});
                    nameTextField.setText("");
                    departmentTextField.setText("");
                    emailTextField.setText("");
                    passwordTextField.setText("");

                   
 try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String url = "jdbc:mysql://localhost:3306/EmploiTemps1";
    String user = "root";
    String password1 = "passwd";
    Connection conn = DriverManager.getConnection(url, user, password1);

    PreparedStatement statement = conn.prepareStatement("INSERT INTO prof (userName, idDep, email, password) VALUES (?, ?, ?, ?);");
    PreparedStatement st = conn.prepareStatement("SELECT idDep FROM departement WHERE nomDep=?");

    statement.setString(1, name);

    st.setString(1, department);
    ResultSet r = st.executeQuery();
    if (r.next()) {
        int idDep = r.getInt("idDep");
        statement.setInt(2, idDep);
    } else {
       System.out.println("Error: Department not found.");
        return; 
    }

    statement.setString(3, email);
    statement.setString(4, password);

    int rowsAffected = statement.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Data inserted successfully.");
    } else {
        System.out.println("Failed to insert data.");
    }

} catch (Exception ex) {
    ex.printStackTrace();
}


                }
            }
        });
             frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(addButton, BorderLayout.SOUTH);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
      //  frame.add(addButton, BorderLayout.SOUTH);
        //frame.pack();
        //frame.setSize(400, 300);
        //frame.setVisible(true);
    }

    public static void main(String[] args) {
        AddProf2 example = new AddProf2();
    }
}
