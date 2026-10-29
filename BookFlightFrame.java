package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookFlightFrame extends JFrame {
    JTextField passengerField, flightField, baseFareField, luggageField, totalFareDisplay;
    JComboBox<String> travelClassCombo;

    public BookFlightFrame() {
        setTitle("Book Flight");
        setSize(500, 420);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("CONFIRM FLIGHT BOOKING", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));

        // 6 rows to accommodate Base Fare, Luggage Weight, and Calculated Total Fare
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
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

        formPanel.add(new JLabel("Base Ticket Fare (₹):"));
        baseFareField = new JTextField();
        formPanel.add(baseFareField);

        formPanel.add(new JLabel("Luggage Weight (kg) [Free: 15kg]:"));
        luggageField = new JTextField();
        formPanel.add(luggageField);

        formPanel.add(new JLabel("Total Calculated Fare (₹):"));
        totalFareDisplay = new JTextField();
        totalFareDisplay.setEditable(false);
        totalFareDisplay.setBackground(new Color(240, 240, 240));
        formPanel.add(totalFareDisplay);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton calcButton = new JButton("Calculate Fare");
        JButton confirmButton = new JButton("Confirm Booking");
        JButton backButton = new JButton("Back to Dashboard");

        buttonPanel.add(calcButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);

        // Calculate and preview fare before booking
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalFare(true);
            }
        });

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

    // Helper method to compute base fare + luggage surcharge
    private Double calculateTotalFare(boolean showPopup) {
        String baseFareText = baseFareField.getText().trim();
        String luggageText = luggageField.getText().trim();

        if (baseFareText.isEmpty() || luggageText.isEmpty()) {
            if (showPopup) {
                JOptionPane.showMessageDialog(this, "Please enter both Base Fare and Luggage Weight.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            return null;
        }

        double baseFare;
        double luggageWeight;

        try {
            baseFare = Double.parseDouble(baseFareText);
            luggageWeight = Double.parseDouble(luggageText);

            if (baseFare <= 0 || Double.isNaN(baseFare) || Double.isInfinite(baseFare)) {
                JOptionPane.showMessageDialog(this, "Base fare must be a valid positive amount.", "Invalid Fare", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            if (luggageWeight < 0 || Double.isNaN(luggageWeight) || Double.isInfinite(luggageWeight)) {
                JOptionPane.showMessageDialog(this, "Luggage weight cannot be negative.", "Invalid Weight", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Base fare and luggage weight must be numeric values.", "Format Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Fare Calculation Logic:
        // Free allowance: up to 15 kg
        // Extra weight: charged at ₹500 per kg
        double extraLuggageFare = 0.0;
        if (luggageWeight > 15.0) {
            extraLuggageFare = (luggageWeight - 15.0) * 500.0;
        }

        double totalFare = baseFare + extraLuggageFare;
        totalFareDisplay.setText(String.format("%.2f", totalFare));

        if (showPopup) {
            String breakdown = "--- FARE SUMMARY ---\n"
                    + "Base Fare: ₹" + String.format("%.2f", baseFare) + "\n"
                    + "Luggage Weight: " + luggageWeight + " kg\n"
                    + "Free Allowance: 15.0 kg\n"
                    + "Extra Luggage Fare: ₹" + String.format("%.2f", extraLuggageFare) + "\n"
                    + "--------------------\n"
                    + "Total Payable Fare: ₹" + String.format("%.2f", totalFare);
            JOptionPane.showMessageDialog(this, breakdown, "Fare Breakdown", JOptionPane.INFORMATION_MESSAGE);
        }

        return totalFare;
    }

    private void saveBookingData() {
        String passenger = passengerField.getText().trim();
        String flight = flightField.getText().trim();
        String travelClass = (String) travelClassCombo.getSelectedItem();

        if (passenger.isEmpty() || flight.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Passenger Name and Flight No.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculate and get the final total fare
        Double totalFare = calculateTotalFare(false);
        if (totalFare == null) {
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

        // Signature preserved exactly: (passenger, flight, travel_class, fare)
        String sql = "INSERT INTO bookings (passenger, flight, travel_class, fare) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, passenger);
            pst.setString(2, flight);
            pst.setString(3, travelClass);
            pst.setDouble(4, totalFare);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Booking successfully confirmed!\nTotal Fare Charged: ₹" + String.format("%.2f", totalFare));
                passengerField.setText("");
                flightField.setText("");
                baseFareField.setText("");
                luggageField.setText("");
                totalFareDisplay.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BookFlightFrame();
    }
}