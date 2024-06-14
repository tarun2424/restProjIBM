package graphQL;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class GQL_Query {
	
	public static String graphqlToJson(String payload) {
		JSONObject json = new JSONObject();
		json.put("query", payload);
		return json.toString();
	}
	
	@Test
	public void testGraphQL() {
		baseURI="https://www.predic8.de/fruit-shop-graphql";
		String query = "query{\r\n"
				+ " products(id: \"7\"){\r\n"
				+ "	name\r\n"
				+ "	price\r\n"
				+ "	category{\r\n"
				+ "	 name\r\n"
				+ "	 }\r\n"
				+ "	 vendor{\r\n"
				+ "	  name\r\n"
				+ "	  id\r\n"
				+ "	}\r\n"
				+ "  }\r\n"
				+ "}";
		
		String jsonString=graphqlToJson(query);
		
		given()
			.contentType(ContentType.JSON)
			.body(jsonString)
		.when()
			.post()
		.then()
			.assertThat()
			.statusLine("HTTP/1.1 200 OK")
			.log().all();
	}

}
