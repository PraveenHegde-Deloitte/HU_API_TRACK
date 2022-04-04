import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class USER
{
    @Test(priority = 1)
    public void userRegister() throws IOException {
        for (int i = 1; i <= 5; i++) {
            excelData ed = new excelData();
            String nam = ed.getString(0,i, 0);
            String emai = ed.getString(0,i, 1);
            String pass = ed.getString(0,i, 2);
            int age = ed.getAge(0,i, 3);
            Data dt = new Data(nam, emai, pass, age);
            Response response=given().
                    body(dt).
                    contentType(ContentType.JSON).
                    when().
                    post("https://api-nodejs-todolist.herokuapp.com/user/register").
                    then().
                    log().body().

                    statusCode(HttpStatus.SC_CREATED).extract().response();
            JSONObject jsonObject = new JSONObject(response.asString());
            Object ObjToken = jsonObject.get("token");
            excelData excelData = new excelData();
            excelData.writeToken(ObjToken,i,4);
        }
    }
    @Test(priority = 2)
    public void userLogin() throws IOException {
        excelData ed = new excelData();
        String emai = ed.getString(0,1, 1);
        String pass = ed.getString(0,1, 2);
        Data dt = new Data(emai, pass);
        Response response = given().
                body(dt).
                contentType(ContentType.JSON).
                when().
                post("https://api-nodejs-todolist.herokuapp.com/user/login").
                then().
                log().body().
                statusCode(HttpStatus.SC_OK).extract().response();
        JSONObject jsonObject = new JSONObject(response.asString());
        Object obj = jsonObject.getJSONObject("user").get("email");
        assertThat(obj, is(emai));
    }
}