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

public class UpdateToManagerPanel extends JPanel {

	////////panel components//////////
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JTextField salaryField;
	private JTextField managerUsernameField;
	private JPasswordField managerPasswordField;
	String regex = "\\d+"; //check if salary contains only numbers
	private JButton btnUpdate;
	private JLabel lblUsername;
	private JLabel lblSalary;
	private JLabel lblUpdateToManagerForm;
	private JButton btnBack;
	private JLabel lblManagerUsername;
	private JLabel lblManagerPassword;
	PropertyChangeSupport propertyChangeHandler;

	///////////constructor//////////
	public UpdateToManagerPanel() {
		///////panel initialization///////////
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		/////////components initialization////////
		usernameField = new JTextField();
		usernameField.setBounds(604, 177, 116, 22);
		add(usernameField);
		usernameField.setColumns(10);
		
		salaryField = new JTextField();
		salaryField.setBounds(604, 245, 116, 22);
		add(salaryField);
		salaryField.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//checks if one of the fields are empty
				if(getUsernameField().equals("")||getSalaryField().equals("")||getManagerUsernameField().equals("")
					||getManagerPasswordField().equals(""))
				{
					propertyChangeHandler.firePropertyChange("EmptyFields",0, 1);
				}
					//checks if salary fields contains only numbers
				else if (salaryField.getText().matches(regex)) {
					propertyChangeHandler.firePropertyChange("UpdateToManagerEventView",0, 1);
				}
				else {
					propertyChangeHandler.firePropertyChange("WrongParameters",0, 1);
				}
				cleanTextContainers();
			}
		});
		
		btnUpdate.setBounds(386, 482, 125, 40);
		add(btnUpdate);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(172, 177, 77, 22);
		add(lblUsername);
		
		lblSalary = new JLabel("New Salary");
		lblSalary.setBounds(172, 245, 109, 22);
		add(lblSalary);
		
		lblUpdateToManagerForm = new JLabel("Update Employee To Manager Form",SwingConstants.CENTER);
		lblUpdateToManagerForm.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblUpdateToManagerForm.setBounds(172, 58, 540, 57);
		add(lblUpdateToManagerForm);
		
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
		
		lblManagerUsername = new JLabel("Manager Username");
		lblManagerUsername.setBounds(172, 319, 150, 19);
		add(lblManagerUsername);
		
		lblManagerPassword = new JLabel("Manager Password");
		lblManagerPassword.setBounds(172, 380, 150, 19);
		add(lblManagerPassword);
		
		managerUsernameField = new JTextField();
		managerUsernameField.setBounds(604, 316, 116, 22);
		add(managerUsernameField);
		managerUsernameField.setColumns(10);
		
		managerPasswordField = new JPasswordField();
		managerPasswordField.setBounds(604, 380, 116, 22);
		add(managerPasswordField);
		managerPasswordField.setColumns(10);
		
		setFonts();
	}
	//set all the fonts for the panel components
	private void setFonts() {
		btnUpdate.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblSalary.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblUpdateToManagerForm.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblManagerUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblManagerPassword.setFont(new Font("Thaoma", Font.PLAIN, 16));
	}

	//clear all of the panel fields
	public void cleanTextContainers() {
		usernameField.setText("");
		salaryField.setText("");
		managerPasswordField.setText("");
		managerUsernameField.setText("");
	}
	
/////text and password fields getters
	public String getUsernameField(){
		return usernameField.getText();
	}
	
	public String getSalaryField() {
		return salaryField.getText();
	}
	
	public String getManagerUsernameField(){
		return managerUsernameField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getManagerPasswordField(){
		return managerPasswordField.getText();
	}
	
////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }


}
