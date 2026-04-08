package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.Test;
import pojo.*;
import endpoints.EndPoints;
import payload.FeedbackPayload;
import payload.LoginPayload;
import payload.TransactionPayload;
import payload.TransferPayload;
import utils.BaseTest;
import utils.ConfigReader;
import utils.DataProviders;

public class UserJourney1 extends BaseTest{
	
	String authToken;
	
	@Test(dataProvider="getExcelData", dataProviderClass=utils.DataProviders.class)
	public void testUserJourney(String username, String password,String account,
								String startDate,String endDate,String fromAccount,
								String toAccount,String transferAmount,String name,
								String email,String subject,String message) {
		login(username,password);
		getTransactions(account,startDate,endDate);
		moneyTransfer(transferAmount,fromAccount,toAccount);
		submitFeedback(name,email,subject,message);
		logout();
	}
	
	//Login
	public void login(String username, String password) {
		authToken =
				given()
					.spec(reqSpec)
					.body(new LoginPojo(username,password))
				.when()
					.post(EndPoints.LOGIN)
				.then()
					.extract()
					.response().jsonPath().getString("Authorization");
	
	}
	
	//check specific account transactions for given dates
	public void getTransactions(String accId, String fromDate, String toDate) {
			given()
			.spec(reqSpec)
			.header("Authorization",authToken)
			.pathParam("id", accId)
			.body(new TransactionPojo(fromDate, toDate))
		.when()
			.post(EndPoints.GET_ACCOUNT_TRANSACTIONS)
		.then()
			.statusCode(200);
	}
	
	
	//transfer money 
	public void moneyTransfer(String price, String fromAcc, String toAcc) {
		String text = price+".0 was successfully transferred from Account "+fromAcc+" into Account "+toAcc+" at";
		given()
			.spec(reqSpec)
			.header("Authorization", authToken)
			.body(new TransferPojo(toAcc,fromAcc,price))
		.when()
			.post(EndPoints.POST_TRANSFER_MONEY)
		.then()
			.statusCode(200)
			.body("success",containsString(text));
	}
	
	//submit feedback
	public void submitFeedback(String name, String email, String subject, String msg) {
		given()
			.spec(reqSpec)
			.header("Authorization",authToken)
			.body(new FeedbackPojo(name,email,subject,msg))
		.when()
			.post(EndPoints.POST_SUBMIT_FEEDBACK)
		.then()
			.statusCode(200)
			.body("comments", notNullValue())
			.body("subject", notNullValue())
			.body("name", notNullValue())
			.body("email", notNullValue());
	}
	
	//logout
	public void logout() {
			given()
				.spec(reqSpec)
				.header("Authorization",authToken)
			.when()
				.get(EndPoints.LOGOUT)
			.then()
				.log().body()
				.statusCode(200)
				.body("LoggedOut", equalTo("True"));
	}
}
