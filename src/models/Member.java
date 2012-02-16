package models;
import java.io.Serializable;
import java.util.UUID;


public class Member extends Thread implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UUID Identity;
	
	public Member()
	{
		setIdentity(UUID.randomUUID());
	}

	public UUID getIdentity() {
		return Identity;
	}

	public void setIdentity(UUID identity) {
		Identity = identity;
	}
	
}
