package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookFlightFrame extends JFrame {

    BookFlightFrame() {
        setTitle("Booking Details");
        setSize(500, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("BOOKING DETAILS", JLabel.CENTER);

        JLabel passenger = new JLabel("Passenger:");
        JTextField passengerField = new JTextField(15);

        JLabel flight = new JLabel("Flight:");
        JTextField flightField = new JTextField(15);

        JLabel travelClass = new JLabel("Travel Class:");
        JTextField classField = new JTextField(15);

        JLabel fare = new JLabel("Fare:");
        JTextField fareField = new JTextField(15);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.add(passenger);
        formPanel.add(passengerField);
        formPanel.add(flight);
        formPanel.add(flightField);
        formPanel.add(travelClass);
        formPanel.add(classField);
        formPanel.add(fare);
        formPanel.add(fareField);

        JPanel centerWrapper = new JPanel(new FlowLayout());
        centerWrapper.add(formPanel);

        JButton confirmButton = new JButton("Confirm Booking");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String passText = passengerField.getText().trim();
                String fltText = flightField.getText().trim();
                String clsText = classField.getText().trim();
                String fareText = fareField.getText().trim();

                if (passText.isEmpty() || fltText.isEmpty() || clsText.isEmpty() || fareText.isEmpty()) {
                    JOptionPane.showMessageDialog(BookFlightFrame.this, "Please fill in all booking fields.");
                } else {
                    try {
                        double parsedFare = Double.parseDouble(fareText);
                        if (parsedFare <= 0 || Double.isNaN(parsedFare) || Double.isInfinite(parsedFare)) {
                            JOptionPane.showMessageDialog(BookFlightFrame.this, "Fare must be a valid amount greater than zero.");
                        } else {
                            // Step 1: Register the JDBC Driver
                            Class.forName("com.mysql.jdbc.Driver");

                            // Step 2: Establish the Connection
                            Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/flight_db", "root", "SSaaff@$123"
                            );

                            // Step 3: Create PreparedStatement
                            String sql = "INSERT INTO bookings VALUES (?, ?, ?, ?)";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, passText);
                            pst.setString(2, fltText);
                            pst.setString(3, clsText);
                            pst.setDouble(4, parsedFare);

                            // Step 4: Execute the Query
                            pst.executeUpdate();

                            // Step 5: Close the Connection
                            con.close();

                            JOptionPane.showMessageDialog(BookFlightFrame.this, "Booking Confirmed and saved for " + passText + "!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(BookFlightFrame.this, "Invalid Fare! Please enter a valid numeric amount.");
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(BookFlightFrame.this, "JDBC Driver not found: " + ex.getMessage());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(BookFlightFrame.this, "Database Error: " + ex.getMessage());
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

    public static void main(String [] agrs) {
        new BookFlightFrame();
    }
}