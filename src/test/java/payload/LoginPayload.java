package payload;

import pojo.LoginPojo;
import utils.ConfigReader;

public class LoginPayload {
	
	//valid login
	public static LoginPojo validLogin() {
		return new LoginPojo(
				ConfigReader.getPropertyAsString("username"), 
			    ConfigReader.getPropertyAsString("password"));
	}
	
	// Invalid login payload
    public static LoginPojo invalidPassword() {
        return new LoginPojo(ConfigReader.getPropertyAsString("username"), "wrongpass");
    }
    
    public static LoginPojo invalidUsername() {
        return new LoginPojo("xyz", ConfigReader.getPropertyAsString("password"));
    }
    
    public static LoginPojo blankUsername() {
        return new LoginPojo("", ConfigReader.getPropertyAsString("password"));
    }
    
    public static LoginPojo blankPassword() {
        return new LoginPojo(ConfigReader.getPropertyAsString("username"), "");
    }
    
 // Empty credentials payload
    public static LoginPojo emptyCredentials() {
        return new LoginPojo("", "");
    }
	
}
