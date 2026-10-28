package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Managebookframe extends JFrame {

    Managebookframe() {
        setTitle("Travel History");
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("TRAVEL HISTORY", JLabel.CENTER);

        String[] columns = {
            "Flight No", "Departure", "Destination", "Date", "Status"
        };

        String[][] data = {
            {"FL101", "Kochi", "Delhi", "10-09-2026", "Completed"},
            {"FL202", "Mumbai", "Kochi", "15-09-2026", "Completed"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(refreshButton);
        southPanel.add(backButton);

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Step 1: Register the JDBC Driver
                    Class.forName("com.mysql.jdbc.Driver");

                    // Step 2: Establish the Connection
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/flight_db", "root", "SSaaff@$123"
                    );

                    // Step 3 & 4: Create Statement and Execute SELECT Query
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM flights");
                    int count = 0;
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }

                    // Step 5: Close the Connection
                    con.close();

                    JOptionPane.showMessageDialog(
                        Managebookframe.this,
                        "Travel history refreshed! Total saved flights in DB: " + count
                    );
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(Managebookframe.this, "JDBC Driver not found: " + ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(Managebookframe.this, "Database Error: " + ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboardframe();
                dispose();
            }
        });

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Managebookframe();
    }
}