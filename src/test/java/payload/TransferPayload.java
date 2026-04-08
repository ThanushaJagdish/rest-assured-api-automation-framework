package payload;

import pojo.TransferPojo;
import utils.ConfigReader;

public class TransferPayload {
	
	public static TransferPojo validFundTransferToValidAccounts() {
		return new TransferPojo(
					ConfigReader.getPropertyAsString("toacc"),
					ConfigReader.getPropertyAsString("fromacc"),
					ConfigReader.getPropertyAsString("amt"));
	}
	
	public static TransferPojo validFundTransferFromInvalidAcc() {
		return new TransferPojo(
					ConfigReader.getPropertyAsString("toacc"),
					"000",
					ConfigReader.getPropertyAsString("amt"));
	}
	
	public static TransferPojo validFundTransferToInvalidAcc() {
		return new TransferPojo(
					"000",
					ConfigReader.getPropertyAsString("fromacc"),
					ConfigReader.getPropertyAsString("amt"));
	}
	
	public static TransferPojo zeroFundTransfer() {
		return new TransferPojo(
					ConfigReader.getPropertyAsString("toacc"),
					ConfigReader.getPropertyAsString("fromacc"),
					"0");
	}
	
	public static TransferPojo invalidFundTransfer() {
		return new TransferPojo(
					ConfigReader.getPropertyAsString("toacc"),
					ConfigReader.getPropertyAsString("fromacc"),
					"-100");
	}

}
