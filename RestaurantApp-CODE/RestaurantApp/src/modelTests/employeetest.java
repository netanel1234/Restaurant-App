package modelTests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import model.Employee;
import model.Manager;
import model.Waiter;

public class EmployeeTest {

	Employee manager;

	// This test is true both for the manager and for the waiter
	@Test
	public void testCalcWorkTime() throws InterruptedException {
		manager = new Manager("aaa", 3600);
		TimeUnit.SECONDS.sleep(5);
		int expected = 3600 / 60 / 12;
		int actual = (int) manager.calcWorkTime();
		assertSame("This is not the right salary", expected, actual);
	}

}
