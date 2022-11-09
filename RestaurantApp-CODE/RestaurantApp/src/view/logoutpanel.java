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

public class LogOutPanel extends JPanel{
	////////Panel Components///////////////
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnLogOut;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblLogOutForm;
	private JButton btnBack;
	PropertyChangeSupport propertyChangeHandler;

	
	//////////constructor////////////
	public LogOutPanel() {
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
	/////// componenets initialization////////
		usernameField = new JTextField();
		usernameField.setBounds(586, 251, 116, 22);
		add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(586, 355, 116, 22);
		add(passwordField);
		passwordField.setColumns(10);
		
		btnLogOut = new JButton("Logout");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				//check if logout panel fields are empty
				if(usernameField.getText().equals("")||passwordField.getText().equals(""))
				{
					propertyChangeHandler.firePropertyChange("EmptyFields",0, 1);
				}
				else {
				propertyChangeHandler.firePropertyChange("LogoutEventView",0, 1);
				}
				cleanTextContainers();
			}
		});
		
		btnLogOut.setBounds(378, 493, 138, 35);
		add(btnLogOut);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(243, 250, 77, 22);
		add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(243, 354, 77, 22);
		add(lblPassword);
		
		lblLogOutForm = new JLabel("Logout Form",SwingConstants.CENTER);
		lblLogOutForm.setBounds(346, 104, 191, 44);
		add(lblLogOutForm);

		btnBack = new JButton("Back ->");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
				cleanTextContainers();
			}
		});
		btnBack.setBounds(668, 536, 116, 35);
		add(btnBack);
		
		setFonts();
	}

	//set all the fonts for the panel components
	private void setFonts() {
		btnLogOut.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblPassword.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblLogOutForm.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 26));
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		
	}


	public void cleanTextContainers() {//clear all the text ||password fields of the panel
		usernameField.setText("");
		passwordField.setText("");
	}
	
/////text and password fields getters
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
