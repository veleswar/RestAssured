import io.restassured.path.json.JsonPath;
import org.apache.http.io.SessionOutputBuffer;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.payloadfile ;
public class complexJsonParse {
    public static void main(String[] args) {

        JsonPath mockJson = new JsonPath(payloadfile.coursePrice()) ;

        // print no of courses returned by api
        System.out.println(mockJson.getInt("courses.size()"));

        // get purchase amount
        System.out.println("Purchase amount is :" + mockJson.getInt("dashboard.purchaseAmount"));

        // print title of first course
        System.out.println("First course title is : " +mockJson.getString("courses[0].title"));

        // print all course titles and their respective Prices
        System.out.println("course title and price are as below:");
         int cnt = mockJson.getInt("courses.size()") ;

         for(int i=0;i<cnt;i++)
         {
             System.out.println(mockJson.get("courses["+i+"].title") +":" +mockJson.get("courses["+i+"].price"));
         }

         // print no of copies sold by RPA COURSE
         for (int j=0;j<cnt;j++)
         {
             if (mockJson.getString("courses["+j+"].title").equalsIgnoreCase("RPA"))
             {
                 System.out.println("copies sold for RPA:"+ mockJson.get("courses["+j+"].copies") );
                 break;
             }
         }

          //verify if sum of all course prices matches with purchase amount
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
