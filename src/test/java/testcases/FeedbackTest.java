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
import payload.FeedbackPayload;
import payload.TransactionPayload;

public class FeedbackTest extends BaseTest{
	@Test
	public void TC_FDB_01_ValidFeedback() {
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.body(FeedbackPayload.validFeedback())
		.when()
			.post(EndPoints.POST_SUBMIT_FEEDBACK)
		.then()
			.statusCode(200)
			.body("comments", notNullValue())
			.body("subject", notNullValue())
			.body("name", notNullValue())
			.body("email", notNullValue());
	}
	
	@Test
	public void TC_FDB_02_invalidFeedback() {
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.body(FeedbackPayload.emptyFeedback())
		.when()
			.post(EndPoints.POST_SUBMIT_FEEDBACK)
		.then()
			.statusCode(400);
		
		throw new SkipException("Not working as expected");
	}
	
	@Test
	public void TC_FDB_03_FeedbackWithoutAuth() {
		given()
			.spec(reqSpec)
			.body(FeedbackPayload.validFeedback())
		.when()
			.post(EndPoints.POST_SUBMIT_FEEDBACK)
		.then()
			.statusCode(401);
		
		throw new SkipException("Working without Auth");
	}

}
