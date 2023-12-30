import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.* ;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import payload.payloadfile;
import utilities.utils;


public class basics {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
         String apiResponse= given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payloadfile.getPlace()).when().post("maps/api/place/add/json").then()
                .assertThat().statusCode(200).body("scope",equalTo("APP")).
                 header("Server",equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();

        System.out.println(apiResponse);
        JsonPath js = new JsonPath(apiResponse);
        //get place id from response
        String placeid = js.getString("place_id");
        System.out.println(placeid);

        //update placeid

        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeid+"\",\n" +
                        "\"address\":\"71 Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

        String newadress = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).body("address",equalTo("71 Summer walk, USA")).extract().response().asString();
        //JsonPath na = new JsonPath(newadress);
        JsonPath na = utils.convertRawToJson(newadress);
        String fa = na.getString("address");
        Assert.assertEquals(fa,"71 Summer walk, USA");

    }
}
