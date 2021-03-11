import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.Random;

public class Session {
	public long id;
	public String username;
	public LocalTime expireTime;
	public LocalTime currTime;
	public Boolean expired;
	
	// Creates a session from a passed Username
	public Session(String username) throws InvalidSession, RemoteException {
		 setID();
		 username = this.username;
		 setTime();
		 expired = false;
		 sessionMonitor();
	}

	// Session Time to last 5 minutes
	private void setTime() {
		expireTime = LocalTime.now().plusMinutes(5);
		currTime = LocalTime.now();
	}
	
	// Generates a random Session ID
	private void setID() {
		id = new Random().nextLong();
	}
	
	// Compares current time to the set expiry time recursively, if the current time is after the session time, throw an exception
	private void sessionMonitor() throws InvalidSession {
		try {
			currTime = LocalTime.now();
			if (currTime.isAfter(expireTime)) {
				expired = true;
				throw new InvalidSession();
			}
			sessionMonitor();
		}
		catch (InvalidSession IS){
			System.out.println("Session for user " +IS.getUsername()+ "expired at " +expireTime.toString());
		}
	}
}
