package demo1;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJOClassJSONArrayPayload {
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
	public String allPojoCLassJSONPayload;
	@Test
	public void CreateList() throws JsonProcessingException {
		
		//Badminon Detail 1
		POJOClassJSONArrayPayload yonex = new POJOClassJSONArrayPayload();
		yonex.setBadmintonBrand("Yonex");
		yonex.setRacketName("Voltric 50 E-tune");
	
		//Badminon Detail 2
		POJOClassJSONArrayPayload Lining = new POJOClassJSONArrayPayload();
		Lining.setBadmintonBrand("Lining");
		Lining.setRacketName("Woods N-90");
		
		//Badminon Detail 3
		POJOClassJSONArrayPayload Victor = new POJOClassJSONArrayPayload();
		Victor.setBadmintonBrand("Victor");
		Victor.setRacketName("Ryuga");
		
		//Creating List
		List<POJOClassJSONArrayPayload> allBadmintonDetails = new ArrayList<POJOClassJSONArrayPayload>();
		allBadmintonDetails.add(yonex);
		allBadmintonDetails.add(Lining);
		allBadmintonDetails.add(Victor);
		
		//Class Object --> String (JSON Array Payload) -- Serialization
		ObjectMapper objectMapper1 = new ObjectMapper();
		allPojoCLassJSONPayload = objectMapper1.writerWithDefaultPrettyPrinter().writeValueAsString(allBadmintonDetails);
		System.out.println(allPojoCLassJSONPayload);
		
		//String (JSON Object Payload) --> Class Object  - Deserialization
		ObjectMapper objectMapper2 = new ObjectMapper();
		List<POJOClassJSONArrayPayload> allBadmintonDetails2 = objectMapper2.readValue(allPojoCLassJSONPayload, new TypeReference<List<POJOClassJSONArrayPayload>>() {});
		
		for(POJOClassJSONArrayPayload badmin : allBadmintonDetails2) {
			System.out.println("======================================================================");
			System.out.println("BadmintonBrand: "+badmin.getBadmintonBrand());
			System.out.println("RacketName: "+badmin.getRacketName());
		}
	
	}
	
}