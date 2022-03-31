import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.File;

public class Reassured_2
{
    RequestSpecification requestSpecification,requestSpecification2;

    ResponseSpecification responseSpecification, responseSpecification2;

    @BeforeClass
    public void setup(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://jsonplaceholder.typicode.com").
                addHeader("Content-Type","application/json");
        requestSpecification = RestAssured.with().spec(requestSpecBuilder.build());
        RequestSpecBuilder requestSpecBuilder2 = new RequestSpecBuilder();
        requestSpecBuilder2.setBaseUri("https://reqres.in/api").
                addHeader("Content-Type","application/json");
        requestSpecification2 = RestAssured.with().spec(requestSpecBuilder2.build());
        responseSpecification = RestAssured.expect().
                contentType(ContentType.JSON);

    }
    @Test
    public void get(){

        Response response=
                given().
                        spec(requestSpecification).
                        when().
                        get("/posts").
                        then().
                        spec(responseSpecification).statusCode(200).log().status().log().headers().


                        extract().response();
        assertThat(response.path("[39].userId"), is(equalTo(4)));
        JSONArray arr = new JSONArray(response.asString());
        int flag = 1;
        for(int i=0;i<arr.length();i++){

            Object ob = arr.getJSONObject(i).get("title");
            if( !(ob instanceof String) ) {
                flag = 0;
                break;
            }
        }
        assertThat(flag,is(equalTo(1)));

    }



    @Test

    public void put(){

        File jsonData = new File("src//test//resource//putdata.jason");

        given().
                spec(requestSpecification2).
                body(jsonData).
                when().
                put("/users").
                then().
                spec(responseSpecification).statusCode(200).log().status().log().body().log().headers();

    }
}
