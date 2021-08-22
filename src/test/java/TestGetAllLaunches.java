package test.java;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestGetAllLaunches extends BaseTest {

    @Test
    public void verifyGetAll() {

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/v4/launches");

        int actualStatusCode = response.getStatusCode();
        if (actualStatusCode == 200) {
            Assert.assertTrue(true, "Expected and Actual Status Code is 200");
        } else {
            Assert.fail("Expected Status Code = 200. Actual Status Code = " + actualStatusCode);
        }

        // ********* Additional details can be verified as per test requirements

        System.out.println("Response Body : \n" + response.getBody().asString());
        System.out.println("Response StatusLine : \n" + response.getStatusLine());

        //Get all headers
        Headers resHeaders = response.headers();
        for (Header header : resHeaders) {
            System.out.println("Header-Name = " + header.getName() + " --> Header-Value = " + header.getValue());
        }

        JsonPath jpath = response.jsonPath();

        //Get Total IDs size
        ArrayList<String> ids = jpath.get("id");
        System.out.println("Total launches Ids  = " + ids.size());

        //Get first id value
        System.out.println("1st ID from the Json response = " + jpath.get("[0].id").toString());

    }
}
