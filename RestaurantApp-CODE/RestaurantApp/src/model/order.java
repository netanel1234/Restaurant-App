package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Order {
	
	private HashMap<String,Integer> bill;
	private String customerName;
	private int orderNum;
	
	public Order(int orderNum,HashMap<String,Integer> orderInfo,String customerName) 
	{
		this.orderNum=orderNum;
		this.bill=orderInfo;
		this.customerName=customerName;
	}
	
	
	public int sumOrder(ArrayList<Product> menu) //returns the sum of the order
	{
		int sum=0;
		for(Product product:menu)
		{
			sum+=product.getPrice()*bill.get(product.getProductName());
		}
		return sum;
	}
	public int getOrderNum()
	{
		return orderNum;
	}
	public String getCustomerName()
	{
		return this.customerName;
	}
	

}
