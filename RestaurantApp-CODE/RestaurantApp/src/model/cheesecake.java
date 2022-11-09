package model;

//A class of Cheesecake item from menu,uses singleton
public class Cheesecake extends ItemsInMenu {
	
	private static Cheesecake instance=null;
	
	private Cheesecake() {
		super("Cheesecake",15, "Excellent cheesecake", "Dessert");
	}
	
	public static Cheesecake getInstance()//use singleton design pattern
	{
		if(instance==null) 
		{
			instance=new Cheesecake();
		}
		return instance;
	}

}
