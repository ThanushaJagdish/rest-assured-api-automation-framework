package testcases;

import org.testng.annotations.Test;
import utils.BaseTest;
import utils.ConfigReader;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.SkipException;
import static org.hamcrest.Matchers.*;
import endpoints.EndPoints;
import io.restassured.response.Response;
import payload.TransactionPayload;

public class AccountTest extends BaseTest{
	
	//@Test
	public void TC_ACC_01() {
		//get all accounts
		given()
			.spec(reqSpec)
			.headers("Authorization", token)
		.when()
			.get(EndPoints.GET_ALL_ACCOUNTS)
		.then()
			.statusCode(200)
			.log()
			.body()
			.body("Accounts", notNullValue())
		    .body("Accounts.size()", equalTo(3))
		    .body("Accounts.Name", hasItems("Savings", "Checking", "Credit Card"));
	}
	
	//@Test
	public void TC_ACC_02() {
		//get all accounts without auth
		
		given()
			.spec(reqSpec)
		.when()
			.get(EndPoints.GET_ALL_ACCOUNTS)
		.then()
			.statusCode(401)
			.body(containsString("Please log in first"));
	}
	
	//@Test
	public void TC_ACC_03() {
		//get valid account by ID
		
		given()
			.spec(reqSpec)
			.pathParam("id", ConfigReader.getPropertyAsString("account"))
			.headers("Authorization",token)
		.when()
			.get(EndPoints.GET_ACCOUNT_BY_ID)
		.then()
			.statusCode(200)
			.body("accountId", equalTo("4539082039396288"))
		    .body("balance", notNullValue())
		    .body("credits", notNullValue())
		    .body("debits", notNullValue());
	}
	
	//@Test
	public void TC_ACC_04() {
		//get invalid account by ID
		
		given()
			.spec(reqSpec)
			.pathParam("id", "000")
			.headers("Authorization",token)
		.when()
			.get(EndPoints.GET_ACCOUNT_BY_ID)
		.then()
			.statusCode(500);
	}
	
	//@Test
	public void TC_ACC_05() {
		//get valid account without auth
		
		given()
			.spec(reqSpec)
			.pathParam("id", ConfigReader.getPropertyAsString("account"))
		.when()
			.get(EndPoints.GET_ACCOUNT_BY_ID)
		.then()
			.statusCode(401);
	}
	
	//@Test
	public void TC_ACC_06() {
		//get last 10 transactions done in the given account 
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.pathParam("id", ConfigReader.getPropertyAsString("account"))
		.when()
			.get(EndPoints.GET_ACCOUNT_TRANSACTIONS)
		.then()
			.statusCode(200)
			.body("last_10_transactions",notNullValue())
			.body("last_10_transactions.size()", greaterThan(0));
	}
	
	//@Test
	public void TC_ACC_07() {
		//get transactions between given date
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.pathParam("id", ConfigReader.getPropertyAsString("fromacc"))
			.body(TransactionPayload.dates())
		.when()
			.post(EndPoints.GET_ACCOUNT_TRANSACTIONS)
		.then()
			.statusCode(200)
			.body("transactions",notNullValue())
			.body("transactions.size()", greaterThan(0));
	}
	
	@Test
	public void TC_ACC_08() {
		//get transactions between future date
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.pathParam("id", ConfigReader.getPropertyAsString("fromacc"))
			.body(TransactionPayload.futureDates())
		.when()
			.post(EndPoints.GET_ACCOUNT_TRANSACTIONS)
		.then()
			.statusCode(200)
			.body("transactions.size()", equalTo(0));
	}
	
	@Test
	public void TC_ACC_09() {
		//get transactions between future date
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.pathParam("id", ConfigReader.getPropertyAsString("fromacc"))
			.body(TransactionPayload.invalidDateRange())
		.when()
			.post(EndPoints.GET_ACCOUNT_TRANSACTIONS)
		.then()
			.statusCode(200)
			.body("transactions.size()", equalTo(0));
	}
	
}
