package HttpMethods;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.tools.sjavac.Log;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Requests {

	 int userid;
	 HashMap<String, String> data;
	 String URL = "http://localhost:3000/employers";
	 
	// get request
	 
	@Test(priority=1, enabled=true)
	public void getRequest()
	{
		try {
		given()
		.when()
			.get(URL)
		.then()
			.statusCode(200)
			.body(containsString("Designation"))
			.header("content-type", equalTo("application/json; charset=utf-8"))
			.time(lessThan(2000L))
		.log().all();
		System.out.println("get request is successfull");
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
	    	    data.put("name", "AMK31sd");
	    	    data.put("job", "Automation Tester");

	    	    userid = given()
	    	        .contentType("application/json")
	    	        .body(data)
	    	    .when()
	    	        .post(URL)
	    	    .then()
	    	        .statusCode(201)
	    	        .body("name", equalTo("AMK31sd"))
	    	        .body("job", equalTo("Automation Tester"))
	    	        .body("id", notNullValue())
	    	        .log().all()
	    	        .extract().jsonPath().getInt("id");
	    	        System.out.println("create request is successfull");
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
		data.put("name", "AMK4156");
		data.put("job", "SDET");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put(URL+"/"+userid)
		.then()
			.statusCode(200)
			.body("name", equalTo("AMK4156"))
			.body("job", equalTo("SDET"))
		.log().all();
		System.out.println("update request is successfull");
		}
		catch(Exception e) {
			Assert.fail("testcase failed: "+e.getMessage());
		}
	}

	//delete request
	
	@Test(priority=4, enabled=true, dependsOnMethods="updateRequest")
	public void deleteRequest()
	{
	    try {
	       
	        given()
	        .when()
	            .delete(URL+"/"+37)
	        .then()
	            .statusCode(204)   // correct for JSONPlaceholder
	            .time(lessThan(2000L))
	            .body(emptyOrNullString())
	            .log().all()
	            .log().body();
	        System.out.println("delete request is successfull");
	    }
	    catch(Exception e) {
	        Assert.fail("testcase failed: "+e.getMessage());
	    }
	}

}
