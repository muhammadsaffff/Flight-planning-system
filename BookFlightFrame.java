package flightmodules;



import javax.swing.*;

public class BookFlightFrame extends JFrame {

    BookFlightFrame() {
        setTitle("Booking Details");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("BOOKING DETAILS");
        title.setBounds(170, 30, 200, 30);

        JLabel passenger = new JLabel("Passenger:");
        passenger.setBounds(70, 90, 100, 25);

        JTextField passengerField = new JTextField();
        passengerField.setBounds(180, 90, 200, 25);

        JLabel flight = new JLabel("Flight:");
        flight.setBounds(70, 140, 100, 25);

        JTextField flightField = new JTextField();
        flightField.setBounds(180, 140, 200, 25);

        JLabel travelClass = new JLabel("Travel Class:");
        travelClass.setBounds(70, 190, 100, 25);

        JTextField classField = new JTextField();
        classField.setBounds(180, 190, 200, 25);

        JLabel fare = new JLabel("Fare:");
        fare.setBounds(70, 240, 100, 25);

        JTextField fareField = new JTextField();
        fareField.setBounds(180, 240, 200, 25);

        add(title);
        add(passenger);
        add(passengerField);
        add(flight);
        add(flightField);
        add(travelClass);
        add(classField);
        add(fare);
        add(fareField);

        setLocationRelativeTo(null);
        setVisible(true);
    }

public static void main(String [] agrs)
	{
		new BookFlightFrame();
	}
}
