import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
	
	// Generates a random Session ID between 0 and 10,000
	private void setID() {
		id = ThreadLocalRandom.current().nextLong(10000);
	}
	
	// Compares current time to the set expiry time, if the current time is after the session time, throw an exception
	public void sessionMonitor() throws InvalidSession {
		try {
			currTime = LocalTime.now();
			if (currTime.isAfter(expireTime)) {
				expired = true;
				throw new InvalidSession();
			}
		}
		catch (InvalidSession IS){
			System.out.println("Session for user " +IS.getUsername()+ "expired at " +expireTime.toString());
		}
	}
}
