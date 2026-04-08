package pojo;

public class TransactionPojo {
	private String startDate;
	private String endDate;
	
	public TransactionPojo(String sdate, String edate) {
		this.startDate = sdate;
		this.endDate = edate;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
