package model;


//A class of VeganPizza item from menu,uses singleton
public class VeganPizza extends ItemsInMenu{
	
	private static VeganPizza instance=null;
	
	private VeganPizza() {
		super("VeganPizza",40, "Pizza with vegan cheese", "Pizza");
	}
	
	public static VeganPizza getInstance()//use singleton design pattern
	{
		if(instance==null) 
		{
			instance=new VeganPizza();
		}
		return instance;
	}
}
