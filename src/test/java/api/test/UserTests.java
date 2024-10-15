package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests {
	
	static Faker faker;
	static User userpayload;
	public static Logger logger;

	@BeforeClass
	public static void setup() {
		
		faker = new Faker();
		userpayload = new User();		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setUsername(faker.name().username());
		
		//logs 
		logger = LogManager.getLogger();
		
	}
	
	@Test(priority=1)
	public void testPostUser(){
		logger.info("****************Creating User***********************");
		Response response = UserEndpoints.createuser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******************Created User*********************");
		
	}
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("*******************Retrieving the User*********************");
		Response response = UserEndpoints.getUser(userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(userpayload.getUsername(), response.jsonPath().get("username"));
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*******************User by Name is retrieved*********************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		logger.info("*******************Update the User*********************");
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());		
		Response response = UserEndpoints.updateuser(userpayload.getUsername(), userpayload);
		response.then().log().body().statusCode(200);
	//	Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******************Updated User*********************");
		testGetUserByName();
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByUsername() {
		logger.info("*******************Deleting the User*********************");
		Response response = UserEndpoints.deleteUser(userpayload.getUsername());
		response.then().statusCode(200).log().all();
		logger.info("*******************Deleted  User*********************");
	}
	
	}


