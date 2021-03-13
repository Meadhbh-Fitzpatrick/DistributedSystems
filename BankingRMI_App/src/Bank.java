import java.beans.Statement;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.server.UnicastRemoteObject; 

public class Bank extends UnicastRemoteObject implements IBank {
	private static List<Account> accounts = new ArrayList<Account>(); // users accounts
	private static List<Transaction> transactions = new ArrayList<Transaction>();
	private static Statement statement;
	Session session;
	
	
	public Bank() throws RemoteException
	{
		super();
	}
	
	
	public long login(String username, String password) throws RemoteException, InvalidLogin, InvalidSession {
		try {
			for (Account i:accounts)
			{
				if (i.username.equals(username))
				{
					if (i.password.equals(password))
					{
						Session sesh = new Session(username);
						session = sesh;
						System.out.println("Login Sucessful. Session " + sesh.id + " is valid for 5 minutes.");
						return sesh.id;
					}
					System.out.println("Login Failed. Invalid Password " + password);
					throw new InvalidLogin();
				}
				System.out.println("Login Failed. Invalid Username " + username);
				throw new InvalidLogin();
			}
			throw new InvalidLogin();
		}
		catch (InvalidLogin IL){
				System.out.println("Invalid Login for User: " +IL.getUsername());
			}
		return session.id;
	}

	public void deposit(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
		try {
			session.sessionMonitor();
			if (sessionID != session.id) {
				throw new InvalidSession();
				}
			for (Account i:accounts) {
				if (accountnum == i.accountNum)
				{
					transactions.add(new Transaction(amount, LocalDate.now(), "Deposit"));
					i.balance = i.balance.add(amount);
					System.out.println(i.balance.toString());
				}
			}
		}
		catch (InvalidSession IS) {
			System.out.println("Invalid Session for User: " +IS.getUsername());
		}
	}

	public void withdraw(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
		try {
			session.sessionMonitor();
			if (sessionID != session.id) {
				throw new InvalidSession();
				}
			for (Account i:accounts) {
				
				if (accountnum == i.accountNum)
				{
					transactions.add(new Transaction(amount, LocalDate.now(), "Withdraw"));
					i.balance = i.balance.subtract(amount);
				}
			}
		}
		catch (InvalidSession IS) {
			System.out.println("Invalid Session for User: " +IS.getUsername());
		}
	}

	public BigDecimal getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession {
		try {
			session.sessionMonitor();
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

	public Statement getStatement(LocalDate from, LocalDate to, long sessionID) throws RemoteException, InvalidSession {
		try {
			session.sessionMonitor();
			if (sessionID != session.id) {
				throw new InvalidSession();
				}
			for (Transaction t:transactions) {
				if (t.date.isAfter(from)){
					if (t.date.isBefore(to)) {
						System.out.println(t.toString());
					}
				}
			}
		}
			catch (InvalidSession IS) {
				System.out.println("Invalid Session for User: " +IS.getUsername());
			}
		return null;
	}
	
	public static void main(String args[]) throws Exception {
		// initialise Bank server - see sample code in the notes and online RMI tutorials for details
		//Not used to working with BigDecimal (Or Java these days) so this is a little awkward
		try { 
			BigDecimal dec = new BigDecimal(10.50);
			Account jmg = new Account("JackMcGirl", "1234", dec);
			dec = dec.add(dec);
			Account mf = new Account("MeadhbhFitzpatrick", "2020", dec);
			accounts.add(jmg);
			accounts.add(mf);
			System.out.println("Account created. Username :" + jmg.username + " Account No. : " + jmg.accountNum);
			System.out.println("Account created. Username :" + mf.username + " Account No. : " + mf.accountNum);
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