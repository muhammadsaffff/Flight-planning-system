package flightmodules;
import javax.swing.*;

public class FlightFrame extends JFrame {

    FlightFrame() {
        setTitle("Flight Details");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FLIGHT DETAILS");
        title.setBounds(180, 30, 200, 30);

        JLabel from = new JLabel("Departure:");
        from.setBounds(70, 90, 100, 25);

        JTextField fromField = new JTextField();
        fromField.setBounds(180, 90, 200, 25);

        JLabel to = new JLabel("Destination:");
        to.setBounds(70, 140, 100, 25);

        JTextField toField = new JTextField();
        toField.setBounds(180, 140, 200, 25);

        JLabel date = new JLabel("Date:");
        date.setBounds(70, 190, 100, 25);

        JTextField dateField = new JTextField();
        dateField.setBounds(180, 190, 200, 25);

        JLabel flightNo = new JLabel("Flight No:");
        flightNo.setBounds(70, 240, 100, 25);

        JTextField flightField = new JTextField();
        flightField.setBounds(180, 240, 200, 25);

        add(title);
        add(from);
        add(fromField);
        add(to);
        add(toField);
        add(date);
        add(dateField);
        add(flightNo);
        add(flightField);

        setLocationRelativeTo(null);
        setVisible(true);
    }

     
     public static void main(String [] args)
     {
    	 new FlightFrame();
     }
 }
