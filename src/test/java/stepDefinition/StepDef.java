package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;

public class StepDef extends Utility {
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    static String place_id;
    
    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String launguage, String address) throws IOException {
    	
    	 res=given().spec(requestSpecification()).body(testDataBuild.addPlacePaylod(name,launguage, address));
       
    }
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		
		APIResources resourceAPI =  APIResources.valueOf(resource);
		System.out.println("Resource API = "+resourceAPI +" = "+ resource);
		
		 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 
		 if(method.equalsIgnoreCase("POST"))
		     response =res.when().post(resourceAPI.getResource());
		 else if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(resourceAPI.getResource());
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String status, String value) {
		
		assertEquals(getJsonPath(response,status), value);
	}
	
	@Then("Verify place_id create map to {string} using {string}")
	public void verify_place_id_create_map_to_using(String expectedName, String resourceAPI) throws IOException {
		
	    place_id = getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resourceAPI, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
	
	}
	
	@Given("DeletePlace Paylod")
	public void delete_place_paylod() throws IOException {
		res=given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(place_id));
	}


}
