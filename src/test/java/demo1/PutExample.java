package demo1;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class PutExample {

	@Test
	public void putTest() {
		baseURI = "https://petstore.swagger.io/v2";
		File jsonDataFile = new File("src/test/resources/payloads/example2.json");
		given()
			.accept(ContentType.JSON)
			//.contentType(ContentType.JSON)
			.headers("Content-Type","application/json")
			.body(jsonDataFile)
			
	.when()
		.put("/pet")
	.then()
		.statusCode(200)
		.log().all();
	}
}
