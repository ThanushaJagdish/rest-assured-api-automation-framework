package utils;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.*;
import endpoints.EndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import payload.LoginPayload;
import utils.ConfigReader;
import utils.RequestResponseLogger;

@Listeners(utils.ExtentReporter.class)
public class BaseTest {
	protected RequestSpecification reqSpec;
	protected String token;
	
	@BeforeSuite
	public void setup() {
	    RestAssured.filters(new RequestResponseLogger());
	}
	
	@BeforeClass
	public void setUp() {
		reqSpec = new RequestSpecBuilder()
				  .setContentType(ContentType.JSON)
				  .setBaseUri(ConfigReader.getPropertyAsString("base.url"))
				  .build();
		
		token =
			given()
				.spec(reqSpec)
				.body(LoginPayload.validLogin())
			.when()
				.post(EndPoints.LOGIN)
			.then()
				.extract()
				.response().jsonPath().getString("Authorization");
	}
}
