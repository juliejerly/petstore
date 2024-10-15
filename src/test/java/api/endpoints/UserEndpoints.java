package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.response.*;

import io.restassured.http.ContentType;


public class UserEndpoints {
	
	//created to perform crud (create, retrieve, update, delete ) operations 
	public static Response createuser(User userpayload){
		Response response = given().log().all()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(userpayload)
		.when()
			.post(Routes.post_url);
		return response;
	}
	
	public static Response getUser(String username){
		Response response = given().log().all()
				.pathParam("username", username)
		
		.when()
			.get(Routes.get_url);
		return response;
	}
	public static Response updateuser(String username, User userpayload){
		Response response = given().log().all().pathParam("username", username)
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(userpayload)
		.when()
			.put(Routes.put_url);
		return response;
	}
	public static Response deleteUser(String username){
		Response response = given().log().all()
				.pathParam("username", username)
		
		.when()
			.delete(Routes.delete_url);
		return response;
	}
	
}
