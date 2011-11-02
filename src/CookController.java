import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum CookController {
	INSTANCE;

	private Random generator = new Random();
	
	//private ArrayList<Cook> Cooks = new ArrayList<Cook>();
	private List<Cook> Cooks = Collections.synchronizedList(new ArrayList<Cook>());
	
	public int GetRandomWaitTime()
	{
		return generator.nextInt(7001);
	}
	
	public void CookSignIn(Cook cook){
		Cooks.add(cook);
		cook.setLoggedIn();
	}
	
	public void CookSignOut(Cook cook){
		//TODO: add check to see if cook has any current orders
		Cooks.remove(cook);
		cook.setLoggedOut();
	}
	
	
	//TODO: would this be better if it was using notify all, should a random item be called on the method ? 
	public void notifyCooksOfNewOrder()
	{
		for( Cook cook : Cooks)
		{
			cook.notifyAll();
			break;
		}
	}
	
}
