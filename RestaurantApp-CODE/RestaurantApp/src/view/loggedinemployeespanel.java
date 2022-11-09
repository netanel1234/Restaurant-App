package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;


public class LoggedInEmployeesPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private int i;
		private int j;
		JLabel lblLoggedInEmployees;
		JButton btnBack;
		JButton btnNewEmployee;
		PropertyChangeSupport propertyChangeHandler;
		
		//////constructor/////////
	public LoggedInEmployeesPanel() {
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		

	}
	
	public void initEmployees(ArrayList<String> employees) {////this function gets
		//a arraylist of employees and present them to the user by creating jbuttons
		//the buttons created by a kind of a self made grid and also initialize all the panel components
		removeAll();
		this.i=0;
		this.j=0;
		lblLoggedInEmployees = new JLabel("Logged in Employees");
		lblLoggedInEmployees.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		lblLoggedInEmployees.setBounds(290, 75, 320, 35);
		add(lblLoggedInEmployees);
		btnBack = new JButton("Back ->");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
			}
		});
		btnBack.setBounds(668, 536, 116, 35);
		btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		add(btnBack);
		//initialize buttons for all logged in employees
		for(String userName:employees) {
			
			btnNewEmployee = new JButton(userName);
			if(180+i>480) {
				i=0;
				j+=200;
			}
				
			btnNewEmployee.setBounds(100+j, 180+i, 130, 35);
			btnNewEmployee.setFont(new Font("Thaoma", Font.PLAIN, 15));
			add(btnNewEmployee);
			i+=100;
			//show employee information if its button has been clicked
			btnNewEmployee.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					propertyChangeHandler.firePropertyChange("EmployeeInformationEventView",0,userName.toString());
				}
			});
		}
	}
	////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
}
