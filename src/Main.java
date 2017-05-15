import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	
	static int WIDTH = 1500;
	static int HEIGHT = 860;
	public static SimpleDateFormat today; 
	
	private static void Frame(){
		JFrame input = new Window();	
		input.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		input.setResizable(true);
		input.pack();
		input.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				Frame();
				
			}
		}
	);

	}

}