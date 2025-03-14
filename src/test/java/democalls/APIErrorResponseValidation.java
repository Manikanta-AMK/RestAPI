package democalls;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APIErrorResponseValidation {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://api.example.com";

// Sending a request that triggers an error response
		Response response = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"wrongUser\", \"password\": \"wrongPass\" }").post("/login");

// Validate the status code and error message in the response
		response.then().statusCode(401).body("error", equalTo("Unauthorized")).body("message",
				equalTo("Invalid username or password."));
	}
}