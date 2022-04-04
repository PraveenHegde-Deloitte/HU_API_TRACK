import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class Tasks
{
    @Test(priority = 1)
    public void add_tasks() throws IOException {
        excelData ed=new excelData();
        String token=ed.getToken(0,1,4);
        for(int i=1;i<=20;i++){
            String task=ed.getToken(1,i,0);
            Data dt=new Data(task);
            Response response = given().
                    body(dt).
                    headers(
                            "Authorization",
                            "Bearer " + token,
                            "Content-Type",
                            ContentType.JSON,
                            "Accept",
                            ContentType.JSON).
                    // contentType(ContentType.JSON).
                            when().
                    post("https://api-nodejs-todolist.herokuapp.com/task").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_CREATED).extract().response();

        }
    }
    @Test(priority = 2)
    public void pagination() throws IOException {
        excelData ed=new excelData();
        String token=ed.getToken(0,1,4);
        for(int i=0;i<3;i++) {
            int k;
            if(i==0){
                k=2;
            }
            else if(i==1){
                k=5;
            }
            else
                k=10;
            Response response = given().param("limit", k).
                    headers(
                            "Authorization",
                            "Bearer " + token,
                            "Content-Type",
                            ContentType.JSON,
                            "Accept",
                            ContentType.JSON).
                    // contentType(ContentType.JSON).
                            when().
                    get("https://api-nodejs-todolist.herokuapp.com/task").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_OK).extract().response();
            JSONObject jsonObject = new JSONObject(response.asString());
            Object ObjToken = jsonObject.get("count");
            assertThat(ObjToken, is(k));
        }
    }
}
