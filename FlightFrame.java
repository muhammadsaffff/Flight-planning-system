package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                    JOptionPane.showMessageDialog(FlightFrame.this, "Flight details validated successfully!");
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
        new FlightFrame();
    }
}