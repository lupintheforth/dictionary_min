package Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public class Serverwindow {

	private JFrame frame;
	private static int port = 5001;
	private static String Path = "dictionary.json";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Serverwindow window = new Serverwindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Serverwindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1029, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(49, 28, 868, 258);
		frame.getContentPane().add(textPane);
	}
}
