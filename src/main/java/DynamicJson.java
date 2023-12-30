import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.payloadfile;
import utilities.utils;

public class DynamicJson {

    @Test()
    public void addBook()
    {
        RestAssured.baseURI = "http://216.10.245.166" ;
        String resp = given().header("Content-Type","application/json").body(payloadfile.Addbook("Python","bdf",237,"Viswa")).
                when().post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response().asString();
        JsonPath js = utils.convertRawToJson(resp) ;
        String bookid = js.get("ID");
        System.out.println(bookid);
    }
}
