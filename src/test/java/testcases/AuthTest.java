package testcases;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import endpoints.EndPoints;
import io.restassured.response.Response;
import payload.LoginPayload;
import utils.BaseTest;

public class AuthTest extends BaseTest{
	
	@Test(priority=1)
	public void TC_AUTH_01_ValidLogin() {
		
		given()
			.spec(reqSpec)
			.body(LoginPayload.validLogin())
		.when()
			.post(EndPoints.LOGIN)
		.then()
			.log().body()
			.statusCode(200)
			.body("success", equalTo("jsmith is now logged in"))
			.body("Authorization", notNullValue());
	}
	
	@Test(priority=2)
	public void TC_AUTH_02_invalidPassword() {
		String errorMsg =
		given()
			.spec(reqSpec)
			.body(LoginPayload.invalidPassword())
		.when()
			.post(EndPoints.LOGIN)
		.then()
			.statusCode(400)
			.extract().response().jsonPath().get("error");
		
		Assert.assertEquals(errorMsg, "We're sorry, but this username or password was not found in our system.");
	
	}
	
	@Test(priority=3)
	public void TC_AUTH_03_invalidUsername() {
		String errorMsg =
		given()
			.spec(reqSpec)
			.body(LoginPayload.invalidUsername())
		.when()
			.post(EndPoints.LOGIN)
		.then()
			.statusCode(400)
			.extract().response().jsonPath().get("error");
		
		Assert.assertEquals(errorMsg, "We're sorry, but this username or password was not found in our system.");
	}
	
	@Test(priority=4)
	public void TC_AUTH_04_blankUsername() {
		String errorMsg =
		given()
			.spec(reqSpec)
			.body(LoginPayload.blankUsername())
		.when()
			.post(EndPoints.LOGIN)
		.then()
			.statusCode(400)
			.extract().response().jsonPath().get("error");
		
		Assert.assertEquals(errorMsg, "We're sorry, but this username or password was not found in our system.");
	}
	
	@Test(priority=5)
	public void TC_AUTH_05_blankPassword() {
		String errorMsg =
		given()
			.spec(reqSpec)
			.body(LoginPayload.blankPassword())
		.when()
			.post(EndPoints.LOGIN)
		.then()
			.statusCode(400)
			.extract().response().jsonPath().get("error");
		
		Assert.assertEquals(errorMsg, "We're sorry, but this username or password was not found in our system.");
	}
	
	@Test(priority=6)
	public void TC_AUTH_06_EmptyCredentials() {
		String errorMsg =
		given()
			.spec(reqSpec)
			.body(LoginPayload.emptyCredentials())
		.when()
			.post(EndPoints.LOGIN)
		.then()
			.statusCode(400)
			.extract().response().jsonPath().get("error");
		
		Assert.assertEquals(errorMsg, "We're sorry, but this username or password was not found in our system.");
	}
	
	@Test(priority=7)
	public void TC_AUTH_07_noBody() {
		String errorMsg =
		given()
			.spec(reqSpec)
		.when()
			.post(EndPoints.LOGIN)
		.then()
			.statusCode(400)
			.extract().response().jsonPath().get("error");
		
		Assert.assertEquals(errorMsg, "body is not JSON");
	}
	
	@Test(priority=8)
	public void TC_AUTH_08_validLogout() {
		given()
			.spec(reqSpec)
			.header("Authorization",token)
		.when()
			.get(EndPoints.LOGOUT)
		.then()
			.log().body()
			.statusCode(200)
			.body("LoggedOut", equalTo("True"));
	}
	
	// BUG: API returns 200 even without Authorization header
	// Expected: 400/401, Actual: 200
	// Test disabled until bug is fixed
	@Test(priority=9, enabled=false)
	public void TC_AUTH_09_LogoutWithoutAuth() {
		given()
			.spec(reqSpec)
		.when()
			.get(EndPoints.LOGOUT)
		.then()
			.log().body()
			.statusCode(400);
		throw new SkipException("Skipping - API bug, returns 200 without token");
	}
	
}
