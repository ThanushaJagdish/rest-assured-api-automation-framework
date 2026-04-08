package pojo;

public class TransferPojo {
	private String toAccount;
	private String fromAccount;
	private String transferAmount;
	
	public TransferPojo(String to, String from, String amt) {
		this.transferAmount = amt;
		this.toAccount = to;
		this.fromAccount = from;
	}
	
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}
	
	

}
