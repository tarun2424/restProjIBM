package demo1;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ExtractExample {

	@Test
	public void test_circuit() {
	//First retrive the circuit ID for the first circuit if 2023
		
				String circuitId = given()
				.when()
					.get("https://ergast.com/api/f1/2023/circuits.json")
				.then()
					.extract()
					.path("MRData.CircuitTable.Circuits[1].circuitId");
	// Then pass it to second request
				given()
					.pathParam("circuitId1", circuitId)
				.when()
					.get("https://ergast.com/api/f1/2023/circuits/{circuitId1}.json")
				.then()
					.log().all();
		

	}
				

}
