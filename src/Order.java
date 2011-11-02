
public class Order {

	private long OrderNumber;
	private OrderStatus OrderStatus;
	private Cashier Cashier;
	
	
	Order(Cashier cashier){
		OrderNumber = OrderController.INSTANCE.getNextOrderNumber();
		setOrderStatus(OrderStatus.pending);
		setCashier(cashier);
	}
	
	Order(long orderNumber){
		setOrderStatus(OrderStatus.pending);
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
	
}
