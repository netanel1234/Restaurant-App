package modelTests;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.AppModel;

public class ModelTest3 implements PropertyChangeListener {

	AppModel model;
	ModelTest3 test;
	
	@Before
	public void setUp()
	{
		model=new AppModel();
		test=new ModelTest3();
		model.addPropertyChangeListener(test);//test listen to model
	}
	
	//waiter trying to do actions of manager
	@Test
	public void testAddEmployee()
	{
		model.isAuthenticated("gal", "2424");
		model.addEmployee("kaki","1234",20 , true,"gal", "2424");
		model.deleteEmployee("yossi","gal", "2424");
		model.updateToManager("yossi", 900, "gal", "2424");
		model.updateSalaryToEmployee("yossi", 999, "gal", "2424");
		model.logOut("gal", "2424");
	}
	
	//manager trying to do illegal actions,on someone who doesn't exist
	@Test
	public void testDeleteEmployee()
	{
		model.isAuthenticated("yossi", "12345");
		model.addEmployee("gal", "2424", 43, false, "yossi", "12345");//already exists
		model.deleteEmployee("kaka","nati", "2424");//kaka doesn't exist
		model.updateToManager("kaka", 32332, "yossi", "12345");//Can't promote kaka because he doesn't exist
		model.logOut("yossi", "12345");
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
			if(!event.getNewValue().equals("Employee has been added"))
			{
				assertEquals("employee has not been added",true,true);	
			}
		}
		else if(event.getPropertyName().equals("DeleteEmployeeEventModel"))
		{
			if(!event.getNewValue().equals("Successfully deleted employee"))
			{
			assertEquals("employee as been deleted",true,true);
			}
		}
		else if(event.getPropertyName().equals("UpdateToManagerEventModel"))
		{
			if(!event.getNewValue().equals("kaka has been updated to manager"))
			{
			assertEquals("employee cant be updated",true,true);
			}
		}
		else if(event.getPropertyName().equals("UpdateEmployeeEventModel"))
		{//userName+" salary has been updated to "+newSalary
			if(!event.getNewValue().equals("yossi salary has been updated to "+999))
			{
			assertEquals("employee cant be updated",true,true);
			}
		}
		

		
		
	}
}
