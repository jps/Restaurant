package models;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import clients.CookClient;
import enumerations.OrderStatus;

public class Cook extends RestaurantMember {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient CookClient cookClient;

	// This list should never really get long as it is just a mechanism for
	// ensuring orders arn't lost
	private Queue<Order> WaitingOrders = new ConcurrentLinkedQueue<Order>();

	public Cook(String firstName, String secondName, CookClient CookClient) {
		super(firstName, secondName);
		setName("Cook:" + firstName + ' ' + secondName);
		cookClient = CookClient;

	}
	
	
	public synchronized void addToWaiting(Order order)
	{
		WaitingOrders.add(order);
		this.notifyAll();
	}
	
	public synchronized void run()
	{
		CookMessage("been started");
		while(getLoggedIn())
		{
			
			Order order = WaitingOrders.poll();
			
			if(order == null)
			{
				//request a new order
				cookClient.outQueue.OutQueue.add("CookOrderRequest");
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					wait(GetRandomWaitTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				order.setCookedAtNow();
				order.setOrderStatus(OrderStatus.cooked);
				cookClient.outQueue.OutQueue.add(order);//pass back to server
			}
		}
		//TODO: if they still have incomplete orders do something
	}

	public void CookMessage(String action) {
		System.out.println("Cook:" + this.getMemberName() + " has " + action
				+ ".");
	}
	
	public int GetRandomWaitTime() {
		Random generator = new Random();
		return generator.nextInt(7001);
	}
	

}
