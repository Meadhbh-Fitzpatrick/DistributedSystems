import java.util.List;
import java.time.LocalDate;

public class Statement implements IStatement  {

	private static final long serialVersionUID = -3393428484808591906L;
	private int accountNum;
	private LocalDate startDate, endDate;
	private String accountName;
    private List<Transaction> t;

	public int getAccountnum() {
		return accountNum;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getAccoutName() {
		return accountName;
	}

	public List<Transaction> getTransactions() {
		return t;
	}

}
