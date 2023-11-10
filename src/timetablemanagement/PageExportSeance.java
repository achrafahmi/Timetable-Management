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

public class PageExportSeance extends JFrame {
    private static final long serialVersionUID = 1L;
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "EmploiTemps1";
    private static String user = "root";
    private static String password = "zxcvbnm1234";
    private String[] columns = new String[] { "NOM_PROF", "MODULE", "HEURE_DEBUT",  "HEURE_FIN", "JOUR", "SALLE" };
    private String[][] data = new String[10][6];
    private JTable table = new JTable(data, columns);
    private JPanel panel = new JPanel(new BorderLayout());
    private JButton btn = new JButton("Export");

    // =================================CONSTRUCTOR==================================

    public PageExportSeance() {
        super("Export Emploi de Temps");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        try {
            try (Connection con = DriverManager.getConnection(url + dbName, user, password); Statement stmt = con.createStatement()) {
                String query="SELECT prof.userName, module.nomModule, seance.heureD, seance.heureF, seance.jour, seance.salle " +
                        "FROM prof, module, seance, departement " +
                        "WHERE prof.idDep = departement.idDep AND seance.idModule = module.idModule";
                // Populate the JTable with data from the ResultSet
                try (ResultSet rs = stmt.executeQuery(query)) {
                    // Populate the JTable with data from the ResultSet
                    int i = 0;
                    while (rs.next()) {
                        data[i][0] = rs.getString("userName");
                        data[i][1] = rs.getString("nomModule");
                        data[i][2] = rs.getString("heureD");
                        data[i][3] = rs.getString("heureF");
                        data[i][4] = rs.getString("jour");
                        data[i][5] = rs.getString("salle");
                        i++;
                    }
                }
            }
        } catch (SQLException e) {
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
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btn) {
                JFileChooser fchoose = new JFileChooser();
                int option = fchoose.showSaveDialog(PageExportSeance.this);
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
            try (FileWriter fw = new FileWriter(file)) {
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
            }
        } catch (IOException e) {
           System.out.println(e); }
    }
}


