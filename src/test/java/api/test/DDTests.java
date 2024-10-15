package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	User userpayload = new User();
	
	@Test(priority =1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String UserID, String UserName, String fname, String lname, String Email, String Password, String Phone) {
		
		
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		
		Response  response = UserEndpoints.createuser(userpayload);
		response.then().statusCode(200);
	}
	
	@Test(priority =3, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testGetUser(String UserName) {
		Response getresponse = UserEndpoints.getUser(UserName);
		getresponse.then().log().body().statusCode(404);
	}
	
	@Test(priority =2, dataProvider="UserNames", dataProviderClass=DataProviders.class)

	public void testDeleteUser(String UserName) {
		Response response = UserEndpoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
