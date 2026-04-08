package testcases;

import utils.BaseTest;
import org.testng.annotations.Test;
import utils.ConfigReader;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.SkipException;
import static org.hamcrest.Matchers.*;
import endpoints.EndPoints;
import io.restassured.response.Response;
import payload.AddUserPayload;
import payload.ChangePasswordPayload;
import payload.FeedbackPayload;
import payload.TransactionPayload;

public class AdminTest extends BaseTest{
	@Test
	public void TCAdmin_001_validRegistration() {
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.body(AddUserPayload.validRegistration())
		.when()
			.post(EndPoints.POST_ADD_USER)
		.then()
		.statusCode(200)
		.body("success",containsString("Requested operation has completed successfully."));
	}
	
	@Test
	public void TCAdmin_002_RegistrationWithExistingUsername() {
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.body(AddUserPayload.registerWithExistingUsername())
		.when()
			.post(EndPoints.POST_ADD_USER)
		.then()
		.statusCode(500);
		
		throw new SkipException("failed:adding new user with existing username");
	}
	
	@Test
	public void TCAdmin_003_RegistrationPasswordMismatch() {
		given()
			.spec(reqSpec)
			.header("Authorization",token)
			.body(AddUserPayload.registrationWithPasswordMismatch())
		.when()
			.post(EndPoints.POST_ADD_USER)
		.then()
		.statusCode(400)
		.body(containsString("Entered passwords did not match."));	
	}
	
	@Test
	public void TCAdmin_004_ExistingUserPasswordChange() {
		given()
		.spec(reqSpec)
		.header("Authorization",token)
		.body(ChangePasswordPayload.existingUserPasswordChange())
	.when()
		.post(EndPoints.POST_CHANGE_PASSWORD)
	.then()
	.statusCode(200)
	.body("success",containsString("Requested operation has completed successfully."));
	}
	
}
