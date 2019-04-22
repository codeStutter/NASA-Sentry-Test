package Nasa.API.Files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonConverterMethod{
	
	public static JsonPath rawToJSon(Response ResponseData) {
		String ResponseString =  ResponseData.asString();
		JsonPath stringtojson =  new JsonPath(ResponseString);
		return stringtojson;
	    }


}