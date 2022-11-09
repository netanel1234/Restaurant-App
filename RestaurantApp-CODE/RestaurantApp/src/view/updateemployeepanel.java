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

public class UpdateEmployeePanel extends JPanel {

	///////////panel components/////////
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JTextField salaryField;
	private JTextField managerUsernameField;
	private JPasswordField managerPasswordField;
	String regex = "\\d+"; //check if salary contains only numbers
	private JButton btnUpdate;
	private JLabel lblUsername;
	private JLabel lblSalary;
	private JLabel lblUpdateEmployee;
	private JButton btnBack;
	private JLabel lblManagerUsername;
	private JLabel lblManagerPassword;
	PropertyChangeSupport propertyChangeHandler;
	

	/////////constructor/////////
	public UpdateEmployeePanel() {
		/////////panel initialization////////////
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		////////////components initialization////////
		usernameField = new JTextField();
		usernameField.setBounds(605, 180, 116, 22);
		add(usernameField);
		usernameField.setColumns(10);
		
		salaryField = new JTextField();
		salaryField.setBounds(605, 243, 116, 22);
		add(salaryField);
		salaryField.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//checks if one of the fields are empty
				if(getUsernameField().equals("")||getNewSalaryField().equals("")||getManagerUsernameField().equals("")
						||getManagerPasswordField().equals(""))
					{
						propertyChangeHandler.firePropertyChange("EmptyFields",0, 1);
					}
						//checks if salary field contains only numbers
					else if (salaryField.getText().matches(regex)) {
						propertyChangeHandler.firePropertyChange("UpdateEmployeeEventView",0, 1);
					}
					else {
						propertyChangeHandler.firePropertyChange("WrongParameters",0, 1);
					}
				cleanTextContainers();
			}
		});
		
		btnUpdate.setBounds(382, 459, 133, 41);
		add(btnUpdate);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(175, 180, 77, 22);
		add(lblUsername);
		
		lblSalary = new JLabel("New Salary");
		lblSalary.setBounds(175, 243, 116, 22);
		add(lblSalary);
		
		lblUpdateEmployee = new JLabel("Update Employee Form",SwingConstants.CENTER);
		lblUpdateEmployee.setBounds(254, 78, 364, 46);
		add(lblUpdateEmployee);
		
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
		lblManagerUsername.setBounds(175, 311, 153, 19);
		add(lblManagerUsername);
		
		lblManagerPassword = new JLabel("Manager Password");
		lblManagerPassword.setBounds(175, 380, 141, 19);
		add(lblManagerPassword);
		
		managerUsernameField = new JTextField();
		managerUsernameField.setBounds(605, 308, 116, 22);
		add(managerUsernameField);
		managerUsernameField.setColumns(10);
		
		managerPasswordField = new JPasswordField();
		managerPasswordField.setBounds(605, 377, 116, 22);
		add(managerPasswordField);
		managerPasswordField.setColumns(10);
		
		setFonts();
	}
	//set all the fonts for the panel components
	private void setFonts() {
		btnUpdate.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblSalary.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblUpdateEmployee.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblManagerUsername.setFont(new Font("Thaoma", Font.PLAIN, 16));
		lblManagerPassword.setFont(new Font("Thaoma", Font.PLAIN, 16));
		
	}

	public void cleanTextContainers() {//clear all the text ||password fields of the panel
		usernameField.setText("");
		salaryField.setText("");
		managerPasswordField.setText("");
		managerUsernameField.setText("");
	}
	
/////text and password fields getters
	public String getUsernameField(){
		return usernameField.getText();
	}
	
	public String getNewSalaryField(){
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
