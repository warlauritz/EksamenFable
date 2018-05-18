package fablemedia.demo;

import com.jayway.jsonpath.DocumentContext;
import files.Resources;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GoDaddyGET {


    Properties prop = new Properties();


    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\Users\\nerdi\\FableMedia\\src\\main\\java\\files\\env.properties");
        prop.load(fis);

        //prop.getProperty("HOST");
    }


    public void Test(){

        // write your code here

        //BaseURL or Host
        RestAssured.baseURI = prop.getProperty("GODADDYHOST");

        Response res = given().
                header("Authorization", prop.getProperty("GODADDYKEY")).log().all().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                param("includes", "contacts").
                when().
                get(Resources.godaddyGetData()).
                then().assertThat().statusCode(200).
                extract().response();


//        DocumentContext raw = com.jayway.jsonpath.JsonPath.parse(res);
//
//        JSONArray domains = raw.read("$..domain");
//        for (int i = 0; i < domains.size(); i++){
//            System.out.println(domains.get(i));
//        }

        JsonPath js = ReusableMethods.rawToJson(res);

        int count = js.get("array.size()");

        for (int i = 0; i < count; i++){
            String name = js.get("array["+i+"].domain");
            System.out.println(name);
        }
        System.out.println(count);



    }
}




