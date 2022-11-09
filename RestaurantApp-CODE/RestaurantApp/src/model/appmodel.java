package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import controller.AppController;

public class AppModel {
	private LoginDatabase loginDB;//Instance of login database
	private WorkersDatabase workersDB;//Instance of workers database
	private InventoryDatabase inventoryDB;//Instance of inventory database
	private PropertyChangeSupport propertyChangeHandler;//Fires answers to the controller
	private ArrayList<User> onShiftEmpolyees;//A list of employees on call
	private ArrayList<Product> menu;//The restaurant menu, has all products for sale
	private ArrayList<Order> allOrders;//All the orders that are currently in store
	private int orderNum;//Assigns a order number to each order in a sequential order,each day resets,manager can see how many orders were today till now
	
	public AppModel()//constructor
	{
		setPropertyChangeSupport();
		loginDB=LoginDatabase.getInstance();
		workersDB=WorkersDatabase.getInstance();
		inventoryDB=InventoryDatabase.getInstance();
		onShiftEmpolyees=new ArrayList<User>();
		menu=new ArrayList<Product>();
		menu.add(Cheesecake.getInstance());
		menu.add(IceCream.getInstance());
		menu.add(ItalianPizza.getInstance());
		menu.add(RegularPizza.getInstance());
		menu.add(VeganPizza.getInstance());
		allOrders=new ArrayList<Order>();
		orderNum=0;
	}
	
///////////////LOGIN Authentication FUNCTIONS///////////////
	//Attempt login by user,returns true if connected else return false
	public void isAuthenticated(String usernameField, String passwordField) 
	{
		boolean answer=loginDB.loginAuthentication(usernameField,passwordField);//Attempt login from database,returns if successful return true,else false
		try {
			getOnShiftEmployee(usernameField,passwordField);
			propertyChangeHandler.firePropertyChange("LoginEventModel",  0,"User already logged in!!!");
			return;
		} catch (Exception e) {	
			
		}
		if(answer) {
			onShiftEmpolyees.add(workersDB.getEmployee(usernameField));//adds worker do on shift list
			propertyChangeHandler.firePropertyChange("LoginEventModel",  0,"successfully logged in");//Notify ConnectEvent was successful
		}
		else
		{
			propertyChangeHandler.firePropertyChange("LoginEventModel", 0, "Wrong username or password!");//Notify ConnectEvent was unsuccessful
		}
	}
	
	
	//A function that logs out a user that before logged in
	public void logOut(String userNameToLogOff,String userPassToLogOff)//A log off event for user
	{
		boolean answer=loginDB.loginAuthentication(userNameToLogOff,userPassToLogOff);
		if(!answer)
		{
			propertyChangeHandler.firePropertyChange("LogoutEventModel", 0, "Wrong user name or password!!!");//Notify controller log out wasn't successful
			return;
		}
		for(int i=0;i<onShiftEmpolyees.size();i++)
		{
			if(onShiftEmpolyees.get(i).getUserName().equals(userNameToLogOff))
			{
				workersDB.updateTotalSalary(userNameToLogOff, onShiftEmpolyees.get(i).calcWorkTime());
				onShiftEmpolyees.remove(i);
				propertyChangeHandler.firePropertyChange("LogoutEventModel", 0, "successfully logged out");//Notify controller log out was successful
				return;
			}
		}
		propertyChangeHandler.firePropertyChange("LogoutEventModel", 0, "Employee isnt logged in!!!");//Notify controller log out wasn't successful
	}
	

	
	
///////////////Employee management FUNCTIONS///////////////
	//Add an employee the database//needs to be checked
	public void addEmployee(String userNameToAdd,String password,double salaryPerHour,boolean isManager,String executerUserName,String executerPass)//dosent fire 0 option needs to checked
	{
		System.out.println(userNameToAdd);
		System.out.println(password);
		if(password.contains(" ") || userNameToAdd.contains(" ")||containsDigits(userNameToAdd)|| password.length()<=3||userNameToAdd.length()<=1)//Check input fields are valid
		{
			propertyChangeHandler.firePropertyChange("AddWorkerEventModel", 1, "username or password are not valid");//Notify that userName or pass are not valid
			return;
		}
		else if(workersDB.getEmployee(userNameToAdd)!=null)
		{
			propertyChangeHandler.firePropertyChange("AddWorkerEventModel", 0, "Username already exists");//Notify that user name is already taken
			return;
		}
		try {
			Manager man=(Manager) getOnShiftEmployee(executerUserName, executerPass);
			man.addEmployee(userNameToAdd, password, salaryPerHour, isManager, workersDB, loginDB);
			propertyChangeHandler.firePropertyChange("AddWorkerEventModel", 0, "Employee has been added");//Notify that user has been added
			return;
		}
		catch(Exception e)
		{
			propertyChangeHandler.firePropertyChange("AddWorkerEventModel", 0, "This action is only for logged in managers!!!");//Notify that the action was executed by a waiter and not been done
		}
	}
	
	//The method will delete an employee from the database,only by a manager
	public void deleteEmployee(String userNameToDelete,String executerUserName,String executerPass)
	{
		if(userNameToDelete.equals("Admin"))
		{
		propertyChangeHandler.firePropertyChange("DeleteEmployeeEventModel", 0, "Cannot delete Admin");//Notify controller that removed was unsuccessful
		return;
		}
		for(User user:onShiftEmpolyees)//if employee is logged in
		{
			if(user.getUserName().equals(userNameToDelete))
			{
				propertyChangeHandler.firePropertyChange("DeleteEmployeeEventModel", 0, "Employee needs to be logged off first!!!");//Notify controller that removed was unsuccessful
				return;

			}
		}
		try {
			Manager man=(Manager) getOnShiftEmployee(executerUserName, executerPass);
			man.deleteEmployee(userNameToDelete, workersDB, loginDB);
			propertyChangeHandler.firePropertyChange("DeleteEmployeeEventModel", 0, "Successfully deleted employee");//Notify controller that removed was successful			}
		}
		catch(Exception e)
		{

			propertyChangeHandler.firePropertyChange("DeleteEmployeeEventModel", 0, "This action is only for logged in managers!!!");//Notify controller that removed was unsuccessful
		}
	}

	

	//promote a waiter to manager,can only be done by a manager
	public void updateToManager(String userName,double newSalary,String executerUserName,String executerPass)
	{
		for(User user:onShiftEmpolyees)//if employee is logged in
		{
			if(user.getUserName().equals(userName))
			{
				propertyChangeHandler.firePropertyChange("UpdateToManagerEventModel", 0, "Employee needs to be logged off first!!!");//Notify controller that removed was unsuccessful
				return;

			}
		}
		if(workersDB.getEmployee(userName)==null)//if theres no such employee
		{
			propertyChangeHandler.firePropertyChange("UpdateToManagerEventModel", 0, "No such employee userName exists");	
			
		}
		try {
			
			Manager man=(Manager) getOnShiftEmployee(executerUserName, executerPass);
			man.updateToManager(userName, newSalary, workersDB);
			propertyChangeHandler.firePropertyChange("UpdateToManagerEventModel", 0, userName+" has been updated to manager");//update was successful									
		}
		catch(Exception e)
		{
			propertyChangeHandler.firePropertyChange("UpdateToManagerEventModel", 0, "This action is only for logged in managers!!!");//promotion wasn't done by a manager
		}
	}
	
	//Will only update salary for an employee,can only be done by a manager
	public void updateSalaryToEmployee(String userName,double newSalary,String executerUserName,String executerPass)
	{
		for(User user:onShiftEmpolyees)//if employee is logged in
		{
			if(user.getUserName().equals(userName))
			{
				propertyChangeHandler.firePropertyChange("UpdateEmployeeEventModel", 0, "Employee needs to be logged off first!!!");//Notify controller that removed was unsuccessful
				return;

			}
		if(workersDB.getEmployee(userName)==null)//if theres no such employee
		{
			propertyChangeHandler.firePropertyChange("UpdateEmployeeEventModel", 0, "No such employee userName exists");									
		}
		try {
			
			Manager man=(Manager) getOnShiftEmployee(executerUserName, executerPass);
			man.updateSalaryToEmployee(userName, newSalary, workersDB);
			propertyChangeHandler.firePropertyChange("UpdateEmployeeEventModel", 0, userName+" salary has been updated to "+newSalary);//update was successful									
		}
		catch(Exception e)
		{
			propertyChangeHandler.firePropertyChange("UpdateEmployeeEventModel", 0, "This action is only for logged in managers!!!");//promotion wasn't successful because wasn't done by manager
		}
		}
	}
	
	//Will send an array list of strings, with the logged in employees user name
	public void getLoggedInEmployees()
	{
		ArrayList<String> loggedInEmployees=new ArrayList<String>();
		for(User onShift:onShiftEmpolyees)
		{
			loggedInEmployees.add(onShift.getUserName());
		}
		propertyChangeHandler.firePropertyChange("LoggedInEventModel", 0, loggedInEmployees);//Send all logged in employees to controller
	}
	public void getEmployeeDetails(String userName)
	{
		String details="";
		for(User user:onShiftEmpolyees)
		{
			if(user.getUserName().equals(userName))
			{
				details+="User Name: "+user.getUserName()+"\n";
				details+="Salary per hour: "+user.getsalaryPerHour()+"\n";
				details+="Income so far: "+workersDB.getSalaryCount(userName);
				propertyChangeHandler.firePropertyChange("EmployeeInformationEventModel", 0, details);//Send all logged in employees to controller
			}
		}
		
	}
///////////////Restaurant menu FUNCTIONS///////////////	
	
	//Will notify the controller with an arrayList of the names of products in menu
	public void getMenu()
	{
		ArrayList<String> allProducts=new ArrayList<String>();
		for(Product product:this.menu)
		{
			allProducts.add(product.getProductName());
		}
		propertyChangeHandler.firePropertyChange("ViewOrderFormModel", 0, allProducts);//Notify controller with the arrayList of products names
		
	}
	
	
///////////////Restaurant orders FUNCTIONS///////////////	
	
	//Add a new order to the restaurant order list
	public void makeOrder(HashMap<String,Integer> orderInfo,HashMap<String,Integer> customerName)
	{
		String customer = null;
		String message="";
		for(Entry<String, Integer> e : customerName.entrySet())
		{
			customer=e.getKey();
		}
		HashMap<String,Integer> inventory=inventoryDB.getInventory();
		for(Product product:this.menu)
		{
			if(orderInfo.containsKey(product.getProductName()))
			{
				if(orderInfo.get(product.getProductName())>inventory.get(product.getProductName()))
				{
					propertyChangeHandler.firePropertyChange("MakeOrderEventModel", 0,"Order can not be done\nPlease check inventory" );//Notify controller that there aren't sufficient supply in inventory
					return;
				}
			}
			else
			{
				orderInfo.put(product.getProductName(), 0);
			}
		}
		inventory=inventoryDB.getInventory();
		for(Product product:this.menu)
		{
			if(inventory.get(product.getProductName())<10)
			{
				message+="Attention, inventory is running low\n\n";
				break;
			}
		}
		for(Product product:this.menu)
		{
			inventoryDB.takeFromInventory(product.getProductName(), orderInfo.get(product.getProductName()));
		}
		Order order=new Order(++orderNum,orderInfo,customer);
		allOrders.add(order);
		message+="Order was successfull, and its sum is: "+order.sumOrder(menu);
		propertyChangeHandler.firePropertyChange("MakeOrderEventModel", 0,message );//Notify controller that the order is valid
	}
	
	//After an order as been paid ,this will remove the order from the restaurant order list
	public void removeOrder(int orderNum)
	{
		for(int i=0;i<allOrders.size();i++)
		{
			if(allOrders.get(i).getOrderNum()==orderNum)
			{
				allOrders.remove(i);
			}
		}

	}
	
	//Get all pending orders info to view
	public void getOrders()
	{
		ArrayList<String> ordersInfo=new ArrayList<String>();
		for(Order order:allOrders)
		{
			System.out.println(order.getCustomerName());
			ordersInfo.add(Integer.toString(order.getOrderNum()));
			ordersInfo.add(order.getCustomerName());
			ordersInfo.add(Integer.toString(order.sumOrder(menu)));
		}
		propertyChangeHandler.firePropertyChange("ViewPendingOrdersPanelModel", 0, ordersInfo);//Notify controller that inventory DB update was unsuccessful

	}
///////////////INVETORY FUNCTIONS///////////////	
	public void supplyUpdate()
	{
		String updateLog="";
		HashMap<String,Integer> supply=new HashMap<String,Integer>();
		HashMap<String,Integer> inventory=inventoryDB.getInventory();
		for(Product product:this.menu)
		{
			supply.put(product.getProductName(), 50-inventory.get(product.getProductName()));
			updateLog+="For [roduct "+product.getProductName()+" ,"+(50-inventory.get(product.getProductName()))+" have been added to inventory\n";
		}
		if(inventoryDB.insertSupplyOrder(supply))
		{
			propertyChangeHandler.firePropertyChange("SupplyUpdateEventModel", 0, updateLog);//Notify controller that inventory DB update was unsuccessful
		}
		else
		{
			propertyChangeHandler.firePropertyChange("SupplyUpdateEventModel", 0, "An error accourd, please try again");//Notify controller that inventory DB update unsuccessful
		}
	}
	//Will return all info in inventory
	public void checkInventory()
	{
		ArrayList<String> inventoryList=new ArrayList<String>();
		HashMap<String,Integer> inventory=inventoryDB.getInventory();
		for(Product product:this.menu)
		{
			inventoryList.add(product.getProductName());
			inventoryList.add(inventory.get(product.getProductName()).toString());
		}
		propertyChangeHandler.firePropertyChange("ViewSuppliesPanelModel", 0, inventoryList);//Notify controller that inventory DB update unsuccessful

	}
///////////////MODEL PRIVATE FUNCTIONS///////////////
	
	//A method that will search for logged in employees to do manager actions,if user is not logged or is not validated will throw an exception
	private User getOnShiftEmployee(String executerUserName,String executerPass) throws Exception
	{
		if(loginDB.loginAuthentication(executerUserName, executerPass))
		{
			for(int i=0;i<=onShiftEmpolyees.size();i++)
			{
				if(onShiftEmpolyees.get(i).getUserName().equals(executerUserName))
				{
					return onShiftEmpolyees.get(i);
				}
			}
		}
		throw new Exception();

	}
	
	//method checks that a string doesn't contains digits
	private boolean containsDigits(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			if(Character.isDigit((str.charAt(i))))
			{
				return true;
			}
		}
		return false;
	}

///////////////OBSERVER FUNCTIONS///////////////	
	public void setPropertyChangeSupport() {
		propertyChangeHandler = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeHandler.addPropertyChangeListener(listener); }

}
	