package testcases;

import com.github.javafaker.Faker;
import endpoints.UserEndPoints;
import endpoints.UserEndPoints2;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.User;

public class UserTest2 {

   public static Faker faker;
   User userPayload;

   public Logger logger;

    @BeforeClass
    public void setUp(){

         faker=new Faker();
         userPayload=new User();
         userPayload.setId(faker.idNumber().hashCode());
         userPayload.setUsername(faker.name().username());
         userPayload.setFirstName(faker.name().firstName());
         userPayload.setLastName(faker.name().lastName());
         userPayload.setEmail(faker.internet().safeEmailAddress());
         userPayload.setPassword(faker.internet().password());
         userPayload.setPhone(faker.phoneNumber().cellPhone());

         //logs
        logger= LogManager.getLogger(this.getClass());
    }


    @Test(priority = 1)
    public void testPostUser(){
        logger.info("********* Creating User  *************");

        Response response= UserEndPoints2.createUser(userPayload);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("user created ");
    }

    @Test(priority = 2)
    public void testGetUserByName(){

        logger.info("user reading info..... ");
       Response response= UserEndPoints2.readUser(this.userPayload.getUsername());

       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(  ),200);


        logger.info("user info displayed ");
    }


    @Test(priority = 3)
    public void testUpdateUserByName(){

        // update data using payload

        logger.info("updating user ");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        Response response =
                UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload );

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);


        Response responseAfterUpdate
                = UserEndPoints2.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate  .getStatusCode(),200);


        logger.info("user is updated ");
    }


    @Test(priority = 4)
    public void deleteUserByName(){

        logger.info("user deleting ..... ");
        Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());

        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("user deleted");
        logger.debug("debugging......................");
    }


}



