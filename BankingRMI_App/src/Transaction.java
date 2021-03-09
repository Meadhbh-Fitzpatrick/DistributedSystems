import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 8803216572303579376L;
	public BigDecimal amount;
	public Date date;
	
	// Needs some accessor methods to return information about the transaction
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Date getDate() {
	return date;
	}
	
	public String description;
} 