package democalls;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class restassured {

	@Test
	public void responseapi() {
		Response resp = RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(resp.getBody().asPrettyString());
		System.out.println(resp.getStatusLine());
		System.out.println(resp.getTime());
		System.out.println(resp.getHeader("Content-type"));
		int actualstatuscode = resp.getStatusCode();		
		int expectedstatuscode = 200;
		
		Assert.assertEquals(actualstatuscode, expectedstatuscode, "status code is matched");
		
	}
	
	@Test
	public void test2()
	{
		given().get("https://reqres.in/api/users?page=2")
		 .then()
		 .statusCode(200)
		 .log().all();
		 
		
	}

	
}
