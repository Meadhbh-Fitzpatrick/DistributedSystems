import java.beans.Statement;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Bank implements IBank {
	private List<Account> accounts; // users accounts
	Session session;
	public Bank() throws RemoteException
	{
	}
	
	
	public long login(String username, String password) throws RemoteException, InvalidLogin, InvalidSession {
		try {
			for (Account i:accounts)
			{
				if (i.username == username)
				{
					if (i.password == password)
					{
						Session sesh = new Session(username);
						session = sesh;
						return sesh.id;
					}
					throw new InvalidLogin();
				}
				throw new InvalidLogin();
			}
			throw new InvalidLogin();
		}
		catch (InvalidLogin IL){
				System.out.println("Invalid Login for User: " +IL.getUsername());
			}
		return (Long) null;
	}

	public void deposit(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
		try {
			if (sessionID != session.id) {
				throw new InvalidSession();
				}
			for (Account i:accounts) {
				if (accountnum == i.accountNum)
				{
					i.balance.add(amount);
				}
			}
		}
		catch (InvalidSession IS) {
			System.out.println("Invalid Session for User: " +IS.getUsername());
		}
	}

	public void withdraw(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
		try {
			if (sessionID != session.id) {
				throw new InvalidSession();
				}
			for (Account i:accounts) {
				if (accountnum == i.accountNum)
				{
					i.balance.subtract(amount);
				}
			}
		}
		catch (InvalidSession IS) {
			System.out.println("Invalid Session for User: " +IS.getUsername());
		}
	}

	public BigDecimal getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		try {
			if (sessionID != session.id) {
				throw new InvalidSession();
				}
			for (Account i:accounts) {
				if (accountnum == i.accountNum)
				{
					return i.balance;
				}
			}
		}
		catch (InvalidSession IS) {
			System.out.println("Invalid Session for User: " +IS.getUsername());
		}
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