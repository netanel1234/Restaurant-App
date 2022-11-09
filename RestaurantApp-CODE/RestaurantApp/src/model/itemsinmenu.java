package model;


//Abstarct class that implements the Product interface and all products extends
public abstract class ItemsInMenu implements Product {
	
	private String itemName;
	private double price;
	private String description;
	private String itemType;
	
	public ItemsInMenu(String itemName,double price,String description,String itemType) {
		this.itemName=itemName;
		this.price=price;
		this.description=description;
		this.itemType=itemType;
	}
	
	@Override
	public String getProductName()
	{
		return this.itemName;
	}


	@Override
	public double getPrice() 
	{
		return this.price;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String getType() {
		return this.itemType;
	}
	


}
