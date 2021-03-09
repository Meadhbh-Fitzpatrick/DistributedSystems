import java.rmi.RemoteException;

public interface IBank extends Remote {

		public long login(String username, String password) throws RemoteException, InvalidLogin;
		public void deposit(int accountnum, Money amount, long sessionID) throws RemoteExcept, InvalidSession;
		public void withdraw(int accountnum, Money amount, long sessionID) throws RemoteException, InvalidSession;
		public Money getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession;
		public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession;

} 
