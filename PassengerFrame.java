package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                            JOptionPane.showMessageDialog(PassengerFrame.this, "Passenger details submitted successfully!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(PassengerFrame.this, "Invalid Age! Please enter a valid whole number.");
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

    public static void main(String [] args)
    {
        new PassengerFrame();
    }
}