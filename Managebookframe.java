package flightmodules;

import javax.swing.JFrame;

public class Managebookframe {

	static void managef()
	{
		JFrame manf= new JFrame();
		manf.setTitle("Manage Booking");
		manf.setSize(750,500);
		manf.setVisible(true);
		manf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args)
	{
		managef();
		
	}
}
