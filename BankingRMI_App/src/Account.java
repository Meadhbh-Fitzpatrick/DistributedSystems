import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.concurrent.ThreadLocalRandom;

public class Account {

	public String username;
	public String password;
	public int accountNum; // Random Start point
	public BigDecimal balance;
	
	public Account(String Username, String password, BigDecimal openingBalance) throws RemoteException {
		username = Username;
		this.password = password;
		balance = openingBalance;
		accountNum = ThreadLocalRandom.current().nextInt(100);
	}
	
}
