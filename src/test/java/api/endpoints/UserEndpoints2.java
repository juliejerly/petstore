package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.*;

import io.restassured.http.ContentType;


public class UserEndpoints2 {
	
	
	static ResourceBundle getURL(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");//load the properties file
		return routes;
		
	}
	
	//created to perform crud (create, retrieve, update, delete ) operations 
	public static Response createuser(User userpayload){
		String post_url = getURL().getString("post_url");
		Response response = given().log().all()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(userpayload)
		.when()
			.post(post_url);
		return response;
	}
	
	public static Response getUser(String username){
		String get_url = getURL().getString("get_url");
		Response response = given().log().all()
				.pathParam("username", username)
		
		.when()
			.get(get_url);
		return response;
	}
	public static Response updateuser(String username, User userpayload){
		String update_url = getURL().getString("get_url");
		Response response = given().log().all().pathParam("username", username)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(userpayload)
		.when()
			.put(update_url);
		return response;
	}
	public static Response deleteUser(String username){
		String delete_url = getURL().getString("get_url");
		Response response = given().log().all()
				.pathParam("username", username)
		
		.when()
			.delete(delete_url);
		return response;
	}
	
}
