package typesofpostrequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.minidev.json.JSONObject;

public class employes {

	public static Response response;
	public static int id;

	

	@Test(priority = 1)
	public static void validation() {
		
		
		pojoclass pjc = new pojoclass();
		pjc.setName("MR.wick");
		pjc.setLocation("US");
		pjc.setTaget("vinson");
		pjc.setWeapon("M24");
		pjc.setVehical("mastang");
		

		response = given().contentType("application/json").body(pjc).when().post("http://localhost:3000/AssassinWorld");
		response.then()
				.statusCode(201).body("name", equalTo("MR.wick")).body("location", equalTo("US"))
				.body("taget", equalTo("vinson")).body("weapon", equalTo("M24"))
				.body("vehical", equalTo("mastang"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
		response.jsonPath().getInt("id");
	
		
	}

}
