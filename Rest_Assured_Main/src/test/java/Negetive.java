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

public class Negetive {
        @Test(priority = 1)
        public void user_already_Register() throws IOException {
            excelData ed = new excelData();
            String nam = ed.getString(2,1, 0);
            String emai = ed.getString(2,1, 1);
            String pass = ed.getString(2,1, 2);
            int age = ed.getAge(2,1, 3);
            Data dt = new Data(nam, emai, pass, age);
            Response response =  given().
                    body(dt).
                    contentType(ContentType.JSON).
                    when().
                    post("https://api-nodejs-todolist.herokuapp.com/user/register").
                    then().
                    log().body().

                    statusCode(HttpStatus.SC_CREATED).extract().response();
        }
        @Test(priority = 2)
        public void InvalidUserLogin() throws IOException {
            excelData ed = new excelData();
            String emai = "u344sr@gmail.com";
            String pass = "1132342";
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
        @Test(priority = 3)
        public void wrong_add_tasks() throws IOException {
            excelData ed=new excelData();
            String token=ed.getToken(0,1,4);
            String task=ed.getToken(1,1,0);
            // data dt=new data(task);
            String dt = "ffrrf";
            Response response = given().
                    body(" ").
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
