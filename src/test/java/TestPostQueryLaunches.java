package test.java;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPostQueryLaunches extends BaseTest {

    @Test
    public void verifyPost() {

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Connection", "keep-alive");

        Response response = httpRequest.request(Method.POST, "/query");

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

    }
}
