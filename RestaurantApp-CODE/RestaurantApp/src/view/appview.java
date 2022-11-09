package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/////this is the app view class 
//it contains all the app panels
// the app view class fires changes to the controller
//controller may use view methods to update the user gui

public class AppView implements PropertyChangeListener{
	//////////view panels///////
	private PropertyChangeSupport propertyChangeHandler;
	private LoginPanel loginPanel; 
	private RestaurantMenuPanel menuPanel; // user can switch to log in , log off, make order
	//, view supplies and show logged in employees panels from this panel 
	private ManagerZonePanel managerPanel;// user can switch to add employee ,delete employee ,
	//update employee , and update employee to manager panels
	private AddEmployeePanel addEmployeePanel;
	private UpdateEmployeePanel updateEmployeePanel ;
	private LoggedInEmployeesPanel loggedInEmployessPanel;
	private MainFrame mainFrame;//we add and remove panels from this main frame
	private OrderPanel orderPanel;
	private DeleteEmployeePanel deleteEmployeePanel;
	private LogOutPanel logOutPanel;
	private UpdateToManagerPanel updateToManagerPanel;
	private PendingOrdersPanel pendingOrdersPanel;
	private ViewSuppliesPanel viewSuppliesPanel; 

	public AppView() {
		initialize();
		
	}

	private void initialize() {
		
		///////initialize all the view panels////////
		setPropertyChangeSupport();
		mainFrame = new MainFrame();
		loginPanel = new LoginPanel();
		menuPanel=new RestaurantMenuPanel();
		managerPanel=new ManagerZonePanel();
		addEmployeePanel=new AddEmployeePanel();
		updateEmployeePanel= new UpdateEmployeePanel();
		loggedInEmployessPanel=new LoggedInEmployeesPanel();
		deleteEmployeePanel = new DeleteEmployeePanel();
		orderPanel=new OrderPanel();
		logOutPanel=new LogOutPanel();
		updateToManagerPanel=new UpdateToManagerPanel();
		pendingOrdersPanel= new PendingOrdersPanel();
		viewSuppliesPanel= new ViewSuppliesPanel();
		
		/////////appview will listen to all its panels by using observer functions/////////
		loginPanel.addPropertyChangeListener(this);
		menuPanel.addPropertyChangeListener(this);
		managerPanel.addPropertyChangeListener(this);
		addEmployeePanel.addPropertyChangeListener(this);
		loggedInEmployessPanel.addPropertyChangeListener(this);
		orderPanel.addPropertyChangeListener(this);
		deleteEmployeePanel.addPropertyChangeListener(this);
		logOutPanel.addPropertyChangeListener(this);
		updateEmployeePanel.addPropertyChangeListener(this);
		updateToManagerPanel.addPropertyChangeListener(this);
		pendingOrdersPanel.addPropertyChangeListener(this);
		viewSuppliesPanel.addPropertyChangeListener(this);
		mainFrame.setContentPane(menuPanel);

	}
	
	//changing mainframe to the desirable panel
	public void ChangePanels(String newPanel) {
		switch(newPanel) {
		case"menuPanel":{
			mainFrame.setContentPane(menuPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			break;
		}
		case"loginPanel":
		{
			mainFrame.setContentPane(loginPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			break;
		}
		case"managerPanel":{	
			mainFrame.setContentPane(managerPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			break;
		}
		case"addWorkerPanel":{
			mainFrame.setContentPane(addEmployeePanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			break;
		}
		case"orderPanel":{
			mainFrame.setContentPane(orderPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			break;
		}
		case"loggedInPanel":{
			mainFrame.setContentPane(loggedInEmployessPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			break;
		}
		case"pendingOrdersPanel":{
			mainFrame.setContentPane(pendingOrdersPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			break;
		}
		case"ViewSuppliesPanel":{
			mainFrame.setContentPane(viewSuppliesPanel);
			mainFrame.revalidate();
			mainFrame.revalidate();
			break;
		}
	}
}
	//show input message to the user
	public void showDialogMessage(String message,String frame) {
		switch(frame) {
		case"mainFrame":{
			JOptionPane.showMessageDialog(mainFrame, message);
			break;
		}
	}
}
	//show dialog message to the user
	public String showInputDialog(String someString) {
		return JOptionPane.showInputDialog(someString);
	}
	/////observer functions //////
	
	//handlnig with property changes //
	///if the changes are belong to the view we will handle them here
	//else we send the changes to the controller
	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName().equals("LoginForm")){
			mainFrame.setContentPane(loginPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			
		}else if(event.getPropertyName().equals("AddWaiterForm")){
			mainFrame.setContentPane(addEmployeePanel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		else if(event.getPropertyName().equals("UpdateEmployeeForm")) {
			mainFrame.setContentPane(updateEmployeePanel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}else if(event.getPropertyName().equals("OrderForm")) {
			mainFrame.setContentPane(orderPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		else if(event.getPropertyName().equals("ManagerZone")) {
			mainFrame.setContentPane(managerPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
			
		}	
		else if(event.getPropertyName().equals("DeleteEmployeePanel")) {
			mainFrame.setContentPane(deleteEmployeePanel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		else if(event.getPropertyName().equals("BackToMenu")) {
			mainFrame.setContentPane(menuPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		else if(event.getPropertyName().equals("LogoutForm")) {
			mainFrame.setContentPane(logOutPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		else if(event.getPropertyName().equals("UptadeToManagerForm")) {
			mainFrame.setContentPane(updateToManagerPanel);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		else if(event.getPropertyName().equals("WrongParameters")) {
			showDialogMessage("You entered wrong parameters", "mainFrame");
		}
		else if(event.getPropertyName().equals("EmptyFields")) {
			showDialogMessage("One or more fields are empty","mainFrame");
		}
		else if(event.getPropertyName().equals("About")) {
			showDialogMessage("Restaurant Management app\nVersion:1.0\ncreators:\nGal Carmi\nOmer Ifrach\nNetanel Elikemel\nDudi Ahilota","mainFrame");
		}
		else {
			//send the property change to the controller
			propertyChangeHandler.firePropertyChange(event.getPropertyName(), event.getNewValue(), event.getOldValue());	
		}
	}
	

	//////controller will listen to the view changes//////
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }
	
	////run new thread for the app/////
	public void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainFrame.setVisible(true);
			}
		});
	}
	
	
	
	///////////////LOGIN FORM FUNCTIONS///////////////
	public String getUsernameFieldLogin(){
		return this.loginPanel.getUsernameField();
	}
	
	public String getPasswordFieldLogin(){
		return this.loginPanel.getPasswordField();
	}
	
	////////////////ADD WORKER FORM FUNCTIONS//////////////////
	public String getUsernameFieldAdd(){
		return this.addEmployeePanel.getUsernameField();
		
	}
	
	public String getPasswordFieldAdd(){
		return this.addEmployeePanel.getPasswordField();
	}
	
	public Double getNewSalaryFieldAddEmployee() {
		return addEmployeePanel.getSalaryField();
	}
	
	public Boolean getIsManagerField() {
		return addEmployeePanel.getIsManagerField();
	}
	
	public String getManagerUsernameFieldAdd() {
		return addEmployeePanel.getManagerUsernameField();
	}
	
	public String getManagerPasswordFieldAdd() {
		return addEmployeePanel.getManagerPasswordField();
	}
	
	/////////////Delete Worker Form Functions/////////////////
	public String getManagerUsernameFieldDelete(){
		return this.deleteEmployeePanel.getManagerUsernameField();
	}
	
	public String getManagerPasswordFieldDelete(){
		return this.deleteEmployeePanel.getManagerPasswordField();
	}
	
	public String getEmployeeToDeleteFieldDelete(){
		return this.deleteEmployeePanel.getEmployeeToDeleteField();
	}
	
	//////////update to manager functions////////////
	public String getUsernameFieldUpdateToManager() {
		return this.updateToManagerPanel.getUsernameField();
	}
	
	public String getNewSalaryUpdateToManager() {
		return updateToManagerPanel.getSalaryField();
	}
	
	public String getManagerUsernameFieldUpdateToManager() {
		return updateToManagerPanel.getManagerUsernameField();
	}
	
	public String getManagerPasswordFieldUpdateToManager() {
		return updateToManagerPanel.getManagerPasswordField();
	}
	
	//////////logout panel functions///////////
	public String getUsernameFieldLogOut() {
		return logOutPanel.getUsernameField();
	}
	
	public String getPasswordFieldLogOut() {
		return logOutPanel.getPasswordField();
	}
	
	////////loggedInEmployees panel functions///////////
	public void initLoggedInEmployees(ArrayList<String> loggedInEmployees) {
		loggedInEmployessPanel.initEmployees(loggedInEmployees);
	}
	
	///////////updateEmployee panel functions////////////////
	public String getUsernameFieldUpdate() {
		return updateEmployeePanel.getUsernameField();
	}
	
	public double getNewSalaryUpdate() {
		return Double.parseDouble(updateEmployeePanel.getNewSalaryField());
	}
	
	public String getManagerUsernameFieldUpdate() {
		return updateEmployeePanel.getManagerUsernameField();
	}
	
	public String getManagerPasswordFieldUpdate() {
		return updateEmployeePanel.getManagerPasswordField();
	}
	
	///////////order form functions///////////////
	public void  initOrderPanel(ArrayList<String> menuItems) {
		this.orderPanel.initOrderPanel(menuItems);
	}
	
	////////////pending orders panel functions///////////
	public void initPendingOrderPanel(ArrayList<String> orderList) {
		this.pendingOrdersPanel.initPendingOrdersPanel(orderList);
	}
	
	///////////////supplies panel functions//////////////
	
	public void initSuppliesPanel(ArrayList<String> products) {
		viewSuppliesPanel.initSuppliesPanel(products);
	}
	
	}
	
		


