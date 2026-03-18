package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hook {

	@Before("@Deleteplace")
	public void beforeScenario() throws IOException {
		
		StepDef seDef = new StepDef();
		if(StepDef.place_id == null) {
			seDef.add_place_payload_with("shabeer", "america", "india");
			seDef.user_calls_with_post_http_request("AddPlaceAPI", "POST");
			seDef.verify_place_id_create_map_to_using("shabeer", "GetPlaceAPI");
		}
		
	}
}
