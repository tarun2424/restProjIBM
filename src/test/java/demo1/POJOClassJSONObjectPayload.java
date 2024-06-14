package demo1;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class POJOClassJSONObjectPayload {
	//Data members
	private String badmintonBrand;
	private String racketName;
	
	//getters and setters
	public String getBadmintonBrand() {
		return badmintonBrand;
	}
	
	public void setBadmintonBrand(String badmintonBrand) {
		this.badmintonBrand = badmintonBrand;
	}
	
	public String getRacketName() {
		return racketName;
	}
	
	public void setRacketName(String racketName) {
		this.racketName = racketName;
	}
	
	@Test
	public void BadmintonJSONFromPOJOClass() throws JsonProcessingException {
		//Creating object of this class
		POJOClassJSONObjectPayload objpojo = new POJOClassJSONObjectPayload();
	
		//set values
		objpojo.setBadmintonBrand("Yonex");
		objpojo.setRacketName("Voltric 50 E-tune");
		
		//Class Object --> String (JSON Object Payload) -- Serialization
		ObjectMapper objectMapper = new ObjectMapper();
		String objpojojson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objpojo);
		System.out.println(objpojojson);
		
		//String (JSON Object Payload) --> Class Object  - Deserialization
		POJOClassJSONObjectPayload objpojo2 = objectMapper.readValue(objpojojson, POJOClassJSONObjectPayload.class);
		System.out.println("BadmintonBrand: "+objpojo2.getBadmintonBrand());
		System.out.println("Racket Name: "+objpojo2.getRacketName());
	}
	
}
