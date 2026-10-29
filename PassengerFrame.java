package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PassengerFrame extends JFrame {
    JTextField nameField, ageField, phoneField, emailField;

    public PassengerFrame() {
        setTitle("Passenger Details");
        setSize(450, 320);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("ENTER PASSENGER DETAILS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        formPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email Address:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("Save Passenger");
        JButton backButton = new JButton("Back to Dashboard");
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savePassengerData();
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

    private void savePassengerData() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        // Validate empty fields
        if (name.isEmpty() || ageText.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all passenger fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate age integer conversion
        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0 || age > 120) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age between 1 and 120.", "Invalid Age", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number.", "Number Format Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Load driver fallback
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
        String password = "SSaaff@$123"; // Set your MySQL password if configured

        String sql = "INSERT INTO passengers (name, age, phone, email) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, phone);
            pst.setString(4, email);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Passenger record saved successfully to MySQL!");
                nameField.setText("");
                ageField.setText("");
                phoneField.setText("");
                emailField.setText("");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new PassengerFrame();
    }
}