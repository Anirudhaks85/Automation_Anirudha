package FIS.Assessment;

import org.junit.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIAutomation {
	
	 @Test
	    public void RegistrationSuccessful() {
	        RestAssured.baseURI = "api.coindesk.com";
	        Response response = RestAssured.given().when().get("/v1/bpi/currentprice.json");
	        JsonPath js = new JsonPath(response.asString());
	        int expectedSize=3;
	        int actualSize = js.getInt("bpi.size()");
	        Assert.assertEquals(actualSize, expectedSize);
	        String expected1="USD";
	        String expected2="GBP";
	        String expected3="EUR";
	        String actual1=js.bpi[0];
	        String actual2=js.bpi[1];
	        String actual3=js.bpi[2];
	        
	        Assert.assertEquals(actual1, expected1);
	        Assert.assertEquals(actual2, expected2);
	        Assert.assertEquals(actual3, expected3);
	        
	        String gbpVal=js.bpi.GBP.code ;
	        String desc=js.bpi.GBP.description ;
	        
	        Assert.assertEquals(gbpVal, "GBP");
	        Assert.assertEquals(desc, "British Pound Sterling");
	    }
}
