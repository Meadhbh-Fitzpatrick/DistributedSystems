import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BankClient {

	public static void main(String args[])
	{
		try {
			IBank server = (IBank) Naming.lookup("//localhost:2001/Bank");
			String name;
			String password;
			BigDecimal balance;
			long sessionId;
			System.out.println("Connected to Server");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
