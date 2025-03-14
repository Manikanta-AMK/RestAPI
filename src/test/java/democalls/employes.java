package democalls;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class employes {

	public static Response response;
	public static int id;

	@Test(priority = 1)
	public static void getusers() {
		given().when().get("http://localhost:3000/Employers").then().statusCode(200).log().all();

	}

	@Test(priority = 2)
	public static void emplys() {
		HashMap workers = new HashMap();
		workers.put("Firstname", "Manikanta");
		workers.put("Lastname", "Arige");
		workers.put("Role", "SDET");
		workers.put("Company", "IBM");

		Response response = given().contentType("application/json").body(workers).when()
				.post("http://localhost:3000/Employers");
		response.jsonPath().getInt("id");
		response.then().statusCode(201).body("Firstname", equalTo("Manikanta")).body("Lastname", equalTo("Arige"))
				.body("Role", equalTo("SDET")).body("Company", equalTo("IBM")).log().all();
	}

	// post the data by using jsonObject

	@Test(priority = 3)
	public void postWithJsonObject() {
		JSONObject js = new JSONObject();
		js.put("name", "venom");
		js.put("lastname", "bgmi");
		js.put("power", "bokka");

		String colour[] = { "balck", "red", "blue" };
		js.put("coulur", colour);

		Response post = given().contentType("application/json").body(js).when().post("http://localhost:3000/Employers");
		post.then().statusCode(201).log().all();
		post.jsonPath().getInt("id");
		System.out.println(post);

	}

	@Test(priority = 4)
	public void validation() {
		JSONObject js2 = new JSONObject();
		js2.put("name", "blackbeast");
		js2.put("game", "bgmi");
		js2.put("royalpass", "yes");

		String weapons[] = { "AKM", "M416", "AUG", "DSR", "M24" };
		js2.put("weapons", weapons);

		given().contentType("application/json").body(js2).when().post("http://localhost:3000/Employers").then()
				.statusCode(201).body("name", equalTo("blackbeast")).body("game", equalTo("bgmi"))
				.body("royalpass", equalTo("yes")).body("weapons[0]", equalTo("AKM"))
				.body("weapons[1]", equalTo("M416")).body("weapons[2]", equalTo("AUG"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

}
