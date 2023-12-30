package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class utils {

    public static JsonPath convertRawToJson(String resp)
    {
        JsonPath js1 = new JsonPath(resp);
        return js1;
    }
}
