package model;


//A class of ItalianPizza item from menu,uses singleton
public class ItalianPizza extends ItemsInMenu{
	
	private static ItalianPizza instance=null;
	
	private ItalianPizza() 
	{
		super("Italian Pizza",35, "Pizza made from Italian dough with pesto spread", "Pizza");
	}
	
	public static ItalianPizza getInstance()//use singleton design pattern
	{
		if(instance==null) 
		{
			instance=new ItalianPizza();
		}
		return instance;
	}

}
