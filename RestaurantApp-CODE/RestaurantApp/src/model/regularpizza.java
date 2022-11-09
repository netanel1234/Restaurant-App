package model;


//A class of RegularPizza item from menu,uses singleton
public class RegularPizza extends ItemsInMenu {
	
	private static RegularPizza instance=null;

	private RegularPizza() {
		super("Regular Pizza",30, "Regular pizza with mozzarella cheese", "Pizza");
	}
	
	public static RegularPizza getInstance()//use singleton design pattern
	{
		if(instance==null) 
		{
			instance=new RegularPizza();
		}
		return instance;
	}



}
