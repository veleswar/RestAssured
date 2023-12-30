import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.* ;

import io.restassured.path.json.JsonPath;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonFile {

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String apiResponse= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get("src/main/java/files/AddPlace.json")))).when().post("maps/api/place/add/json").then()
                .assertThat().statusCode(200).body("scope",equalTo("APP")).
                header("Server",equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();

        System.out.println(apiResponse);
        JsonPath js = new JsonPath(apiResponse);
        //get place id from response
        String placeid = js.getString("place_id");
        System.out.println(placeid);

    }


}
