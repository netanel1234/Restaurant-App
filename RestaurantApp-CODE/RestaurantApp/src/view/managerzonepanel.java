package view;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerZonePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblManagersZone;
	private JButton btnAddEmployee;
	private JButton btnDeleteEmployee;
	private JButton btnUpdateEmployee;
	private JButton btnBack;
	private JButton btnUpdateEmployeeToManager;
	PropertyChangeSupport propertyChangeHandler;
	////////constructor////////////
	public ManagerZonePanel() {
	////panel initialization///////////
		setPropertyChangeSupport();
		setBounds(100, 100, 900, 650);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
	/////// componenets initialization////////
		
		lblManagersZone = new JLabel("Managers Zone",SwingConstants.CENTER);
		lblManagersZone.setBounds(317, 83, 261, 47);
		add(lblManagersZone);
		
		////add waiter
		btnAddEmployee = new JButton("Add Waiter");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				propertyChangeHandler.firePropertyChange("AddWaiterForm",0, 1);
			}
		});
		btnAddEmployee.setBounds(274, 203, 352, 47);
		add(btnAddEmployee);
		
		//delete waiter
		btnDeleteEmployee = new JButton("Delete Waiter");
		btnDeleteEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				propertyChangeHandler.firePropertyChange("DeleteEmployeePanel", 0, 1);
			}
		});
		
		btnDeleteEmployee.setBounds(274, 277, 352, 47);
		add(btnDeleteEmployee);
		
		//update waiter salary
		btnUpdateEmployee = new JButton("Update waiter details");
		btnUpdateEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				propertyChangeHandler.firePropertyChange("UpdateEmployeeForm", 0, 1);
			}
		});
		btnUpdateEmployee.setBounds(274, 351, 352, 47);
		add(btnUpdateEmployee);
		
		btnBack = new JButton("Back ->");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				propertyChangeHandler.firePropertyChange("BackToMenu",0, 1);
			}
		});
		btnBack.setBounds(668, 536, 116, 35);
		add(btnBack);
		
		///update employee to manager
		btnUpdateEmployeeToManager = new JButton("Update To Manager");
		btnUpdateEmployeeToManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				propertyChangeHandler.firePropertyChange("UptadeToManagerForm",0, 1);
			}
		});
		btnUpdateEmployeeToManager.setBounds(274, 429, 352, 47);
		add(btnUpdateEmployeeToManager);
		
		setFonts();
	}
	//set all the fonts for the panel components
private void setFonts() {
	lblManagersZone.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
	btnAddEmployee.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
	btnDeleteEmployee.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
	btnUpdateEmployee.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
	btnBack.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
	btnUpdateEmployeeToManager.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		
	}

////////observer functions app view will listen to this panel/////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
}
