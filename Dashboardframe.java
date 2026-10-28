package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboardframe extends JFrame {

    Dashboardframe() {
        setTitle("Flight Planning System - Dashboard");
        setSize(600, 450);
        setLayout(new BorderLayout(15, 15));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FLIGHT PLANNING SYSTEM", JLabel.CENTER);
        JLabel subtitle = new JLabel("DASHBOARD", JLabel.CENTER);

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headerPanel.add(title);
        headerPanel.add(subtitle);

        JButton passengerButton = new JButton("Passenger Details");
        JButton flightButton = new JButton("Flight Details");
        JButton bookingButton = new JButton("Booking Details");
        JButton historyButton = new JButton("Travel History");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.add(passengerButton);
        buttonPanel.add(flightButton);
        buttonPanel.add(bookingButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(exitButton);

        JPanel centerWrapper = new JPanel(new FlowLayout());
        centerWrapper.add(buttonPanel);

        passengerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PassengerFrame();
                dispose();
            }
        });

        flightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FlightFrame();
                dispose();
            }
        });

        bookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookFlightFrame();
                dispose();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Managebookframe();
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                    Dashboardframe.this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        add(headerPanel, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboardframe();
    }
}