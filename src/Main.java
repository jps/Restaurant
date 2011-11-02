
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boolean RestaurantOpen = true;
		
			Cashier ca1 = new Cashier("Tom","Taylor");
			Cashier ca2 = new Cashier("Ben","Thomas");
			Cashier ca3 = new Cashier("Nick","Cage");
			
			CashierController.INSTANCE.CashierSignIn(ca1);
			CashierController.INSTANCE.CashierSignIn(ca2);
			CashierController.INSTANCE.CashierSignIn(ca3);
			
			ca1.start();
			ca2.start();
			ca3.start();
			
			Cook co1 = new Cook("James", "Spencer");
			Cook co2 = new Cook("Alice", "Stringer");
			Cook co3 = new Cook("Amber", "Ryan");
			Cook co4 = new Cook("Joel", "Herber");		
					
			CookController.INSTANCE.CookSignIn(co1);
			CookController.INSTANCE.CookSignIn(co2);
			CookController.INSTANCE.CookSignIn(co3);
			CookController.INSTANCE.CookSignIn(co4);
			
			co1.start();
			co2.start();
			co3.start(); 
			co4.start();
			
			while(RestaurantOpen)
			{
			
			}
	}

}
