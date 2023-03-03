package productotesting;


import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class guide {
    final String url = "https://jsonplaceholder.typicode.com";
    public static String value;

    @Before
    public void setUp() {
        RestAssured.config = new RestAssuredConfig().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.baseURI = url;
    }

    @Test
    public void a_getGuide() {
            get("/posts/1").then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void b_postguide() {
        Map<String, Object> guid = new HashMap<>();
        guid.put("title", "Taller1");
        guid.put("body", "var");
        guid.put("userId", 1);

             given().log().all().contentType(ContentType.JSON).body(guid)
                .when().post("/posts")
                .then().log().all().assertThat().statusCode(201).and().body("id", notNullValue()
                        , "title", equalTo("Taller1")
                        , "body", equalTo("var")
                        , "userId", equalTo(1)).extract().jsonPath();

    }

    @Test
    public void c_updateProduct() {
        Map<String, Object> product = new HashMap<>();
        product.put("title", "Taller1.2");
        product.put("body", "Bar1.2");

        given().log().all().contentType(ContentType.JSON)
                .body(product).when().put("/posts/1")
                .then().log().all().assertThat().statusCode(200).and().body("title", equalTo("Taller1.2")
                        , "body", equalTo("Bar1.2"));
    }


    @Test
    public void d_deleteguide() {
            delete("/posts/1").then().log().all().assertThat().statusCode(200);
    }


}
