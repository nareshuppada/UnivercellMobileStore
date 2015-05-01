package com.univercellmobiles.app.ui.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.transaction.Transactional.TxType;

import org.jfree.ui.RefineryUtilities;

import com.univercellmobiles.app.ui.main.AdminMainFrontEnd.ImagePanel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;

public class MainFrontEnd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	static MainFrontEnd thisRef;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrontEnd frame = new MainFrontEnd();
					RefineryUtilities.centerFrameOnScreen(frame);
					frame.setVisible(true);
					thisRef= frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrontEnd() {
		setTitle("Univercell Store login");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name ");
		lblUserName.setBounds(68, 124, 122, 14);
		panel.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password ");
		lblPassword.setBounds(68, 165, 104, 14);
		panel.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(146, 121, 205, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 162, 205, 20);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*System.out.println(passwordField.getText().equals("rajesh"));
				System.out.println(textField.getText().equals("admin"));*/
				String user = textField.getText();
				String password = passwordField.getText(); 
				if( user.equals("admin")&& password.equals("rajesh")){
					
					AdminMainFrontEnd window = new AdminMainFrontEnd();
					thisRef.dispose();
				}
				else if( user.equals("sales")&& password.equals("sales")){
					SalesMainFrontEnd smf = new SalesMainFrontEnd();
					thisRef.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,
							"Invalid Username or Password. Please try again.",
							"Invalid User",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnLogin.setBounds(262, 199, 89, 23);
		panel.add(btnLogin);
		
		ImagePanel imageP = new ImagePanel();
		imageP.setBounds(28, 11, 366, 82);
		panel.add(imageP);
	}
	
	
	class ImagePanel extends JPanel{

	    private ImageIcon imageIcon;
	    Image image;

	    public ImagePanel() {
	    	URL url = ImagePanel.class.getResource("/images/univercellLogo.png");
	    	imageIcon =new ImageIcon(url);
	    	System.out.println(url.getPath());
	    	image = imageIcon.getImage();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        //g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters          
	        g.drawImage(image, 0, 0, 366, 82, null);
	    }

	}
}
