package testcases;

import utils.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.SkipException;
import org.testng.annotations.*;
import endpoints.EndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import payload.LoginPayload;
import payload.TransferPayload;
import utils.ConfigReader;

public class TransferTest extends BaseTest{
	String price = ConfigReader.getPropertyAsString("amt");
	String fromAct = ConfigReader.getPropertyAsString("fromacc");
	String toAct = ConfigReader.getPropertyAsString("toacc");
	
	@Test
	public void TC_TRF_01_validTransfer() {
		
		String text = price+".0 was successfully transferred from Account "+fromAct+" into Account "+toAct+" at";
		given()
			.spec(reqSpec)
			.header("Authorization", token)
			.body(TransferPayload.validFundTransferToValidAccounts())
		.when()
			.post(EndPoints.POST_TRANSFER_MONEY)
		.then()
			.statusCode(200)
			.body("success",containsString(text));
	}

	@Test
	public void TC_TRF_02_TransferFromInvalidAcc() {
		given()
			.spec(reqSpec)
			.header("Authorization", token)
			.body(TransferPayload.validFundTransferFromInvalidAcc())
		.when()
			.post(EndPoints.POST_TRANSFER_MONEY)
		.then()
			.statusCode(500);
	}
	
	@Test
	public void TC_TRF_03_TransferToInvalidAcc() {
		
		given()
			.spec(reqSpec)
			.header("Authorization", token)
			.body(TransferPayload.validFundTransferToInvalidAcc())
		.when()
			.post(EndPoints.POST_TRANSFER_MONEY)
		.then()
			.statusCode(500);
	}
	
	@Test
	public void TC_TRF_04_zeroFundTranfer() {
		
		String text = "0.0 was successfully transferred from Account "+fromAct+" into Account "+toAct+" at";
		given()
			.spec(reqSpec)
			.header("Authorization", token)
			.body(TransferPayload.zeroFundTransfer())
		.when()
			.post(EndPoints.POST_TRANSFER_MONEY)
		.then()
			.statusCode(200)
			.body("success", containsString(text));
	}
	
	@Test
	public void TC_TRF_05_InvalidFundsTranfer() {
		
		given()
			.spec(reqSpec)
			.header("Authorization", token)
			.body(TransferPayload.invalidFundTransfer())
		.when()
			.post(EndPoints.POST_TRANSFER_MONEY)
		.then()
			.statusCode(500);
		throw new SkipException("Skipping - API bug, returns 200 with negative price");
	}
	
	@Test
	public void TC_TRF_06_validTransfer() {
		given()
			.spec(reqSpec)
			.body(TransferPayload.validFundTransferToValidAccounts())
		.when()
			.post(EndPoints.POST_TRANSFER_MONEY)
		.then()
			.statusCode(401);
	}
}
