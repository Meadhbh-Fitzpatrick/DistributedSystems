import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 8803216572303579376L;
	public BigDecimal amount;
	public LocalDate date;
	public String description;
	
	// Needs some accessor methods to return information about the transaction
	
	public Transaction(BigDecimal amount, LocalDate date, String description) {
		amount = this.amount;
		date = this.date;
		description = this.description;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public LocalDate getDate() {
	return date;
	}
	
	public void PrintTransaciton(Transaction t) {
		System.out.println("Transaction Details : Amount - " + t.amount.toString() + "Date - " + t.date.toString() + t.description);
	}
} 