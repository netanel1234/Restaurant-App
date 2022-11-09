package modelTests;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.Before;
import org.junit.Test;

import model.AppModel;

public class ModelTest2 implements PropertyChangeListener{
	
	AppModel model;
	ModelTest2 test;
	
	@Before
	public void setUp()
	{
		model=new AppModel();
		test=new ModelTest2();
		model.addPropertyChangeListener(test);//test listen to model
	}
	
	//Check that someone who is not exist in the database can't connect
	@Test
	public void testLogin()//Test log in
	{
		model.isAuthenticated("no one", "1234");
	}
	
	//Check that manager/waiter in the database is trying to disconnect but has not logged in before
	@Test
	public void testLogOut()
	{
		model.logOut("gal", "1234");

	}
	
	//Check that someone does not exist in the database trying to disconnect without trying to connect
	@Test
	public void testLogOut3()
	{
		model.logOut("zzz", "119");
	}
		

	@Override
	public void propertyChange(PropertyChangeEvent event) 
	{		
		if(event.getPropertyName().equals("LoginEventModel"))
		{
			assertEquals("Test if auth was succesfull","Wrong username or password!",event.getNewValue());
		}
		else if(event.getPropertyName().equals("LogoffEventModel"))
		{
			assertEquals("is loged off","Wrong user name or password!!!",event.getNewValue());
		}

	}

	

}
