package flightmodules;
import javax.swing.*;

public class Loginframe extends JFrame {

    LoginFrame() {
        setTitle("Flight Planning System");
        setSize(500, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("FLIGHT PLANNING SYSTEM");
        title.setBounds(130, 80, 250, 30);

        JLabel welcome = new JLabel("Welcome to Flight Planning System");
        welcome.setBounds(140, 130, 250, 30);

        add(title);
        add(welcome);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}