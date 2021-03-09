import java.beans.Statement;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Bank implements IBank {
	private List<Account> accounts; // users accounts
	
	public Bank() throws RemoteException
	{
	}
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	public static String generateNewToken() {
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
	public long login(String username, String password) throws RemoteException, InvalidLogin {
		try {
			for (Account i:accounts)
			{
				if (i.username == username)
				{
					if (i.password == password)
					{
						return generateNewToken();
					}
					//throw new InvalidLogin();
				}
				throw new InvalidLogin();
			}
		}
		catch (InvalidLogin IL){
				System.out.println("Invalid Login for User: " +IL.getUsername());
			}
	}

	public void deposit(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		
	}

	public void withdraw(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		
	}

	public BigDecimal getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		return null;
	}

	public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String args[]) throws Exception {
		// initialise Bank server - see sample code in the notes and online RMI tutorials for details
		}
}