package models;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import controller.OrderController;
import enumerations.OrderStatus;


public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID orderNumber;
	private OrderStatus orderStatus;
	private Cashier cashier;
	private Cook cook; 
	private Date cookedAt;
	
		
	Order(Cashier cashier){
		orderNumber = OrderController.INSTANCE.getNextOrderNumber();
		setOrderStatus(enumerations.OrderStatus.pending);
		setCashier(cashier);		
	}
	
	Order(UUID OrderNumber){
		setOrderStatus(enumerations.OrderStatus.pending);
		orderNumber = OrderNumber;
	}
		
	public UUID getOrderNumber() {
		return orderNumber;
	}

	/*
	private void setOrderNumber(long orderNumber) {
		OrderNumber = orderNumber;
	}*/
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus OrderStatus) {
		OrderMessage( "changed order status to " + OrderStatus );
		orderStatus = OrderStatus;
	}

	public Cashier getCashier() {
		return cashier;
	}

	private void setCashier(Cashier Cashier) {
		cashier = Cashier;
	}
	
	public void setCook(Cook Cook)
	{
		cook = Cook; 
	}
	
	public void OrderMessage(String action)
	{
		System.out.println("Order:" + getOrderNumber() + " has " + action + ".");
	}
	
	public void setCookedAtNow()
	{
		cookedAt  = new Date(); 
	}
	
	public Date getCookedAt()
	{
		return cookedAt; 
	}
	
	
}
	
