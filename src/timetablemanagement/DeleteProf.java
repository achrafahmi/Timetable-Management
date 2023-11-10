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

public class DeleteProf {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameTextField;
    private JTextField departmentTextField;
    private JPasswordField passwordTextField;
    Connection connection;

    public DeleteProf() {
        // Initialize database connection
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/EmploiTemps1";
            String user = "root";
            String password1 = "zxcvbnm1234";
            Connection conn = DriverManager.getConnection(url, user, password1);
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
        }

        frame = new JFrame("Modification Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nom ");
        tableModel.addColumn("Department");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("Nom de prof:"));
        nameTextField = new JTextField();
        formPanel.add(nameTextField);
        formPanel.add(new JLabel("Departmentde prof:"));
        departmentTextField = new JTextField();
        formPanel.add(departmentTextField);
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        frame.add(formPanel, BorderLayout.NORTH);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String department = departmentTextField.getText();

                if (!name.isEmpty() && !department.isEmpty()) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/EmploiTemps1";
                        String user = "root";
                        String password1 = "zxcvbnm1234";
                        Connection conn = DriverManager.getConnection(url, user, password1);

                        PreparedStatement statement = conn.prepareStatement("DELETE FROM prof WHERE userName= ? and IdDep=?");
                        statement.setString(1, name);
                        statement.setString(2, department);
                        int rowsAffected = statement.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Deleted Successfully!");
                            tableModel.addRow(new Object[]{name, department});
                            nameTextField.setText("");
                            departmentTextField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error: User not found!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    frame.pack();
    frame.add(deleteButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
       // frame.add(deleteButton, BorderLayout.SOUTH);
       // frame.pack();
       // frame.setSize(400, 300);
       // frame.setVisible(true);
    }

    public static void main(String[] args) {
        DeleteProf example = new DeleteProf();
    }
}

 