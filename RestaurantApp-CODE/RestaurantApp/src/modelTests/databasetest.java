package modelTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.InventoryDatabase;
import model.LoginDatabase;
import model.WorkersDatabase;

public class DatabaseTest {

	LoginDatabase loginDB;
	WorkersDatabase workersDB;
	InventoryDatabase inventoryDB;
	
	@Before
	public void setUp()
	{
		loginDB=LoginDatabase.getInstance();
		workersDB=WorkersDatabase.getInstance();
		inventoryDB=InventoryDatabase.getInstance();
	}

	@Test
	public void testLoginAuth1() {
		assertEquals("Insert login info successfull",true,loginDB.loginAuthentication("yossi", "12345"));
	}
	@Test
	public void testLoginAuth2()
	{
		assertEquals("Insert login info successfull",false,loginDB.loginAuthentication("NoOne", "12345"));
	}
	@Test
	public void testWorkersDB()
	{
		assertEquals("Insert login info successfull","yossi",workersDB.getEmployee("yossi").getUserName());
	}
	@Test
	public void testInventoryDB()
	{
		
	}
}
