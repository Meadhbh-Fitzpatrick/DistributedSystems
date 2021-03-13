import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDate;
import java.util.List;

public interface IStatement extends Serializable, Remote {
	
public int getAccountnum();  // returns account number associated with this statement
public LocalDate getStartDate(); // returns start Date of Statement
public LocalDate getEndDate(); // returns end Date of Statement
public String getAccoutName(); // returns name of account holder
public List<Transaction> getTransactions(); // return list of transactions included in this statement  

} 
