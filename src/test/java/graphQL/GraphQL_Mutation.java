package graphQL;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GraphQL_Mutation {

	public static String graphqlToJson(String payload) {
		JSONObject json = new JSONObject();
		json.put("query", payload);
		return json.toString();
	}
	
	@Test
	public void testMutationGraphQL() {
		baseURI="https://www.predic8.de/fruit-shop-graphql";
		String query = "mutation {\r\n"
				+ "  addCategory(id: 6, name: \"Green Fruits\", products: [8, 2, 3]) {\r\n"
				+ "    name\r\n"
				+ "    products {\r\n"
				+ "      name\r\n"
				+ "    }\r\n"
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