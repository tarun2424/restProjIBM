package demo1;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostWithPayload {
	
	@Test
	public void test1() {
		//String Root_URI= "https://petstore.swagger.io/v2";
		baseURI = "https://petstore.swagger.io/v2";
		File jsonDataFile = new File("src/test/resources/payloads/example1.json");
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(jsonDataFile)
		.when()
			.post("/pet")
		.then()
			.statusCode(200)
			.log().all();
	}

}
