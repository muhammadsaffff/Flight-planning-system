package flightmodules;

import javax.swing.*;

public class Dashboardframe extends JFrame {

    Dashboardframe() {
        setTitle("Flight Planning System - Dashboard");
        setSize(600, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FLIGHT PLANNING SYSTEM");
        title.setBounds(190, 30, 250, 30);

        JLabel subtitle = new JLabel("DASHBOARD");
        subtitle.setBounds(260, 70, 100, 25);

        JButton passengerButton = new JButton("Passenger Details");
        passengerButton.setBounds(180, 120, 220, 40);

        JButton flightButton = new JButton("Flight Details");
        flightButton.setBounds(180, 175, 220, 40);

        JButton bookingButton = new JButton("Booking Details");
        bookingButton.setBounds(180, 230, 220, 40);

        JButton historyButton = new JButton("Travel History");
        historyButton.setBounds(180, 285, 220, 40);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(180, 340, 220, 40);

        add(title);
        add(subtitle);
        add(passengerButton);
        add(flightButton);
        add(bookingButton);
        add(historyButton);
        add(exitButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboardframe();
    }
}