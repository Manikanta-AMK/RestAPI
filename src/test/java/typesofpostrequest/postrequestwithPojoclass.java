package typesofpostrequest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 different ways to create post request
 1.post request body creation using hashmap
 2.post request body creation using Org.JSON
 3.post request body creation using POJO(Plain old java object) class
 4.post request body creation using external json file data
 */

public class postrequestwithPojoclass {

	@Test
	public void hasmap()
	{
		//1.post request body creation using hashmap
		HashMap data = new HashMap(); 
		data.put("name", "abcd");
		data.put("nickname", "xyz");
		data.put("job", "nothing");
		data.put("surname", "nick");
		
		String skills[] = {"java", "selenium"}; 
		data.put("courses", skills);
		
		given()
		    .contentType("application/json")
		    .body(data)
		
		.when()
		    .post("http://localhost:3000/students")
		
		.then()
		    .statusCode(201)
		    .body("name",equalTo("abcd"))
		    .body("nickname", equalTo("xyz"))
		    .body("job", equalTo("nothing"))
		    .body("surname", equalTo("nick"))
		    .body("courses[0]", equalTo("java"))
		    .body("courses[1]", equalTo("selenium"))
		    .header("content-type", "application/json, charset-utf-8t")
		    .log().all();
		    
		    
		    
		
		
	}
	
	
}
