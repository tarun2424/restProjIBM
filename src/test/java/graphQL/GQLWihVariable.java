package graphQL;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.*;

import io.restassured.http.ContentType;

public class GQLWihVariable {

	private static String graphqlToJson(String payload)
	{
	     JSONObject json = new JSONObject();
	     json.put("query",payload);
	     return  json.toString();
	}
	
	@DataProvider
	public Object[][] getQueryData(){
	return new Object[][] {{"8"},
	};
	}
	
	@Test(dataProvider="getQueryData")
	public void testGraphql(String id)
	{
	baseURI="https://www.predic8.de/fruit-shop-graphql?";
	String query="{\"query\":\"query($id:String!){\\n  products(id:$id) {\\n    name\\n    price\\n    category {\\n      name\\n    }\\n    vendor {\\n      name\\n      id\\n    }\\n  }\\n}\\n\","
			+ "\"variables\":{\"id\":"+id+"}}";
	String jsonString= graphqlToJson(query);
		
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
