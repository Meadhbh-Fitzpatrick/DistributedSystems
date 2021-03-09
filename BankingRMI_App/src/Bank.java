import java.beans.Statement;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class Bank implements IBank {
	private List<Account> accounts; // users accounts
	
	public Bank() throws RemoteException
	{
	}
	
	public void deposit(int account, BigDecimal amount) throws RemoteException, InvalidSession {
	// implementation code
	}
	
	public void withdraw(int account, BigDecimal amount) throws RemoteException, InvalidSession {
	// implementation code
	}
	
	public BigDecimal getBalance(int account) throws RemoteException, InvalidSession {
	// implementation code
	}
	
	public Statement getStatement(Date from, Date to) throws RemoteException, InvalidSession {
	// implementation code
	}
	
	public static void main(String args[]) throws Exception {
	// initialise Bank server - see sample code in the notes and online RMI tutorials for details
	}
}