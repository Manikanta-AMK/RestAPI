package democalls;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPrequest {	 
	
	public static Response response;
	public  static HashMap newdata;

	public static int id;

	@Test(priority=1, enabled=false) 
	  public static void getusers() {
		  given()
	       .when() .get("https://reqres.in/api/users?page=2")
	       .then() .statusCode(200) .body("per_page",equalTo(6)) .log().all();
	  
	  }
	
	@Test(priority = 2)
	public static void postuser() {
		newdata = new HashMap();
		newdata.put("role", "sdet");
		newdata.put("company", "IBM"); 

		response = given().contentType("application/json").body(newdata).when()
				.post("https://reqres.in/api/users");
//		response.jsonPath().getInt("id");

		response.then().statusCode(201).log().all();
		System.out.println("user created with id " + id);

		System.out.println("created role is " + newdata.get("role").equals("sdet"));
	}

	@Test(priority=3,enabled=false)
	public static void updateuser() {
		newdata.put("location","hyderabad");
		newdata.put("area", "mindspace");
		newdata.put("nickname", "chanti");
		response = given().contentType("application/json").body(newdata).when().post("https://reqres.in/api/users");
		response.jsonPath();
		
		System.out.println(response.getBody().asPrettyString());
		System.out.println("company name: "+newdata.get("company"));

	}
	
	@Test(dependsOnMethods="updateuser",enabled=false)
	public static void deleteuser()
	{
		newdata.remove("area");
		response = given().contentType("application/json").body(newdata).when().post("https://reqres.in/api/users");
				response.then().statusCode(201).log().all();
		System.out.println("status code of delete "+response.getStatusCode());
		response.jsonPath();
		
		System.out.println("area name is not there "+newdata.get("area"));
		
	}
	
	
	

	/*
	  @Test(priority=0) public static void getusers() { given()
	  
	  .when() .get("https://reqres.in/api/users?page=2")
	  
	  .then() .statusCode(200) .body("per_page",equalTo(6)) .log().all();
	  
	  }
	  
	  @Test(priority=2) void createuser() { HashMap data = new HashMap();
	  data.put("name", "AMK"); data.put("Role", "Automation Engineer");
	  data.put("Company", "Sutherland");
	  
	  id = given() .contentType("application/json") .body(data) .when()
	  .post("https://reqres.in/api/users") .jsonPath().getInt("id"); .then()
	  .statusCode(201) .log().all(); }
	  
	  @Test(priority=3,dependsOnMethods = {"createuser"}) void updateuser() {
	  HashMap data = new HashMap(); data.put("name", "Manikanta"); data.put("Role",
	  "SDET");
	  
	  given() .contentType("application/json") .body(data) .when()
	  .put("https://reqres.in/api/users/"+id)
	  
	  .then() .statusCode(200) .log().all();
	  
	  }
	  
	  @Test(priority=4,dependsOnMethods = {"updateuser"}) void deleteuser() {
	  given()
	  
	  .when() .delete("https://reqres.in/api/users/"+id) .then() .statusCode(204)
	  .log().all();
	  
	  }*/
	 
}
