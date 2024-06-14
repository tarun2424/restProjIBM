package demo1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class F1Example2 {

	//Checking the count or size
	@Test
	public void test_NumberOfCircuits() {
		given()
		.when()
			.get("https://ergast.com/api/f1/2023/circuits.json")
		.then()
			.assertThat()
			.body("MRData.CircuitTable.Circuits", hasSize(22));
	}
	
	@Test
	public void test_ResponseHeaderData() {
		given()
		.when()
			.get("https://ergast.com/api/f1/2023/circuits.json")
		.then()
			.assertThat()
			.statusCode(200)
		.and()
			.contentType(ContentType.JSON)
		.and()
			.header("Content-Length", equalTo("5013"))
			.log().all();
	}
	
	//Path Parameter
	@Test
	public void test_NoOfCircuitsParametrized() {
		String season = "2017";
		int numberOfRaces = 20;
		
		given()
			.pathParam("raceSeason", season)
		.when()
			.get("https://ergast.com/api/f1/{raceSeason}/circuits.json")
		.then()
			.assertThat()
			.body("MRData.CircuitTable.Circuits", hasSize(numberOfRaces));
			
	}
	
	//Using Data Provider
	@DataProvider(name="seasonAndNoOfRaces")
	public Object[][] createTestData(){
		return new Object[][] {
			{"2017",20},
			{"2020",14},
			{"2023",22},
			{"2024",24}
		};
	}
	
	
	@Test(dataProvider="seasonAndNoOfRaces")
	public void testNoOfCircuits_DataDriven(String season, int noOfRaces) {
		given()
			.pathParam("raceSeason1", season)
		.when()
			.get("https://ergast.com/api/f1/{raceSeason1}/circuits.json")
		.then()
			.assertThat()
			.body("MRData.CircuitTable.Circuits", hasSize(noOfRaces));
	}

	//Response Specification Builder
	ResponseSpecification checkStatusAndContentType = new ResponseSpecBuilder()
														.expectStatusCode(200)
														.expectContentType(ContentType.JSON).build();
	
	@Test
	public void test_noOfCircuitsUsingResSpec() {
		given()
		.when()
			.get("https://ergast.com/api/f1/2023/circuits.json")
		.then()
			.assertThat()
			.spec(checkStatusAndContentType)
		.and()
			.body("MRData.CircuitTable.Circuits[0].Location.country", equalTo("Australia"));
	}
}
