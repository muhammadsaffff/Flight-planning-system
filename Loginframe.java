package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Loginframe extends JFrame {

    Loginframe() {
        setTitle("Flight Planning System");
        setSize(500, 300);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FLIGHT PLANNING SYSTEM", JLabel.CENTER);
        JLabel welcome = new JLabel("Welcome to Flight Planning System", JLabel.CENTER);
        JButton startButton = new JButton("Start");

        JPanel messagePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        messagePanel.add(title);
        messagePanel.add(welcome);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboardframe();
                dispose();
            }
        });

        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Loginframe();
    }
}