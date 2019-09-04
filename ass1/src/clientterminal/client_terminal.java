package clientterminal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class client_terminal {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static String ip ="localhost";
	private static int port = 5001;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
	
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client_terminal window = new client_terminal();
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
	public client_terminal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setBounds(100, 100, 1254, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField("here to type in the word");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(26, 28, 566, 84);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("here to type in the meaning(only type in using add)");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(26, 129, 566, 112);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String word = textField.getText();
				String meanings = textField_1.getText();
				
				clientdoing newrequest = new clientdoing(ip,port);
				
				String result = newrequest.sendRequest(1,word,meanings);
				textField_2.setText(result);
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(885, 44, 171, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("remove");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String word = textField.getText();
				String meanings = textField_1.getText();
				
				clientdoing newrequest = new clientdoing(ip,port);
				
				String result = newrequest.sendRequest(2,word,meanings);
				textField_2.setText(result);
				
			}
		});
		btnNewButton_1.setBounds(885, 164, 171, 41);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("search");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String word = textField.getText();
				String meanings = textField_1.getText();
				
				clientdoing newrequest = new clientdoing(ip,port);
				
				String result = newrequest.sendRequest(3,word,meanings);
				textField_2.setText(result);
				
			}
		});
		btnNewButton_2.setBounds(885, 305, 171, 41);
		frame.getContentPane().add(btnNewButton_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(26, 269, 566, 104);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
	}
}
