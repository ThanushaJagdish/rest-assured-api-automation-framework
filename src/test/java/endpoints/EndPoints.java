package endpoints;

public class EndPoints {
	
	//LOGIN MODULE END POINTS
	public static final String LOGIN = "/api/login";
	
	//ACCOUNT MODULE ENDPOINTS
	public static final String GET_ALL_ACCOUNTS = "/api/account";
	public static final String GET_ACCOUNT_BY_ID = "/api/account/{id}";
	public static final String GET_ACCOUNT_TRANSACTIONS = "/api/account/{id}/transactions";
	
	//TRANSFER MODULE ENDPOINT
	public static final String POST_TRANSFER_MONEY = "/api/transfer";
	
	//FEEDBACK MODULE ENDPOINT
	public static final String POST_SUBMIT_FEEDBACK = "/api/feedback/submit";
	
	
	//ADMIN MODULE ENDPOINT
	public static final String POST_ADD_USER = "/api/admin/addUser";
	public static final String POST_CHANGE_PASSWORD = "/api/admin/changePassword";
	
	//LOGOUT MODULE
	public static final String LOGOUT = "/api/logout";

	//USER MODULE
	public static final String GET_ALL_USERS = "/api/user";
	public static final String GET_USER_BY_NAME = "/api/user/{username}";
	public static final String REGISTER_USER = "/api/user/register";
	

}
