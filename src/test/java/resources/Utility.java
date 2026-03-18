package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {

		if(req == null) {
			PrintStream printStreamLog = new PrintStream(new FileOutputStream("Logging.txt"));
			 req =new RequestSpecBuilder().setBaseUri(globalValue("baseURI")).addQueryParam("key", "qaclick123")
					 .addFilter(RequestLoggingFilter.logRequestTo(printStreamLog))
					 .addFilter(ResponseLoggingFilter.logResponseTo(printStreamLog))
			.setContentType(ContentType.JSON).build();
			 return req;
		}
		 return req;
	}
	
	public static String globalValue(String key) throws IOException {
		
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Eclipse_WorkSpace\\API_Project\\RestAssuredAutomation\\src\\test\\java\\resources\\global.properties");
		properties.load(fis);
		return properties.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		return js.get(key).toString();
		
	}

}
