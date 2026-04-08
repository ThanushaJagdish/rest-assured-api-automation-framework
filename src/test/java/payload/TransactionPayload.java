package payload;

import pojo.TransactionPojo;
import utils.ConfigReader;

public class TransactionPayload {
	
	public static TransactionPojo dates() {
		return new TransactionPojo(
					ConfigReader.getPropertyAsString("startdt"),
					ConfigReader.getPropertyAsString("enddt")
				);
	}
	
	public static TransactionPojo futureDates() {
		return new TransactionPojo(
				ConfigReader.getPropertyAsString("fstartdt"),
				ConfigReader.getPropertyAsString("fenddt")
			);
	}
	
	public static TransactionPojo invalidDateRange() {
		return new TransactionPojo(
				ConfigReader.getPropertyAsString("enddt"),
				ConfigReader.getPropertyAsString("startdt")
			);
	}

}
