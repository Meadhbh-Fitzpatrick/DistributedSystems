import java.beans.Statement;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.server.UnicastRemoteObject; 

public class Bank extends UnicastRemoteObject implements IBank {
	private static List<Account> accounts = new ArrayList<Account>(); // users accounts
	Session session;
	public Bank() throws RemoteException
	{
		super();
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
		try {
			if (sessionID != session.id) {
				throw new InvalidSession();
				}
		}
			// Statement Code to Go Here
			catch (InvalidSession IS) {
				System.out.println("Invalid Session for User: " +IS.getUsername());
			}
		return null;
	}
	
	public static void main(String args[]) throws Exception {
		// initialise Bank server - see sample code in the notes and online RMI tutorials for details
		//Not used to working with BigDecimal (Or Java these days) so this is a little awkward
		BigDecimal dec = new BigDecimal(10.50);
		Account jmg = new Account("Jack McGirl", "1234", dec);
		dec = dec.add(dec);
		Account mf = new Account("Meadhbh Fitzpatrick", "2020", dec);
		accounts.add(jmg);
		accounts.add(mf);
		try { 
			 //System.setSecurityManager(new SecurityManager());
			 //LocateRegistry.createRegistry(2020);
	         IBank bank = new Bank(); 
	         Registry registry = LocateRegistry.createRegistry(2001);
	         registry.rebind("Bank", bank);
	         System.err.println("Server ready"); 
	      } catch (Exception e) { 
	         System.err.println("Server exception: " + e.toString()); 
	         e.printStackTrace(); 
	      } 
		}
}