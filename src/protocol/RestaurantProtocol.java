package protocol;

import java.util.UUID;

import models.Cashier;
import models.Cook;
import models.Order;

import controller.*;
import enumerations.OrderStatus;

public class RestaurantProtocol {

	public String processInput(String theInput) {

		String theOutput = null;

		return theOutput;
	}

	public Object processInput(Object obj) {
		System.out.println("Object Received :" + obj.toString());
		
		if (obj instanceof Cashier) {
			System.out.println("Cashier Received");
			Cashier cashier = (Cashier) obj;
			if (!cashier.getLoggedIn())
				if (CashierController.INSTANCE.CashierSignIn(cashier)) {
					// signed in correctly
					System.out.println("Cashier Signed in");
					return "SignedIn";
				} else {
					// sign in failed close the client

					return "Failed";
				}
			else if (CashierController.INSTANCE.CashierSignOut(cashier)) {
				return "SignedOut";

			} else {
				// this shouldn't occur
				return "Failed";
			}
			//
		} else if (obj instanceof Cook) {
			System.out.println("Cook Received");
			Cook cook = (Cook) obj;
			if (!cook.getLoggedIn()) {
				// sign in
				if (CookController.INSTANCE.CookSignIn(cook)) {
					System.out.println("Cook Signed in");
					return "SignedIn";
				} else {
					System.out.println("Cashier Sign in failed");
					return "FailedIn";
				}
			} else{
				if(CookController.INSTANCE.CookSignOut(cook)){
					// sign off
					System.out.println("Cook Signed out");
					return "SignedOut";
				}else{
					System.out.println("Cook Sign out failed");
					return "FailedOut";
				}
			}

		} else if (obj instanceof Order) {
			Order order = (Order) obj;
			OrderStatus status = order.getOrderStatus();

			if (status == enumerations.OrderStatus.pending) {
				OrderController.INSTANCE.AddOrderToQueue(order);
				System.out
						.println("order added to server waiting for cooking queue");
			} else if (status == enumerations.OrderStatus.preperation) {
				System.out
						.println("order status is preperations when is this occouring ???");
			} else if (status == enumerations.OrderStatus.cooked) {
				OrderController.INSTANCE.AddOrderToCooked(order);
				System.out.println("order added to server cooked queue");
			} else if (status == enumerations.OrderStatus.completed) {
				OrderController.INSTANCE.AddOrderToFinished(order);
				System.out
						.println("order has been added to the server finished list");
			}

		}else if(obj instanceof String)
		{
			String string = (String)obj; 
			System.out.println("server received string:"+string);
			
			if(string.equals("CookOrderRequest"))
			{
				Order order = OrderController.INSTANCE.GetNextItemOffQueue();
				if(order != null)
				{
					System.out.println("returning order to cook");
					return order;
				}
				else
				{
					System.out.println("no pending orders");
					return "NoPendingOrders";
				}
			}
		}else if(obj instanceof UUID)
		{
			System.out.println("Server has received UUID");
			UUID uuid = (UUID)obj;
			
			return OrderController.INSTANCE.GetCookedOrder(uuid);
			
		}

		return null;
	}
}
