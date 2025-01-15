package FIS.Assessment;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;

import org.hamcrest.Matchers.*;

public class APIAutomation {
	
	 @Test
	    public void RegistrationSuccessful() {
	        RestAssured.baseURI = "https://api.coindesk.com";
	        RequestSpecification request = RestAssured.given();
	        Response  response = request.when().get("/v1/bpi/currentprice.json");
	        JsonPath js = response.jsonPath();    
	        int num = js.getInt("bpi.size()");
	        Assert.assertTrue(js.getInt("bpi.size()")==3);
	        Assert.assertTrue(js.get("bpi").toString().contains("USD"));
	        Assert.assertTrue(js.get("bpi").toString().contains("GBP"));
	        Assert.assertTrue(js.get("bpi").toString().contains("EUR"));    
	        String desc=js.getString("bpi.GBP.description") ;	        
	        Assert.assertEquals(desc, "British Pound Sterling");
	        
	    }
}
