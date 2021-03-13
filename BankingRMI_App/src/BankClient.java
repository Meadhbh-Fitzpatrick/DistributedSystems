import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class BankClient {

	public static void main(String args[]) throws InvalidLogin, InvalidSession
	{
		try {
			IBank server = (IBank) Naming.lookup("//localhost:2001/Bank");
			String name;
			String password;
			BigDecimal balance;
			long sessionId;
			System.out.println("Connected to Server");
			//System.out.println("Please Login in the form \"login username password\"");
			Operations(server);
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
	
	public static void Operations(IBank server) throws RemoteException, InvalidLogin, InvalidSession {
		Scanner in = new Scanner(System.in);
		String toSplit = in.nextLine();
		String[] instructions = toSplit.split(",");
		BigDecimal amount = new BigDecimal(instructions[2]);
		if (instructions[0].equals("login")) {
			System.out.println(instructions[1]);
			System.out.println(instructions[2]);
			server.login(instructions[1], instructions[2]);
			System.out.println("Login Sucessful");
			System.out.println("Please enter instructions in the form \"instruction amount sessionID \"");
			System.out.println("Valid Instructions : deposit, withdraw, getBalance");
			Operations(server);
		}
		else if (instructions[0].equals("deposit")) {
			server.deposit(Integer.parseInt(instructions[1]), amount, Long.parseLong(instructions[3]));
		}
		else if (instructions[0] == "withdraw ") {
			server.withdraw(Integer.parseInt(instructions[1]), amount, Long.parseLong(instructions[3]));
		}
		else if (instructions[0] == "balance ") {
			server.getBalance(Integer.parseInt(instructions[1]), Long.parseLong(instructions[2]));
		}
		else {
			System.out.println("Invalid Instruction: " + instructions[0]);
			Operations(server);
		}
	}
}
