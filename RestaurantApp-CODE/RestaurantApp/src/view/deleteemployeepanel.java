package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DeleteEmployeePanel extends JPanel {

	
	////////Panel Components///////////////
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField deleteEmployeeField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblEmployeeUsername;
	private JLabel lblDeleteEmployee;
	private JButton btnDelete;
	private JButton btnBack;
	PropertyChangeSupport propertyChangeHandler;
	
	//////constructor///////
	public DeleteEmployeePanel() {
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/////// componenets initialization////////
		usernameField = new JTextField();
		usernameField.setBounds(581, 190, 116, 22);
		add(usernameField);
		usernameField.setColumns(10);
		
		lblUsername = new JLabel("username");
		lblUsername.setBounds(150, 192, 79, 22);
		add(lblUsername);
		
		lblPassword = new JLabel("password");
		lblPassword.setBounds(150, 269, 79, 19);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(581, 266, 116, 22);
		add(passwordField);
		passwordField.setColumns(10);
		
		lblEmployeeUsername = new JLabel("employee username");
		lblEmployeeUsername.setBounds(150, 340, 148, 19);
		add(lblEmployeeUsername);
		
		deleteEmployeeField = new JTextField();
		deleteEmployeeField.setBounds(581, 337, 116, 22);
		add(deleteEmployeeField);
		deleteEmployeeField.setColumns(10);
		
		lblDeleteEmployee = new JLabel("Delete employee");
		lblDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDeleteEmployee.setBounds(312, 72, 252, 35);
		add(lblDeleteEmployee);
		
		btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//check if the fields are empty
				if(usernameField.getText().equals("")||passwordField.getText().equals("")||
						deleteEmployeeField.getText().equals(""))
				{
					propertyChangeHandler.firePropertyChange("EmptyFields",0, 1);
				}
				else {
					propertyChangeHandler.firePropertyChange("DeleteEmployeeEventView",0, 1);
				}
				clearTextContainers();
			}
		});
		btnDelete.setBounds(376, 457, 138, 35);
		add(btnDelete);
		
		btnBack = new JButton("Back ->");//pressing this button make the app change to menu panel
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
				clearTextContainers();
			}
		});
		btnBack.setBounds(668, 536, 116, 35);
		add(btnBack);
		
		setFonts();
	}
	//set all the fonts for the panel components
	private void setFonts() {
		lblUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblPassword.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblEmployeeUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblDeleteEmployee.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 26));
		btnDelete.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		
	}

	public void clearTextContainers() {//clear all the text ||password fields of the panel
		this.usernameField.setText("");
		this.passwordField.setText("");
		this.deleteEmployeeField.setText("");
	}

	/////text and password fields getters
	public String getManagerUsernameField() {
		return this.usernameField.getText();
	}

	@SuppressWarnings("deprecation")
	public String getManagerPasswordField() {
		return this.passwordField.getText();
	}

	public String getEmployeeToDeleteField() {
		return this.deleteEmployeeField.getText();
	}
	
	////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
	
}
