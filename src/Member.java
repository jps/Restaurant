import java.util.UUID;


public class Member extends Thread{

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
