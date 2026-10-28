package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PassengerFrame extends JFrame {

    PassengerFrame() {
        setTitle("Passenger Details");
        setSize(500, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("PASSENGER DETAILS", JLabel.CENTER);

        JLabel name = new JLabel("Name:");
        JTextField nameField = new JTextField(15);

        JLabel age = new JLabel("Age:");
        JTextField ageField = new JTextField(15);

        JLabel phone = new JLabel("Phone:");
        JTextField phoneField = new JTextField(15);

        JLabel email = new JLabel("Email:");
        JTextField emailField = new JTextField(15);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.add(name);
        formPanel.add(nameField);
        formPanel.add(age);
        formPanel.add(ageField);
        formPanel.add(phone);
        formPanel.add(phoneField);
        formPanel.add(email);
        formPanel.add(emailField);

        JPanel centerWrapper = new JPanel(new FlowLayout());
        centerWrapper.add(formPanel);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameText = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String phoneText = phoneField.getText().trim();
                String emailText = emailField.getText().trim();

                if (nameText.isEmpty() || ageText.isEmpty() || phoneText.isEmpty() || emailText.isEmpty()) {
                    JOptionPane.showMessageDialog(PassengerFrame.this, "Please fill in all passenger fields.");
                } else {
                    try {
                        int parsedAge = Integer.parseInt(ageText);
                        if (parsedAge <= 0 || parsedAge > 120) {
                            JOptionPane.showMessageDialog(PassengerFrame.this, "Please enter a valid age between 1 and 120.");
                        } else {
                            // Step 1: Register the JDBC Driver
                            Class.forName("com.mysql.jdbc.Driver");

                            // Step 2: Establish the Connection
                            Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/flight_db", "root", "SSaaff@$123"
                            );

                            // Step 3: Create PreparedStatement
                            String sql = "INSERT INTO passengers VALUES (?, ?, ?, ?)";
                            PreparedStatement pst = con.prepareStatement(sql);
                            pst.setString(1, nameText);
                            pst.setInt(2, parsedAge);
                            pst.setString(3, phoneText);
                            pst.setString(4, emailText);

                            // Step 4: Execute the Query
                            pst.executeUpdate();

                            // Step 5: Close the Connection
                            con.close();

                            JOptionPane.showMessageDialog(PassengerFrame.this, "Passenger details saved to database!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PassengerFrame.this, "Invalid Age! Please enter a valid whole number.");
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(PassengerFrame.this, "JDBC Driver not found: " + ex.getMessage());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(PassengerFrame.this, "Database Error: " + ex.getMessage());
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
        new PassengerFrame();
    }
}