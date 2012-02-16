package models;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import controller.CashierController;
import controller.OrderController;
//import java.util.List;
import enumerations.OrderStatus;

public class Cashier extends RestaurantMember {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ArrayList<Order> CompletedOrders = new ArrayList<Order>(); 
	private Queue<Order> CompletedOrders = new ConcurrentLinkedQueue<Order>(); 
	public Cashier(String firstName, String secondName) {
		super(firstName, secondName);
		setName("Cashier:"+firstName+' '+secondName);
	}

	
	public void run()
	{
		CashierMessage("been started");
		while(getLoggedIn())
		{
			//create order
			takeOrder();
			//check finished orders 
			checkFinishedOrders();
			
			try {
				sleep(CashierController.INSTANCE.GetRandomWaitTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void takeOrder()
	{
		Order order = new Order(this);
		OrderController.INSTANCE.AddOrderToQueue(order);
		CashierMessage("taken an order");
	}
	
	public void checkFinishedOrders()
	{
		//populate the list
		
		for(@SuppressWarnings("unused")
		int i = 10; 1 < 10; i--)//get up to 10 completed orders 
		{
			Order order = OrderController.INSTANCE.GetCookedOrder(this);
			if(order == null)
				break; //returns null if none present so break
			
			CompletedOrders.add(order);
		}
		
		if(!CompletedOrders.isEmpty())
		{	
			while(!CompletedOrders.isEmpty())
			{
				Order completedOrder = CompletedOrders.poll();
				completedOrder.setOrderStatus(OrderStatus.completed);
				OrderController.INSTANCE.AddOrderToFinished(completedOrder);
			}
		}else
		{
			CashierMessage("no completed orders");
		}
	}	
	
	public void addItemToFinishedOrders(Order order)
	{
		CompletedOrders.add(order);
	}
	
	public void removeItemFromFinishedOrders(Order order)
	{
		CompletedOrders.remove(order);
	}
	
	
	public void CashierMessage(String action)
	{
		System.out.println("Cashier:"+ this.getMemberName() + " has " + action + ".");
	}
	
}
