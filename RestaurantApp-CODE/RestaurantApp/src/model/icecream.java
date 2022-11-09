package model;

//A class of IceCream item from menu,uses singleton
public class IceCream extends ItemsInMenu{
	
	private static IceCream instance=null;

	private IceCream() {
		super("Ice Cream",10, "Tasty ice cream", "Dessert");
	}
	
	public static IceCream getInstance()//use singleton design pattern
	{
		if(instance==null) 
		{
			instance=new IceCream();
		}
		return instance;
	}

}
