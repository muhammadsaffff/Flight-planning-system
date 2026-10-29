package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookFlightFrame extends JFrame {
    JTextField passengerField, flightField, fareField;
    JComboBox<String> travelClassCombo;

    public BookFlightFrame() {
        setTitle("Book Flight");
        setSize(450, 320);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("CONFIRM FLIGHT BOOKING", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        formPanel.add(new JLabel("Passenger Name:"));
        passengerField = new JTextField();
        formPanel.add(passengerField);

        formPanel.add(new JLabel("Flight No:"));
        flightField = new JTextField();
        formPanel.add(flightField);

        formPanel.add(new JLabel("Travel Class:"));
        String[] classes = {"Economy", "Premium Economy", "Business", "First Class"};
        travelClassCombo = new JComboBox<>(classes);
        formPanel.add(travelClassCombo);

        formPanel.add(new JLabel("Ticket Fare:"));
        fareField = new JTextField();
        formPanel.add(fareField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("Confirm Booking");
        JButton backButton = new JButton("Back to Dashboard");
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveBookingData();
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

    private void saveBookingData() {
        String passenger = passengerField.getText().trim();
        String flight = flightField.getText().trim();
        String travelClass = (String) travelClassCombo.getSelectedItem();
        String fareText = fareField.getText().trim();

        if (passenger.isEmpty() || flight.isEmpty() || fareText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all booking details.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double fare;
        try {
            fare = Double.parseDouble(fareText);
            if (fare <= 0 || Double.isNaN(fare) || Double.isInfinite(fare)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive fare amount.", "Invalid Fare", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Fare must be a numeric value.", "Format Error", JOptionPane.ERROR_MESSAGE);
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

        String sql = "INSERT INTO bookings (passenger, flight, travel_class, fare) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, passenger);
            pst.setString(2, flight);
            pst.setString(3, travelClass);
            pst.setDouble(4, fare);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Booking successfully confirmed and logged into MySQL!");
                passengerField.setText("");
                flightField.setText("");
                fareField.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BookFlightFrame();
    }
}