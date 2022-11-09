package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;


//A manager class extends Employee,can make manager actions
public class Manager extends Employee {
	
	public Manager(String userName,double salaryPerHour) 
	{
		super(userName,salaryPerHour);
	}
	
///////////////MANAGER UNIQE FUNCTIONS///////////////

	
	//A manager can add an employee do database
	public int addEmployee(String userName,String password,double salaryPerHour,boolean isManager,WorkersDatabase workersDB,LoginDatabase loginDB)
	{
		if(userName.isEmpty()|| password.isEmpty()||userName.contains(" ")||password.contains(" "))//if username or password is empty or contains spaces return 0 to controller
		{
			return 0;//Notify addWorkerEventModel to controller,result 0=fail
		}
		else if(workersDB.getEmployee(userName)!=null) //If employee username is taken
		{
			return 1;//Notify addWorkerEventModel to controller,result 1=fail
		}
		else {
			loginDB.insertLoginInfo(userName, password);//Add login info to DB
			if(isManager) 
			{
				workersDB.insertWorkerInfo(new Manager(userName,salaryPerHour));//Add new manager to DB
			}
			else {
				workersDB.insertWorkerInfo(new Waiter(userName,salaryPerHour));//Add new waiter to DB
			}
			return 2;//Notify addWorkerEventModel to controller,result 2=success
		}
	}
	
	//A manager can delete an employee from the database
	public void deleteEmployee(String userName, WorkersDatabase workersDB,LoginDatabase loginDB)//only a manager can delete an employee
	{
		workersDB.delete(userName);
		loginDB.delete(userName);
	}
	
	//A manager can promote a waiter to manager
	public boolean updateToManager(String userName,double newSalary,WorkersDatabase workersDB)
	{
		return workersDB.updateToManager(userName, newSalary);
	}
	
	//A manager can update the salary of an employee
	public boolean updateSalaryToEmployee(String userName,double newSalary,WorkersDatabase workersDB)
	{
		return workersDB.updateSalaryToEmployee(userName, newSalary);
	}



	
}
