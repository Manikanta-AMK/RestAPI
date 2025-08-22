package typesofpostrequest;

import org.testng.Assert;
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

public class postRequestWithHashMap {

	String url = "http://localhost:3000/employers";
	int userid;

	@Test
	public void hasmap() {
		// 1.post request body creation using hashmap
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "abcd");
		data.put("nickname", "xyz");
		data.put("job", "nothing");
		data.put("surname", "nick");

		String skills[] = { "java", "selenium" };
		data.put("courses", skills);

		userid = given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/employers")

				.then().statusCode(201).body("name", equalTo("abcd")).body("nickname", equalTo("xyz"))
				.body("job", equalTo("nothing")).body("surname", equalTo("nick")).body("courses[0]", equalTo("java"))
				.body("courses[1]", equalTo("selenium")).header("content-type", equalTo("application/json; charset=utf-8"))
				.log().body().extract().jsonPath().getInt("id");
		System.out.println(userid);
	}

	@Test
	public void createRequest() {
		try {
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("firstName", "AMK");
			data.put("lastName", "Manikanta");
			data.put("job", "SDET");
			data.put("Experience", "5 years");
			data.put("Company", "Capgemini");
			data.put("Age", "30");
			data.put("Gender", "Male");

			String[] skills = { "Selenium", "java", "Cucumber", "CICD", "Git", "Maven", "Jmeter" };
			data.put("skills", skills);

			userid = given().contentType("application/json").body(data).when().post(url).then()
					.header("Content-Type", "application/json; charset=utf-8").time(lessThan(2000L)).statusCode(201)
					.body("firstName", equalTo("AMK")).body("lastName", equalTo("Manikanta"))
					.body("job", equalTo("SDET")).body("Experience", equalTo("5 years"))
					.body("Company", equalTo("Capgemini")).body("Age", equalTo("30")).body("Gender", equalTo("Male"))
					.body("skills[0]", equalTo("Selenium")).body("skills[1]", equalTo("java"))
					.body("skills[2]", equalTo("Cucumber")).body("skills[3]", equalTo("CICD"))
					.body("skills[4]", equalTo("Git")).body("skills[5]", equalTo("Maven"))
					.body("skills[6]", equalTo("Jmeter")).log().body().extract().jsonPath().getInt("id");
			System.out.println(userid);
		} catch (Exception e) {
			Assert.fail("testcase is failed " + e.getMessage());
		}
	}
	
	@Test(dependsOnMethods ="hasmap")
	public void deleteRequest()
	{
		given()
		.when()
			.delete(url+"/"+userid)
		.then()
			.statusCode(200)
			.time(lessThan(2000L))
			.log().all();
		System.out.println(userid);
	}

}
