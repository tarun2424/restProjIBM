package demo1;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetTest {

	@Test
	public void test1() {
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		
		System.out.println(response.statusCode());
		System.out.println(response.asString());
		System.out.println(response.getTime());
		System.out.println(response.statusLine());
		
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	
	@Test
	public void test2() {
		given()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("data[0].id",equalTo(7))
			.body("data.first_name", hasItems("Michael","Byron","Tobias"))
			.log().all();
	}
}
