package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FlightFrame extends JFrame {
    JTextField flightNoField, departureField, destinationField, dateField;

    public FlightFrame() {
        setTitle("Flight Details");
        setSize(450, 320);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("REGISTER FLIGHT ROUTE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        formPanel.add(new JLabel("Flight No:"));
        flightNoField = new JTextField();
        formPanel.add(flightNoField);

        formPanel.add(new JLabel("Departure City:"));
        departureField = new JTextField();
        formPanel.add(departureField);

        formPanel.add(new JLabel("Destination City:"));
        destinationField = new JTextField();
        formPanel.add(destinationField);

        formPanel.add(new JLabel("Flight Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("Save Flight");
        JButton backButton = new JButton("Back to Dashboard");
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFlightData();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboardframe();
                dispose();
            }
        });

        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveFlightData() {
        String flightNo = flightNoField.getText().trim();
        String departure = departureField.getText().trim();
        String destination = destinationField.getText().trim();
        String date = dateField.getText().trim();

        if (flightNo.isEmpty() || departure.isEmpty() || destination.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all flight route fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (departure.equalsIgnoreCase(destination)) {
            JOptionPane.showMessageDialog(this, "Departure and Destination cities cannot be the same.", "Route Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "JDBC Driver not found.", "Driver Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String url = "jdbc:mysql://localhost:3306/flight_db";
        String user = "root";
        String password = "SSaaff@$123";

        String sql = "INSERT INTO flights (flight_no, departure, destination, flight_date) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, flightNo);
            pst.setString(2, departure);
            pst.setString(3, destination);
            pst.setString(4, date);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Flight route added to database! It will now appear in Travel History.");
                flightNoField.setText("");
                departureField.setText("");
                destinationField.setText("");
                dateField.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FlightFrame();
    }
}