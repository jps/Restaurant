package protocol;

public class CookProtocol {

	public static Object processInput(Object fromServer) {
		// TODO Auto-generated method stub
		if(fromServer instanceof String)
		{
			String string = (String) fromServer; 
			if(string.equals("NoPendingOrders"))
			{
				System.out.println("no pending orders");
				return "CookOrderRequest"; 
			}
		}
		
		
		return null;
	}

}
