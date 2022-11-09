package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import model.AppModel;
import view.AppView;


public class AppController implements PropertyChangeListener {
	AppModel model;
	AppView view ;
	public AppController(AppModel model,AppView view){
		this.model=model;
		this.view=view;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println(event.getPropertyName());
		// check the name of the changed property
		
		//////////////login event listeners//////////////
		if (event.getPropertyName().equals("LoginEventView")) {
				model.isAuthenticated(view.getUsernameFieldLogin(), view.getPasswordFieldLogin()); 
			}
		else if(event.getPropertyName().equals("LoginEventModel")) {
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");
			view.ChangePanels("menuPanel");
		}	
		
		///////////// log out event listeners////////////////////
		else if(event.getPropertyName().equals("LogoutEventView"))//gets userName to logoff,will use logOff method that gets userName
		{
			model.logOut(view.getUsernameFieldLogOut(),view.getPasswordFieldLogOut());
			view.ChangePanels("menuPanel");
		}
		else if(event.getPropertyName().equals("LogoutEventModel"))//will return boolen if succeded,else false(also if user not on shift)
		{
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");
			view.ChangePanels("menuPanel");
		}
		
		////////////////////add employee event listeners (manager zone panel ) /////////////////////////
		else if(event.getPropertyName().equals("AddWorkerEventView")) {
			model.addEmployee(view.getUsernameFieldAdd(), view.getPasswordFieldAdd(), view.getNewSalaryFieldAddEmployee(), view.getIsManagerField(), view.getManagerUsernameFieldAdd(), view.getManagerPasswordFieldAdd());
		}
		else if(event.getPropertyName().equals("AddWorkerEventModel")) 
		{
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");
			view.ChangePanels("menuPanel");
		}
		
		////////////////////delete employee event listeners (manager zone panel ) /////////////////////////
		else if(event.getPropertyName().equals("DeleteEmployeeEventView")) {
			this.model.deleteEmployee(view.getEmployeeToDeleteFieldDelete(), view.getManagerUsernameFieldDelete(), view.getManagerPasswordFieldDelete());
		}
		else if(event.getPropertyName().equals("DeleteEmployeeEventModel")) {
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");
			view.ChangePanels("menuPanel");
		}
		
		////////////////////update employee to manager event listeners (manager zone panel ) /////////////////////////
		else if(event.getPropertyName().equals("UpdateToManagerEventView")) //use updateToManager method from model,sent String userName,double newSalary(its per hour)
		{
			model.updateToManager(view.getUsernameFieldUpdateToManager(), Double.parseDouble(view.getNewSalaryUpdateToManager()), view.getManagerUsernameFieldUpdateToManager(), view.getManagerPasswordFieldUpdateToManager());
			view.ChangePanels("menuPanel");
		}
		else if(event.getPropertyName().equals("UpdateToManagerEventModel")) //here you get event new value as boolean
		{
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");
			view.ChangePanels("menuPanel");
		}
		
		////////////////////delete employee event listeners (manager zone panel ) /////////////////////////
		else if(event.getPropertyName().equals("ViewOrderFormView")) 
		{
			model.getMenu();
		}
		else if(event.getPropertyName().equals("ViewOrderFormModel")) //use supplyUpdate method from model,sent hasamap<int,int>-itemCode,quantity
		{
			view.initOrderPanel((ArrayList<String>) event.getNewValue());
			view.ChangePanels("orderPanel");
		}
		
		////////////////////update employee event listeners (manager zone panel ) /////////////////////////
		else if(event.getPropertyName().equals("UpdateEmployeeEventView")) {
			model.updateSalaryToEmployee(view.getUsernameFieldUpdate(), view.getNewSalaryUpdate(), view.getManagerUsernameFieldUpdate(), view.getManagerPasswordFieldUpdate());
		}
		else if(event.getPropertyName().equals("UpdateEmployeeEventModel")) {
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");

			view.ChangePanels("menuPanel");
		}
		
		//////////////////////////update inventory supplies event listeners//////////////////////////
		else if(event.getPropertyName().equals("SupplyUpdateEventView")) //use supplyUpdate method from model,sent hasamap<int,int>-itemCode,quantity
		{
			model.supplyUpdate();
		}
		else if(event.getPropertyName().equals("SupplyUpdateEventModel")) //here you get event new value as boolean,if succeed
		{
			view.showDialogMessage((String) event.getNewValue(), "mainFrame");
			view.ChangePanels("menuPanel");
		}
		
		///////////////////////show all logged in employees event listeners///////////////////////
		else if(event.getPropertyName().equals("LoggedInEventModel")) //here you get event new value as boolean,if succeed
		{
			view.initLoggedInEmployees((ArrayList<String>) event.getNewValue());//gets arraylist string of employees
		}
		else if(event.getPropertyName().equals("LoggedInEventView")) {
			view.ChangePanels("loggedInPanel");
			model.getLoggedInEmployees();
		}
		
		/////////////////////view all of the pending orders event liseners//////////////////
		else if(event.getPropertyName().equals("ViewPendingOrdersPanelView")) {
			model.getOrders();
		}
		else if(event.getPropertyName().equals("ViewPendingOrdersPanelModel")) {
			view.initPendingOrderPanel((ArrayList<String>) event.getNewValue());
			view.ChangePanels("pendingOrdersPanel");
		}
		
		////////////////////view all of the inventory supplies event listeners//////////////////////////
		else if(event.getPropertyName().equals("ViewSuppliesPanelView")) {
			model.checkInventory();
		}
		else if(event.getPropertyName().equals("ViewSuppliesPanelModel")) {
			view.initSuppliesPanel((ArrayList<String>) event.getNewValue());
			view.ChangePanels("ViewSuppliesPanel");
		}
		
		//////////////////////////make new order event listeners////////////////////
		else if(event.getPropertyName().equals("MakeOrderEventView")) 
		{
			model.makeOrder((HashMap<String, Integer>) event.getOldValue(),(HashMap<String, Integer>) event.getNewValue());
			////public HashMap<String,Integer> getMenu()
			//see return value info below in GetMenuEventModel
		}
		else if(event.getPropertyName().equals("MakeOrderEventModel")) //use supplyUpdate method from model,sent hasamap<int,int>-itemCode,quantity
		{
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");

			view.ChangePanels("menuPanel");
			//will return a hash map where key is name of product
			//value is 0 for each item ,and for each click on it the value will increase by 1
			//after that you will sent it back to me and the model will make use of it as an order
		}
		
		//////////////////////////complete an order by order id event listeners/////////////////////
		else if(event.getPropertyName().equals("CompleteOrderEventView")) {
			String orderId=view.showInputDialog("Enter the order id");
			model.removeOrder(Integer.parseInt(orderId));
			view.ChangePanels("menuPanel");
		}
		else if(event.getPropertyName().equals("CompleteOrderEventModel")) {
			view.showDialogMessage((String) event.getNewValue(), "mainFrame");
			view.ChangePanels("menuPanel");
		}
		
		////////////////////view employee information event listeners/////////////////
		else if(event.getPropertyName().equals("EmployeeInformationEventView")) {
			model.getEmployeeDetails((String)event.getOldValue());
		}
		else if(event.getPropertyName().equals("EmployeeInformationEventModel")) {
			view.showDialogMessage((String)event.getNewValue(), "mainFrame");
		}
		
	}	

	
	
	public static void main(String[] args) {
		AppModel model = new AppModel();		
		AppView view = new AppView();
		AppController controller = new AppController(model, view);
		
		////our controller will listen to the model and the view events
		view.addPropertyChangeListener(controller);
		model.addPropertyChangeListener(controller);
		view.start();
	}
}

