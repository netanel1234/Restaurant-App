package view;


import javax.swing.JPanel;
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
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class AddEmployeePanel extends JPanel {
	

	private static final long serialVersionUID = 1L;
	
	////////Panel Components///////////////
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblAddWaiterForm;
	private JButton btnBack;
	private JLabel lblManagerUsername;
	private JLabel lblManagerPassword;
	private JLabel lblIsManager ;
	private JLabel lblSalary;
	private JButton btnAdd;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField managerUsernameField;
	private JPasswordField managerPasswordField;
	PropertyChangeSupport propertyChangeHandler;
	private JTextField salaryField;
	private JCheckBox isManager;
	String regex = "\\d+"; //check if salary contains only numbers

	//// constructor/////////
	public AddEmployeePanel() {
		
		////panel initialization///////////
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		
		/////// componenets initialization////////
		usernameField = new JTextField();
		usernameField.setBounds(565, 130, 116, 22);
		add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(565, 182, 116, 22);
		add(passwordField);
		passwordField.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				//checks if any of the input fields are empty
				if(usernameField.getText().equals("")||passwordField.getText().equals("")
						||managerUsernameField.getText().equals("")||managerPasswordField.getText().equals("")
						||salaryField.getText().equals(""))
				{
					propertyChangeHandler.firePropertyChange("EmptyFields",0, 1);
				}
				//checks if the salary filed contains only numbers
				else if(salaryField.getText().matches(regex)) {
					propertyChangeHandler.firePropertyChange("AddWorkerEventView",0, 1);
				}
				else {
					propertyChangeHandler.firePropertyChange("WrongParameters",0, 1);
				}
				cleanTextContainers();
			}
		});
		
		btnAdd.setBounds(376, 457, 138, 35);
		add(btnAdd);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(203, 130, 77, 22);
		add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(203, 182, 77, 22);
		add(lblPassword);
		
		lblAddWaiterForm = new JLabel("Add Employee Form",SwingConstants.CENTER);
		lblAddWaiterForm.setBounds(276, 43, 311, 43);
		add(lblAddWaiterForm);
		
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
		lblManagerUsername.setBounds(203, 241, 160, 22);
		add(lblManagerUsername);
		
		lblManagerPassword = new JLabel("Manager Password");
		lblManagerPassword.setBounds(203, 289, 150, 19);
		add(lblManagerPassword);
		
		managerUsernameField = new JTextField();
		managerUsernameField.setBounds(565, 242, 116, 22);
		add(managerUsernameField);
		managerUsernameField.setColumns(10);
		
		managerPasswordField = new JPasswordField();
		managerPasswordField.setBounds(565, 288, 116, 22);
		add(managerPasswordField);
		managerPasswordField.setColumns(10);
		
		lblIsManager = new JLabel("Is Manager?");
		lblIsManager.setBounds(203, 400, 116, 25);
		add(lblIsManager);
		
		isManager = new JCheckBox("");
		isManager.setBounds(329, 400, 113, 25);
		add(isManager);
		
		lblSalary = new JLabel("Salary");
		lblSalary.setBounds(203, 345, 77, 19);
		add(lblSalary);
		
		salaryField = new JTextField();
		salaryField.setBounds(565, 344, 116, 22);
		add(salaryField);
		salaryField.setColumns(10);
		
		setFonts();
		
	}

	//set all the fonts for the panel components
	private void setFonts() {
		lblUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblPassword.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblAddWaiterForm.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 26));
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblManagerUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblManagerPassword.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblIsManager.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblSalary.setFont(new Font("Thaoma", Font.PLAIN, 16));
		btnAdd.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		
	}


	public void cleanTextContainers() { //clear all the text ||password fields of the panel
		usernameField.setText("");
		passwordField.setText("");
		managerPasswordField.setText("");
		managerUsernameField.setText("");
		salaryField.setText("");
		isManager.setSelected(false);
	}
	
	
	/////text and password fields getters
	public String getUsernameField(){
		return usernameField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPasswordField(){
		return passwordField.getText();
	}
	
	public String getManagerUsernameField(){
		return managerUsernameField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getManagerPasswordField(){
		return managerPasswordField.getText();
	}
	
	public Double getSalaryField() {
		return Double.parseDouble(this.salaryField.getText());
	}
	
	public Boolean getIsManagerField() {
		return this.isManager.isSelected();
	}
	
	
	////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
}
