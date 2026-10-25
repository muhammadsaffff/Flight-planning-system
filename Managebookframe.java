package flightmodules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Managebookframe extends JFrame {

    Managebookframe() {
        setTitle("Travel History");
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("TRAVEL HISTORY", JLabel.CENTER);

        String[] columns = {
            "Flight No", "Departure", "Destination", "Date", "Status"
        };

        String[][] data = {
            {"FL101", "Kochi", "Delhi", "10-09-2026", "Completed"},
            {"FL202", "Mumbai", "Kochi", "15-09-2026", "Completed"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(refreshButton);
        southPanel.add(backButton);

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Managebookframe.this, "Travel history refreshed.");
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboardframe();
                dispose();
            }
        });

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Managebookframe();
    }
}