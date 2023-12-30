import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.payloadfile;

public class useTestNg {
    @Test()
    public void sumOfCourse()
    {
        JsonPath mockJson = new JsonPath(payloadfile.coursePrice()) ;
        int cnt = mockJson.getInt("courses.size()") ;

        int sum = 0;
        for (int k = 0; k < cnt; k++) {
            int coursePrice = mockJson.get("courses[" + k + "].price");
            int copies = mockJson.get("courses[" + k + "].copies");
            int courseWiseSum = coursePrice * copies;
            sum = sum + courseWiseSum;
        }

        int totalCost = mockJson.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(totalCost, sum);

    }
}
