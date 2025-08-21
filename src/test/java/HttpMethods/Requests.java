package HttpMethods;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Requests {

	 int userid;
	 HashMap<String, String> data;
	 String URL = "https://jsonplaceholder.typicode.com";
	 
	// get request
	 
	@Test(priority=1, enabled=true)
	public void getRequest()
	{
		try {
		given()
		.when()
			.get(URL+"/posts")
		.then()
			.statusCode(200)
			.body(containsString("title"))
			.header("content-type", equalTo("application/json; charset=utf-8"))
			.time(lessThan(2000L))
		.log().all();
		}
		catch (Exception e) {
				Assert.fail("testcases failed: "+ e.getMessage());
		}
	}
	
	// create request 
	
	@Test(priority=2, enabled=true)
	public void createRequest() {
	    try {
	    	 data = new HashMap<>();
	    	    data.put("name", "AMK31");
	    	    data.put("job", "Automation Tester");

	    	    userid = given()
	    	        .baseUri(URL)
	    	        .contentType("application/json")
	    	        .body(data)
	    	    .when()
	    	        .post("/posts")
	    	    .then()
	    	        .statusCode(201)
	    	        .body("name", equalTo("AMK31"))
	    	        .body("job", equalTo("Automation Tester"))
	    	        .body("id", notNullValue())
	    	        .log().all()
	    	        .extract().jsonPath().getInt("id");
	    	        
	    }
	    catch (Exception e) {
	        Assert.fail("Test case failed: " + e.getMessage());
	    }
	}
	
	//update request
	
	@Test(priority=3, enabled=true, dependsOnMethods= {"createRequest"} )
	public void updateRequest()
	{
		try {
			data = new HashMap<>();
		data.put("name", "AMK41");
		data.put("job", "SDET");
		
		given()
		.baseUri(URL)
			.contentType("application/json")
			.body(data)
		.when()
			.put("/posts/1/")
		.then()
			.statusCode(200)
			.body("name", equalTo("AMK41"))
			.body("job", equalTo("SDET"))
		.log().all();
		}
		catch(Exception e) {
			Assert.fail("testcase failed: "+e.getMessage());
		}
	}

	//delete request
	
	@Test(priority=4, enabled=true)
	public void deleteRequest()
	{
	    try {
	       
	        given()
	        .when()
	            .delete(URL+"/posts/"+userid)
	        .then()
	            .statusCode(204)   // correct for JSONPlaceholder
	            .time(lessThan(2000L))
	            .body(emptyOrNullString())
	            .log().all()
	            .log().body();
	        
	    }
	    catch(Exception e) {
	        Assert.fail("testcase failed: "+e.getMessage());
	    }
	}
	
	@Test(priority=5, enabled=true)
	public void getRequest2()
	{
		try {
		given()
		.when()
			.get(URL+"/posts/1/"+userid)
		.then()
			.statusCode(200)
//			.body(containsString("title"))
			.header("content-type", equalTo("application/json; charset=utf-8"))
			.time(lessThan(2000L))
		.log().all();
		}
		catch (Exception e) {
				Assert.fail("testcases failed: "+ e.getMessage());
		}
	}

}
