package typesofpostrequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


public class postRequestwithExternalJsonfile {

	@Test
	public void externalfile() throws FileNotFoundException
	{
		//1.post request from external json file
		
		File f = new File("C:\\Users\\phani\\eclipse-workspace\\RestApi\\jsonbody.json");
	    FileReader fr = new FileReader(f);
	    JSONTokener jt = new JSONTokener(fr);
	    JSONObject  data = new JSONObject(jt);
	    
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
