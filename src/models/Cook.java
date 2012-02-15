package models;
import controller.CookController;
import controller.OrderController;
import enumerations.OrderStatus;

public class Cook extends RestaurantMember {

	public Cook(String firstName, String secondName) {
		super(firstName, secondName);
		setName("Cook:"+firstName+' '+secondName);
	}
	
	public void run()
	{
		CookMessage("been started");
		while(getLoggedIn())
		{
			
			//refactor
			Order order = OrderController.INSTANCE.GetNextItemOffQueue();
			if(order == null)
			{
				CookMessage("no current orders");
				//thread will now wait for 7 seconds
				/*
				try {
					wait((long)7000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
				//TODO: would be nice to do this with wait
				try {
					sleep(CookController.INSTANCE.GetRandomWaitTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//return item to cashier
				CookMessage("has taken item off queue");
				order.setOrderStatus(OrderStatus.preperation);
				//prepare the item
				try {
					sleep(CookController.INSTANCE.GetRandomWaitTime());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				order.setOrderStatus(OrderStatus.cooked);	
			}
		}
		//TODO: if they still have incomplete orders do something
	}
	
	public void CookMessage(String action)
	{
		System.out.println("Cook:"+ this.getMemberName() + " has " + action + ".");
	}
	
}
