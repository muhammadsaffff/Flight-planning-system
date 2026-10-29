package flightmodules;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Managebookframe extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public Managebookframe() {
        setTitle("Travel History - Flight Status & History");
        setSize(700, 450);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FLIGHT TRAVEL HISTORY & STATUS", JLabel.CENTER);

        String[] columns = {"Flight No", "Departure", "Destination", "Date", "Status"};

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton refreshButton = new JButton("Refresh Data");
        JButton backButton = new JButton("Back to Dashboard");

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(refreshButton);
        southPanel.add(backButton);

        loadFlightHistory();

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadFlightHistory();
                JOptionPane.showMessageDialog(Managebookframe.this, "Travel history successfully updated from database!");
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

    private void loadFlightHistory() {
        tableModel.setRowCount(0);

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Change password if your MySQL root account uses one (e.g., "admin123")
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/flight_db", "root", "SSaaff@$123"
            );

            String sql = "SELECT flight_no, departure, destination, flight_date, status FROM flights";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String flightNo = rs.getString("flight_no");
                String departure = rs.getString("departure");
                String destination = rs.getString("destination");
                String date = rs.getString("flight_date");
                String status = rs.getString("status");

                Object[] row = { flightNo, departure, destination, date, status };
                tableModel.addRow(row);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "JDBC Driver not found: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Managebookframe();
    }
}