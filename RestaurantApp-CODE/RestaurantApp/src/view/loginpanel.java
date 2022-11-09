package view;


import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import java.awt.Font;

public class LoginPanel extends JPanel{

	////////Panel Components///////////////
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnSignIn;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblLoginForm;
	private JButton btnBack;
	PropertyChangeSupport propertyChangeHandler;
	
	//////constructor////////////
	public LoginPanel() {
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		////////components initialization/////////
		usernameField = new JTextField();
		usernameField.setBounds(595, 269, 116, 22);
		add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(595, 356, 116, 22);
		add(passwordField);
		passwordField.setColumns(10);
		
		btnSignIn = new JButton("Sign in");
		btnSignIn.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				//check if login panel fields are empty
				if(usernameField.getText().equals("")||passwordField.getText().equals(""))
				{
					propertyChangeHandler.firePropertyChange("EmptyFields",0, 1);
				}
				else {
				propertyChangeHandler.firePropertyChange("LoginEventView",0, 1);
				}
				cleanTextContainers();
			}
		});
		
		btnSignIn.setBounds(378, 496, 138, 35);
		add(btnSignIn);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(233, 268, 77, 22);
		add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(233, 355, 77, 22);
		add(lblPassword);
		
		lblLoginForm = new JLabel("Login Form",SwingConstants.CENTER);
		lblLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLoginForm.setBounds(351, 81, 188, 41);
		add(lblLoginForm);

		btnBack = new JButton("Back ->");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
				cleanTextContainers();
			}
		});
		btnBack.setBounds(668, 557, 116, 35);
		add(btnBack);
		
		setFonts();
	}

	//set all the fonts for the panel components
	private void setFonts() {
		btnSignIn.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblPassword.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblLoginForm.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		
	}


	public void cleanTextContainers() {//clear all the text ||password fields of the panel
		usernameField.setText("");
		passwordField.setText("");
	}
	
	////////components getters//////////
	public String getUsernameField(){
		return usernameField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPasswordField(){
		return passwordField.getText();
	}
	
	////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }




}
