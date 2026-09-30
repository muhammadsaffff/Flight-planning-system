package flightmodules;

import javax.swing.*;

public class Managebookframe extends JFrame {

    Managebookframe() {
        setTitle("Travel History");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("TRAVEL HISTORY");
        title.setBounds(230, 30, 200, 30);

        String[] columns = {
            "Flight No", "Departure", "Destination", "Date", "Status"
        };

        String[][] data = {
            {"FL101", "Kochi", "Delhi", "10-09-2026", "Completed"},
            {"FL202", "Mumbai", "Kochi", "15-09-2026", "Completed"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(30, 80, 520, 150);

        add(title);
        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }
 public static void main(String[] args) {
        new Managebookframe();
    }
}
