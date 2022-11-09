package model;


//An interface to all direct users of the app
public interface User {
	//all users can use the following methods
	public String getUserName();
	public double getsalaryPerHour();
	public double calcWorkTime();
}
