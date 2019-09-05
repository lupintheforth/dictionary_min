package Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.io.*;
import java.awt.Font;

public class Serverwindow {

	private JFrame frame;
	private static int port = 5001;
	private static String Path = "dictionary.json";
	private MultithreadServer server;
	private JTextArea actions;
	private JTextArea log;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if((Integer.parseInt(args[0]) > 1024 && Integer.parseInt(args[0]) < 9999) && args[1] != null)
					{
					port = Integer.parseInt(args[0]);
					Path = args[1];
					}
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
		frame.setBounds(100, 100, 1029, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Action performed from the clients");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel.setBounds(30, 24, 461, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User logger");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNewLabel_1.setBounds(639, 13, 245, 54);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("connect");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 server = new MultithreadServer(port,Path);
				 Thread t = new Thread(server);
				 t.start();
			}
		});
		btnNewButton.setBounds(148, 472, 171, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try 
				{	String buffer1 = null;
					String buffer2 = null;
				
					BufferedReader ReaderforActions = new BufferedReader( new FileReader("RequestActions.txt"));
					BufferedReader Readerforlog = new BufferedReader( new FileReader("systemlogger.txt"));
					actions.setText(null);
					log.setText(null);
					while((buffer1 = ReaderforActions.readLine())!= null) 
					{	
						actions.append(buffer1+"\n");
						
					}
					while((buffer2 = Readerforlog.readLine())!= null)
					{
						log.append(buffer2+"\n");
					}
					ReaderforActions.close();
					Readerforlog.close();
					
					
					
					
					
				}
				catch(FileNotFoundException ex)
				{
					actions.setText("No file right now, wait for the request to come in");
					log.setText("No file right now, wait for the request to come in");
				}
				catch(IOException ex) 
				{
					actions.setText("reading error");
					log.setText("reading error");
					
				}
				
				
				
			}
		});
		btnNewButton_1.setBounds(675, 472, 171, 41);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 89, 423, 342);
		frame.getContentPane().add(scrollPane);
		
		this.actions = new JTextArea();
		actions.setFont(new Font("Monospaced", Font.PLAIN, 17));
		scrollPane.setViewportView(actions);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(554, 89, 417, 342);
		frame.getContentPane().add(scrollPane_1);
		
		this.log = new JTextArea();
		log.setFont(new Font("Monospaced", Font.PLAIN, 17));
		scrollPane_1.setViewportView(log);
		
		
		
		

		
		
	}
}
