package model;


//All products share this interface
public interface Product 
{
	//Each product can use the following methods
	String getProductName();
	double getPrice();
	String getDescription();
	String getType();
}
