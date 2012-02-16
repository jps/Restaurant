package models;
import java.util.Date;

import controller.OrderController;
import enumerations.OrderStatus;


public class Order {

	private long OrderNumber;
	private OrderStatus OrderStatus;
	private Cashier Cashier;
	private Date CookedAt;
	
		
	Order(Cashier cashier){
		OrderNumber = OrderController.INSTANCE.getNextOrderNumber();
		setOrderStatus(enumerations.OrderStatus.pending);
		setCashier(cashier);
		
	}
	
	Order(long orderNumber){
		setOrderStatus(enumerations.OrderStatus.pending);
		OrderNumber = orderNumber;
	}
		
	public long getOrderNumber() {
		return OrderNumber;
	}

	/*
	private void setOrderNumber(long orderNumber) {
		OrderNumber = orderNumber;
	}*/
	
	public OrderStatus getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		OrderMessage( "changed order status to " + orderStatus );
		OrderStatus = orderStatus;
	}

	public Cashier getCashier() {
		return Cashier;
	}

	private void setCashier(Cashier cashier) {
		Cashier = cashier;
	}
	
	public void OrderMessage(String action)
	{
		System.out.println("Order:" + getOrderNumber() + " has " + action + ".");
	}
	
	public void setCookedAtNow()
	{
		CookedAt  = new Date(); 
	}
	
	public Date getCookedAt()
	{
		return CookedAt; 
	}
	
	
}
	
