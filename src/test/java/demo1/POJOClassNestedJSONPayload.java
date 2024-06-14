package demo1;
//POJO for bwlow JSON
/*{
	"governingBody":"BWF",
	"hostingCity":"Tokyo",
	"sponsors":"TotalEnergies",
	"elitePlayers": {
		"ms":"LIN DAN",
		"ws": "P V SINDHU"
	}
}*/
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJOClassNestedJSONPayload {

	public class ElitePlayers{
		private String ms;
		private String ws;
		
		public String getMS() {
			return ms;
		}
		
		public void setMS(String ms) {
			this.ms=ms;
		}
		public String getWS() {
			return ws;
		
		}
		public void setwS(String ws) {
			this.ws=ws;
		}
	}
	
	private String governingBody;
	private String hostingCity;
	private String sponsors;
	ElitePlayers elitePlayers;
	
	public String getGoverningBody() {
		return governingBody;
	}
	
	public void setGoverningBody(String governingBody) {
		this.governingBody = governingBody;
	}
	
	public String getHostingCity() {
		return hostingCity;
	}
	
	public void setHostingCity(String hostingCity) {
		this.hostingCity = hostingCity;
	}
	
	public String getSponsors() {
		return sponsors;
	}
	
	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
	}
	
	public ElitePlayers getElitePlayers() {
		return elitePlayers;
	}
	
	public void setElitePlayers(ElitePlayers elitePlayers) {
		this.elitePlayers = elitePlayers;
	}
	
	public class CreateNestedJSONFromPojoClass{
		
		
		@Test
		public void test1() throws JsonProcessingException {
			POJOClassNestedJSONPayload nestedPOJO = new POJOClassNestedJSONPayload();
			nestedPOJO.setGoverningBody("BWF");
			nestedPOJO.setHostingCity("Tokyo");
			nestedPOJO.setSponsors("TotalEnergies");
			ElitePlayers elitePlayers = new ElitePlayers();
			elitePlayers.setMS("LIN DAN");
			elitePlayers.setwS("P V SINDHU");
			nestedPOJO.setElitePlayers(elitePlayers);
			
			//Class Object --> String (JSON Object Payload) -- Serialization
			ObjectMapper objectMapper2 = new ObjectMapper();
			String nestedJsonPayload = objectMapper2.writerWithDefaultPrettyPrinter().writeValueAsString(nestedPOJO);
			System.out.println(nestedJsonPayload);
			
			//String (JSON Object Payload) --> Class Object  - Deserialization
			POJOClassNestedJSONPayload objpojo3 = objectMapper2.readValue(nestedJsonPayload, POJOClassNestedJSONPayload.class);
			System.out.println("ElitePlayers(WS): "+objpojo3.getElitePlayers().getWS());
			System.out.println("GoverningBody: "+objpojo3.getGoverningBody());
			

			
		}
	}
	
}
