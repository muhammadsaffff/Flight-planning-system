package flightmodules;
import javax.swing.*;
public class Loginframe {
	
	static void LoginFram() {
		JFrame l = new JFrame();
        l.setTitle("Login");
        l.setSize(300, 200);
        l.setVisible(true);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
	
	
	public static void main(String [] args) 
	{
		LoginFram();
	}
	
}