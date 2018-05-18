package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReusableMethods {


    public static XmlPath rawToXML(Response res){

        String responseString = res.asString();
        XmlPath x = new XmlPath(responseString);

        return x;
    }


    public static JsonPath rawToJson(Response res){

        String responseString = res.asString();
        JsonPath js = new JsonPath(responseString);

        return js;
    }

    public static String expires(JsonPath js, int i){
        ArrayList<String> expireArray = new ArrayList<String>();
        String expiresSource = js.getString("expires["+i+"]");
        expireArray.add(expiresSource);

        for (String dateStr : expireArray){
            if (dateStr != null){

                LocalDateTime parse = LocalDateTime.parse(expiresSource, DateTimeFormatter.ISO_DATE_TIME);
                String expiresFinal = parse.format(DateTimeFormatter.ofPattern("dd MM yyyy hha"));

                return expiresFinal;
            }
        }
        return "No avaliable date";

    }



}
