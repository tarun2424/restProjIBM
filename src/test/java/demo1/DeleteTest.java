package demo1;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class DeleteTest {
	
	@Test
	public void delete_test() {
		given()
		.when()
			.delete("https://reqres.in/api/users/2")
		.then()
			.statusCode(204)
			.log().all();
	}

	@Test
	public void pet_delete() {
		given()
			.accept(ContentType.JSON)
			.header("api-key","special-key")
		.when()
			.delete("https://petstore.swagger.io/v2/pet/9223372036854602131")
		.then()
			.statusCode(200)
			.log().all();
	}
}
