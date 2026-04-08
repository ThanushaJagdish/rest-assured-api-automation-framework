package payload;

import pojo.AdminChangePasswordPojo;
import utils.ConfigReader;

public class ChangePasswordPayload {
	public static AdminChangePasswordPojo existingUserPasswordChange() {
		return new AdminChangePasswordPojo(
				ConfigReader.getPropertyAsString("username"),
				"Abc@123",
				"Abc@123");
	}
	
	public static AdminChangePasswordPojo unregisteredUserPasswordChange() {
		return new AdminChangePasswordPojo(
				"xyzabc",
				"Abc@123",
				"Abc@123");
	}
	
	public static AdminChangePasswordPojo invalidPasswordChange() {
		return new AdminChangePasswordPojo(
				ConfigReader.getPropertyAsString("username"),
				"Abc@123",
				"xyz@123");
	}
	
}
