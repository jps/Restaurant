import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public enum OrderController {
	INSTANCE;
	
	private Long CurrentOrderNumber = (long) 0;
	private Queue<Order> OrderQueue = new ConcurrentLinkedQueue<Order>(); 
		
	
	public Long getNextOrderNumber() {
		IncrementOrderNumber();
		return CurrentOrderNumber;
	}

	public void IncrementOrderNumber()
	{
	    CurrentOrderNumber++;
		++CurrentOrderNumber;
	}
	
	public void AddOrderToQueue(Order order)
	{
		OrderQueue.add(order);
		//TODO: this could be handled with offer this will mean that no oders are lost ie if the list is full, however is this a likely condition.
	}
	
	public Order GetNextItemOffQueue()
	{
		return OrderQueue.poll(); //returns null if empty
	}
}