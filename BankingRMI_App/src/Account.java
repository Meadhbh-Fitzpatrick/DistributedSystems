import java.math.BigDecimal;
import java.rmi.RemoteException;

public class Account {

	public String username;
	public String password;
	public int accountNum = 147258; // Random Start point
	public BigDecimal balance;
	
	public Account(String Username, String password, BigDecimal openingBalance) throws RemoteException {
		username = Username;
		this.password = password;
		balance = openingBalance;
		accountNum = accountNum++;
		accountNum++;
	}
}
