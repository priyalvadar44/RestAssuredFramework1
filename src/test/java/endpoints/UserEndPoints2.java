package endpoints;

// UserEndPoints.java
// Created for perform Create , read ,Update, Delete request the user API.


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

import java.util.Properties;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    public static ResourceBundle getURLFromPropertiesFile(){
        // method created for getting url from properties file

        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;

    }
    public static Response createUser( User payload){

       String postURL= getURLFromPropertiesFile().getString("post_url");

       Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when().post(postURL)
                .then()
                .extract().response();

       return response;
    }

    public static Response readUser(String username){

        String getURL= getURLFromPropertiesFile().getString("get_url");

        Response response = given()
                .pathParam("username",username)

                .when().get(getURL)
                .then()
                .extract().response();

        return response;
    }

    public static Response   updateUser(String username,User payload){


        String updateURL= getURLFromPropertiesFile().getString("update_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when().put(updateURL)
                .then()
                .extract().response();

        return response;
    }

    public static Response deleteUser(String username){


        String deleteURL= getURLFromPropertiesFile().getString("delete_url");

        Response response = given()

                .pathParam("username",username)
                .when().delete(deleteURL )
                .then()
                .extract().response();

        return response;
    }




}
