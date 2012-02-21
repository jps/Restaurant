package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Cook;

public enum CookController {
	INSTANCE;

	private Random generator = new Random();

	// private ArrayList<Cook> Cooks = new ArrayList<Cook>();
	private List<Cook> Cooks = Collections
			.synchronizedList(new ArrayList<Cook>());

	public int GetRandomWaitTime() {
		return generator.nextInt(7001);
	}

	public boolean CookSignIn(Cook cook) { // this could be templated as is the
											// same on cook and cashier
		for (Cook c : Cooks) {
			if (c.getId() == cook.getId()) {
				return false;
			}
		}
		cook.setLoggedIn();
		Cooks.add(cook);
		return true;
	}

	public boolean CookSignOut(Cook cook) {
		return Cooks.remove(cook);
	}

	// TODO: would this be better if it was using notify all, should a random
	// item be called on the method ?
	public void notifyCooksOfNewOrder() {
		for (Cook cook : Cooks) {
			cook.notifyAll();
			break;
		}
	}

}
