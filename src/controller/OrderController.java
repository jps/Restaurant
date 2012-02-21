package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import models.Order;

public enum OrderController {
	INSTANCE;

	private Long CurrentOrderNumber = (long) 0;
	private Queue<Order> OrderQueue = new ConcurrentLinkedQueue<Order>();

	// private List<Order> Cookedat = Collections.synchronizedList(new
	// ArrayList<Order>());
	private List<Order> Cooked = Collections
			.synchronizedList(new ArrayList<Order>());
	private List<Order> FinishedOrders = Collections
			.synchronizedList(new ArrayList<Order>());

	public UUID getNextOrderNumber() {
		// IncrementOrderNumber();
		return UUID.randomUUID();
	}

	public void IncrementOrderNumber() {
		CurrentOrderNumber++;
	}

	public void AddOrderToQueue(Order order) {
		OrderQueue.add(order);
		// TODO: this could be handled with offer this will mean that no oders
		// are lost ie if the list is full, however is this a likely condition.
	}

	public Order GetNextItemOffQueue() {
		return OrderQueue.poll(); // returns null if empty
	}

	public void AddOrderToCooked(Order order) {
		Cooked.add(order);
	}

	public Order GetCookedOrder(UUID Identity) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -60);

		for (Order i : Cooked) {
			if (i.getCashier().getIdentity().equals(Identity)) {
				Cooked.remove(i);
				return i;

			} else if (now.getTime().compareTo(i.getCookedAt()) > 0)// if after
																	// 60
																	// seconds
																	// give the
																	// order out
																	// to
																	// another
																	// cashier
			{
				Cooked.remove(i);
				return i;
			}
		}

		return null;
	}

	public void AddOrderToFinished(Order order) {
		FinishedOrders.add(order);
	}

	public String[] GetPendingStringList() {
		List<Order> orderList = new ArrayList<Order>(OrderQueue);
		String[] ostrings = new String[orderList.size()];
		for (int i = 0; i < orderList.size(); i++) {
			ostrings[i] = String.valueOf(orderList.get(i).getOrderNumber());
		}
		return ostrings;
	}

	public String[] GetCookedStringList() {
		List<Order> cooked = new ArrayList<Order>(Cooked);
		String[] cstrings = new String[cooked.size()];
		for (int i = 0; i < cooked.size(); i++) {
			cstrings[i] = String.valueOf(cooked.get(i).getOrderNumber());
		}
		return cstrings;
	}

	public String[] GetFinishedStringList() {
		List<Order> finished = new ArrayList<Order>(FinishedOrders);
		String[] fstrings = new String[FinishedOrders.size()];
		for (int i = 0; i < finished.size(); i++) {
			fstrings[i] = String.valueOf(finished.get(i).getOrderNumber());
		}
		return fstrings;
	}

}