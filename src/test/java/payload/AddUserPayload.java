package payload;

import pojo.AdminAddUserPojo;
import utils.ConfigReader;

public class AddUserPayload {
	
	public static AdminAddUserPojo validRegistration() {
		return new AdminAddUserPojo(
				"tinku",
				"minku",
				"tinkuminku",
				"Abc@123",
				"Abc@123"
				);
	}
	
	public static AdminAddUserPojo registerWithExistingUsername() {
		return new AdminAddUserPojo(
				"john",
				"smith",
				ConfigReader.getPropertyAsString("username"),
				"123",
				"123"
				);
	}
	
	public static AdminAddUserPojo registrationWithPasswordMismatch() {
		return new AdminAddUserPojo(
				"thanu",
				"manu",
				"thanumanu",
				"123",
				"456"
				);
	}	

}
