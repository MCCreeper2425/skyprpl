package me.neostarbg.skypractice;

public class Manager 
{
	static Manager instance;
	public static Manager getInstance()
	{
		if(instance == null)
		{
			instance = new Manager();
		}
		
		return instance;
	}
	
	private Manager()
	{
		
	}
}
