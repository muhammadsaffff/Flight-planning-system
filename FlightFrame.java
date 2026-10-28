package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FlightFrame extends JFrame {

    FlightFrame() {
        setTitle("Flight Details");
        setSize(500, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FLIGHT DETAILS", JLabel.CENTER);

        JLabel from = new JLabel("Departure:");
        JTextField fromField = new JTextField(15);

        JLabel to = new JLabel("Destination:");
        JTextField toField = new JTextField(15);

        JLabel date = new JLabel("Date:");
        JTextField dateField = new JTextField(15);

        JLabel flightNo = new JLabel("Flight No:");
        JTextField flightField = new JTextField(15);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.add(from);
        formPanel.add(fromField);
        formPanel.add(to);
        formPanel.add(toField);
        formPanel.add(date);
        formPanel.add(dateField);
        formPanel.add(flightNo);
        formPanel.add(flightField);

        JPanel centerWrapper = new JPanel(new FlowLayout());
        centerWrapper.add(formPanel);

        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dep = fromField.getText().trim();
                String dest = toField.getText().trim();
                String dt = dateField.getText().trim();
                String fltNo = flightField.getText().trim();

                if (dep.isEmpty() || dest.isEmpty() || dt.isEmpty() || fltNo.isEmpty()) {
                    JOptionPane.showMessageDialog(FlightFrame.this, "Please fill in all flight fields.");
                } else if (dep.equalsIgnoreCase(dest)) {
                    JOptionPane.showMessageDialog(FlightFrame.this, "Departure and Destination cannot be the same.");
                } else {
                    try {
                        // Step 1: Register the JDBC Driver
                        Class.forName("com.mysql.jdbc.Driver");

                        // Step 2: Establish the Connection
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/flight_db", "root", "SSaaff@$123"
                        );

                        // Step 3: Create PreparedStatement
                        String sql = "INSERT INTO flights VALUES (?, ?, ?, ?)";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, dep);
                        pst.setString(2, dest);
                        pst.setString(3, dt);
                        pst.setString(4, fltNo);

                        // Step 4: Execute the Query
                        pst.executeUpdate();

                        // Step 5: Close the Connection
                        con.close();

                        JOptionPane.showMessageDialog(FlightFrame.this, "Flight details saved to database!");
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(FlightFrame.this, "JDBC Driver not found: " + ex.getMessage());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(FlightFrame.this, "Database Error: " + ex.getMessage());
                    }
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
        add(centerWrapper, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String [] args) {
        new FlightFrame();
    }
}