package com.restassured.api.tests;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.global.dataprovider.JsonFileReader;
import com.restassured.api.extensions.RestAssuredExtensions;
import com.selenium.ui.helper.ExceptionHelper;
import com.selenium.ui.helper.LoggerHelper;
import com.selenium.ui.listener.ReportListener;
import com.selenium.ui.testbase.TestBase;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class PetStoreE2EIT extends TestBase {



	@Test(groups="admin",priority=1)
	public void createCustomer() throws ExceptionHelper {
		Reporter.log("Creating the new customer test initiated");
		File jsonDataInFile = new File("src/main/resources/testdata/userdata.json");
		RequestSpecification requestspec = RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setEndPoint("/user");
		requestspec.contentType(ContentType.JSON).body(jsonDataInFile);
		RestAssuredExtensions.getResponse(requestspec, "POST").then()
		.assertThat().statusCode(200)
		.body("username", equalTo("theUser"));
		Reporter.log("Validating the created user in the response is successful");
	}


	@Test(groups="admin",priority=2)
	public void adminAddingNewPet() {
		Reporter.log("Placing the order for the pet");
		JsonFileReader jfr = new JsonFileReader("src/main/resources/testdata/pet.json");
		File jsonDataInFile = new File("src/main/resources/testdata/pet.json");
		RequestSpecification requestspec = RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setEndPoint("/pet");
		requestspec.contentType(ContentType.JSON).body(jsonDataInFile);
		RestAssuredExtensions.getResponse(requestspec, "POST").then()
		.assertThat().statusCode(200)
		.body("name", equalTo(jfr.getValueFromJson("name").toString()));
		Reporter.log("Validating the placed order by comparing the petID is successful");
	}
	
	@Test(groups="admin",priority=3)
	public void adminUpdateExistingPet() {
		Reporter.log("To check whether the admin is able to update the existing product");
		JsonFileReader jfr = new JsonFileReader("src/main/resources/testdata/petupdate.json");
		File jsonDataInFile = new File("src/main/resources/testdata/petupdate.json");
		RequestSpecification requestspec = RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setEndPoint("/pet");
		requestspec.contentType(ContentType.JSON).body(jsonDataInFile);
		RestAssuredExtensions.getResponse(requestspec, "PUT").then()
		.assertThat().statusCode(200)
		.body("name", equalTo(jfr.getValueFromJson("name").toString()));
		Reporter.log("Validating the placed order by comparing the petID is successful");
	}

	
	@Test(dependsOnGroups="admin",priority=8)
	public void adminDeleteUser() {
		Reporter.log("To check whether the admin is able to delete the user");
		JsonFileReader jfr = new JsonFileReader("src/main/resources/testdata/pet.json");
		RequestSpecification requestspec = RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setEndPoint("/pet/"+jfr.getValueFromJson("id"));
		requestspec.contentType(ContentType.JSON);
		RestAssuredExtensions.getResponse(requestspec, "DELETE").then()
		.assertThat().statusCode(200);
		Reporter.log("User deleted successfully");
	}


	@Test(dependsOnGroups = "admin",priority=4)
	public void loginAsCustomer() {
		Reporter.log("Login using the customer already created");
		RestAssuredExtensions.setEndPoint("/user/login");
		RequestSpecification requestspec = RestAssuredExtensions.getRequestSpecification();
		JsonFileReader jfr = new JsonFileReader("src/main/resources/testdata/userdata.json");
		requestspec.queryParam("username", jfr.getValueFromJson("username").toString()).queryParam("password", jfr.getValueFromJson("password").toString());
		RestAssuredExtensions.getResponse(requestspec,"GET").then().assertThat().statusCode(200);
		Reporter.log("Validating the status code in the response is successful");
	}


	@Test(dependsOnGroups = "admin",priority=5)
	public void searchPetByID() {
		Reporter.log("Search the inventory by pet id");
		JsonFileReader jfr = new JsonFileReader("src/main/resources/testdata/pet.json");
		RestAssuredExtensions.setEndPoint("/pet/"+jfr.getValueFromJson("id"));
		RestAssuredExtensions.getResponse(RestAssuredExtensions.getRequestSpecification(),"GET").then().assertThat().statusCode(200);
		Reporter.log("Search by PetID is successful");
	}


	@Test(dependsOnGroups = "admin",priority=6)
	public void placeOrder() {
		Reporter.log("Placing the order for the pet");
		JsonFileReader jfr = new JsonFileReader("src/main/resources/testdata/order.json");
		File jsonDataInFile = new File("src/main/resources/testdata/order.json");
		RequestSpecification requestspec = RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setEndPoint("/store/order");
		requestspec.contentType(ContentType.JSON).body(jsonDataInFile);
		RestAssuredExtensions.getResponse(requestspec, "POST").then()
		.assertThat().statusCode(200)
		.body("petId", equalTo(Integer.parseInt(jfr.getValueFromJson("petId").toString())));
		Reporter.log("Validating the placed order by comparing the petID is successful");
	}


	@Test(dependsOnGroups = "admin",priority=7)
	public void logoutAsCustomer() {
		Reporter.log("Logout using the customer already created");
		RestAssuredExtensions.setEndPoint("/user/logout");
		RequestSpecification requestspec = RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.getResponse(requestspec,"GET").then().assertThat().statusCode(200);
		Reporter.log("Validating the status code for logout operation in response is successful");
	}

}
