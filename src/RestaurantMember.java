
public class RestaurantMember extends Member {

	private String FirstName; 
	private String SecondName;
	private boolean LoggedIn;
	
	RestaurantMember(String firstName, String secondName)
	{
		super();
	 	setFirstName(firstName);
	 	setSecondName(secondName); 
	}
	
	
	public void SetName (String firstName, String secondName)
	{
		setFirstName(firstName);
	 	setSecondName(secondName); 
	}
	
	public String getMemberName()
	{
		return getFirstName() + ' ' + getSecondName();
	}
	
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getSecondName() {
		return SecondName;
	}

	public void setSecondName(String secondName) {
		SecondName = secondName;
	}

	public boolean getLoggedIn() {
		return LoggedIn;
	}

	public void setLoggedIn() {
		LoggedIn = true;
	}
	
	public void setLoggedOut(){
		LoggedIn = false;
	}
	
}
