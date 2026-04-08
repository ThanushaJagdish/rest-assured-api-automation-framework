package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import endpoints.EndPoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import payload.TransactionPayload;
import utils.BaseTest;
import utils.ConfigReader;

public class SchemaTest extends BaseTest{
	
	@Test
	public void testGetAccountDeatilsSchema()
	{
		given()
			.spec(reqSpec)
			.headers("Authorization", token)
		.when()
			.get(EndPoints.GET_ALL_ACCOUNTS)
		.then()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AccountSchema.json"));
	}
	
	@Test
	public void testGetTransactionsSchema() {
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.pathParam("id", ConfigReader.getPropertyAsString("fromacc"))
			.body(TransactionPayload.dates())
		.when()
			.post(EndPoints.GET_ACCOUNT_TRANSACTIONS)
		.then()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("TransactionSchema.json"));
	}

}
