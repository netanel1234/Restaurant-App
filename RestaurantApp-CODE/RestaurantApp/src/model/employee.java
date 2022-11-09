package model;

import java.util.Date;


//Abstract method that implements some of the functions any user can make
public abstract class Employee implements User 
{
	protected String userName;
	protected double salaryPerHour;
	protected Date login_time;
	
	public Employee(String userName,double salaryPerHour) {
		this.userName=userName;
		this.salaryPerHour=salaryPerHour;
		this.login_time=new Date();
		
	}
	
	
	
	
	
	
	public String getUserName() 
	{
		return this.userName;
	}

	public double getsalaryPerHour() 
	{
		return this.salaryPerHour;
	}
	
//On log out will calculate the total income of the shift for the employee
	public double calcWorkTime() 
	{
		
		//"new" get the time now
		Date end_working=new Date();
		
		//calculate the time from login until logout
		double total_time=end_working.getTime()-login_time.getTime();
		
		//calculate the amount of minutes in the work
		total_time=total_time/1000/60;
		
		//calculate of wages per minute of the employee
		double salary_per_minute=salaryPerHour/60;
		
		//calculate of wages for that day
		double total=total_time*salary_per_minute;
		
		return total;
	}
}
