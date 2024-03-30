package data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIHelper {

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void sendRequestToPay(DataHelper.CardDetails cardDetails) {
        given()
                .spec(requestSpec)
                .body(cardDetails)

                .when()
                .post("/api/v1/pay")

                .then().log().all();
    }

    public static void sendRequestToPayAndGetStatus(DataHelper.CardDetails cardDetails, int statusCode, String status) {
        given()
                .spec(requestSpec)
                .body(cardDetails)

                .when()
                .post("/api/v1/pay")

                .then().log().all()
                .statusCode(statusCode)
                .body("status", equalTo(status));

    }


    public static void sendRequestToCreditPay(DataHelper.CardDetails cardDetails) {
        given()
                .spec(requestSpec)
                .body(cardDetails)

                .when()
                .post("/api/v1/credit")

                .then().log().all();
    }

    public static void sendRequestToCreditPayAndGetStatus(DataHelper.CardDetails cardDetails, int statusCode, String status) {
        given()
                .spec(requestSpec)
                .body(cardDetails)

                .when()
                .post("/api/v1/credit")

                .then().log().all()
                .statusCode(statusCode)
                .body("status", equalTo(status));

    }

}
