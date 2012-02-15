package models;

import java.util.ArrayList;

import controller.CashierController;
import controller.OrderController;
//import java.util.List;
import enumerations.OrderStatus;

public class Cashier extends RestaurantMember {
	
	private ArrayList<Order> CompletedOrders = new ArrayList<Order>(); 
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
		if(!CompletedOrders.isEmpty())
		{	
			for(Order order : CompletedOrders)
			{
				order.setOrderStatus(OrderStatus.completed);
				CashierMessage("completed an order");		
				CompletedOrders.remove(order);
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
