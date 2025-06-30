import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class APIErrorResponseValidation {
	
	public static void main(String[] args) {
		

		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
// Sending a request that triggers an error response
		Response response = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"wrongUser\", \"password\": \"wrongPass\" }").post("/login");

// Validate the status code and error message in the response
		response.then().statusCode(400).body("error", equalTo("Unauthorized")).body("message",
				equalTo("Invalid username or password."));
		System.setProperty(DEFAULT_BODY_ROOT_PATH, "webdriver.chromedriver.exe");
	}
}