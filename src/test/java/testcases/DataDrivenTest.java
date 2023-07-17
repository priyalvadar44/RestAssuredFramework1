package testcases;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.User;
import utilities.DataProviders;

public class DataDrivenTest {

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userID,String userName, String fName, String lName,String userEmail,String pass,String phNo){


        User userPayload= new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pass);
        userPayload.setPhone(phNo);

        Response response = UserEndPoints.createUser(userPayload);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){
       Response response= UserEndPoints.deleteUser(userName);

       response.then().log().all();

       Assert.assertEquals(response.getStatusCode(),200);

    }
}
