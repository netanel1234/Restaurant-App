package modelTests;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.AppModel;

public class ModelTest implements PropertyChangeListener {
	AppModel model;
	ModelTest test;
	
	@Before
	public void setUp()
	{
		model=new AppModel();
		test=new ModelTest();
		model.addPropertyChangeListener(test);//test listen to model
	}

	@Test
	public void testEventSequence1() //Will test sequence1 for Manager login and permissions
	{
		model.isAuthenticated("yossi", "12345");//Manager Log In
		model.addEmployee("kaki","1234",20 , false, "yossi", "12345");//Manager adds an employee
		model.isAuthenticated("kaki", "1234");//Attempt to log in with new password for employee
		model.logOut("kaki", "1234");//Log out with employee
		model.deleteEmployee("kaki", "yossi", "12345");//Manager deletes employee
		model.logOut("yossi", "12345");//Manager logs out
	}
	
	@Test
	public void testLogin()//Test log in
	{
		model.isAuthenticated("yossi", "12345");
	}
	@Test
	public void testLogOut()//Test log in and then log out
	{
		testLogin();
		model.logOut("yossi", "12345");
	}
	@Test
	public void testLoggedInEmployee()//Test login and get all logged in employees and then log out
	{
		testLogin();
		model.getLoggedInEmployees();
		model.logOut("yossi", "12345");
	}
	@Test
	public void testSupplyisUpdated()//An update to supply is done
	{
		HashMap<String, Integer> supply = new HashMap<>();
		supply.put("Cheesecake", 1);
		supply.put("Ice Cream", 1);
		supply.put("Italian Pizza", 1);
		supply.put("Regular Pizza", 1);
		supply.put("VeganPizza", 1);
	}
	@Test
	public void testModelMenu()//Test that that getMenu returns the desired...
	{
		model.getMenu();
	}
	@Test
	public void testOrders()//Test that an order as been made and it returs the sum of the order
	{
		HashMap<String, Integer> productsInOrder = new HashMap<>();
		productsInOrder.put("Ice Cream", 1);
		productsInOrder.put("Italian Pizza", 1);
		productsInOrder.put("Regular Pizza", 1);
		productsInOrder.put("VeganPizza", 1);
		HashMap<String, Integer> customerName=new HashMap<String, Integer>();
		customerName.put("yossi", 0);
		model.makeOrder(productsInOrder, customerName);
		model.getOrders();
	}


	@Override
	public void propertyChange(PropertyChangeEvent event) {

		if(event.getPropertyName().equals("LoginEventModel"))
		{
			assertEquals("Test if auth was succesfull","successfully logged in",event.getNewValue());
		}
		else if(event.getPropertyName().equals("LogoffEventModel"))
		{
			assertEquals("is loged off","successfully logged out",event.getNewValue());
		}
		else if(event.getPropertyName().equals("AddWorkerEventModel"))
		{
			assertEquals("employee as been added","Employee has been added",event.getNewValue());			
		}
		else if(event.getPropertyName().equals("DeleteEmployeeEventModel"))
		{
			assertEquals("employee as been deleted","Successfully deleted employee",event.getNewValue());
		}
		else if(event.getPropertyName().equals("LoggedInEventModel"))
		{
			ArrayList<String> loggedIn=(ArrayList<String>) event.getNewValue();
			for(String name:loggedIn)
			{
				assertEquals("logged in employees are right","yossi",name);
			}
		}
		else if(event.getPropertyName().equals("ViewOrderFormModel"))
		{
			int i=0;
			ArrayList<String> menu=(ArrayList<String>) event.getNewValue();
			for(String product:menu)
			{
				if(i==0)
				{
					assertEquals("Match to menu","Cheesecake",product);
				}
				else if(i==1)
				{
					assertEquals("Match to menu","Ice Cream",product);
				}
				else if(i==2)
				{
					assertEquals("Match to menu","Italian Pizza",product);
				}
				else if(i==3)
				{
					assertEquals("Match to menu","Regular Pizza",product);
				}
				else if(i==4)
				{
					assertEquals("Match to menu","VeganPizza",product);	
				}
				i++;
				
			}
		}
		else if(event.getPropertyName().equals("MakeOrderEventModel"))
		{
			assertEquals("Sum of order is currect",115,event.getNewValue());	

		}
		else if(event.getPropertyName().equals("ViewPendingOrdersPanelModel"))
		{
			ArrayList<String> orders=(ArrayList<String>) event.getNewValue();
			System.out.println();
			assertEquals("logged in employees are right","1",orders.get(0));
			assertEquals("logged in employees are right","yossi",orders.get(1));	
			assertEquals("logged in employees are right","115",orders.get(2));	

		}
	}

}
