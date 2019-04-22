package Nasa.API;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.testng.Assert;
import java.util.Date;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Nasa.API.Files.JsonConverterMethod;
import Nasa.API.Files.resources;

public class APIValidateFieldsTest {
	Properties prop = new Properties();	
	JsonPath js;
	public static String dateData;
	public static String urlData;
	public static String hdUrlData;
	@BeforeTest
	public void getData() throws IOException{
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Nasa\\API\\Files\\env.properties");
		prop.load(fis);
		prop.get("HOST");
	}
	@Test (priority = 1)
	public void verifyStatusCode()
	{
		RestAssured.baseURI=prop.getProperty("HOST");
	    String key = prop.getProperty("KEY");
		Response responseData = given().log().all().
		param("api_key",key ).log().all().
		when().
		get(resources.getData()).
		then().assertThat().statusCode(200).extract().response();
		JsonPath js = JsonConverterMethod.rawToJSon(responseData);
		String copyrightData = js.get("copyright");
		dateData = js.get("date");
		urlData = js.get("url");
		hdUrlData = js.get("hdurl");
}
	
	@Test(dependsOnMethods = "verifyStatusCode" )
	public void verifyDate()
	{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date todaysDate = new Date();
		Assert.assertEquals(dateData, df.format(todaysDate));
		System.out.println(dateData);
}
	
	@Test(dependsOnMethods = "verifyStatusCode" )
	public void verifyUrl()
	{

		Assert.assertNotEquals(urlData, hdUrlData);

}
	
	@Test 
	public void validateQuery()
	{
		RestAssured.baseURI=prop.getProperty("HOST");
		Response responseData = given().log().all().
		when().
		get(resources.getData()).
		then().assertThat().statusCode(403).extract().response();
		JsonPath js = JsonConverterMethod.rawToJSon(responseData);
		

}
	
	
	
}
