package flightmodules;
import javax.swing.*;

public class PassengerFrame extends JFrame {

    PassengerFrame() {
        setTitle("Passenger Details");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("PASSENGER DETAILS");
        title.setBounds(170, 30, 200, 30);

        JLabel name = new JLabel("Name:");
        name.setBounds(80, 90, 100, 25);

        JTextField nameField = new JTextField();
        nameField.setBounds(180, 90, 200, 25);

        JLabel age = new JLabel("Age:");
        age.setBounds(80, 140, 100, 25);

        JTextField ageField = new JTextField();
        ageField.setBounds(180, 140, 200, 25);

        JLabel phone = new JLabel("Phone:");
        phone.setBounds(80, 190, 100, 25);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(180, 190, 200, 25);

        JLabel email = new JLabel("Email:");
        email.setBounds(80, 240, 100, 25);

        JTextField emailField = new JTextField();
        emailField.setBounds(180, 240, 200, 25);

        add(title);
        add(name);
        add(nameField);
        add(age);
        add(ageField);
        add(phone);
        add(phoneField);
        add(email);
        add(emailField);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String [] args)
    {
    	new PassengerFrame();
    }
}