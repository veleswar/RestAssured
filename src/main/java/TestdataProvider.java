import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payload.payloadfile;
import utilities.utils;

import static io.restassured.RestAssured.given;

public class TestdataProvider {

    @Test(dataProvider = "booksdata")
    public void addBook(String bookName,String isbn,int aisle,String author)
    {
        RestAssured.baseURI = "http://216.10.245.166" ;
        String resp = given().header("Content-Type","application/json").body(payloadfile.Addbook(bookName,isbn,aisle,author)).
                when().post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response().asString();
        JsonPath js = utils.convertRawToJson(resp) ;
        String bookid = js.get("ID");
        System.out.println(bookid);
    }

    @DataProvider(name="booksdata")
    public Object[][] getBooksData()
    {
        return new Object[][] {{"python1","abc",123,"v1"},{"python1","abd",124,"v2"}};

    }
}
