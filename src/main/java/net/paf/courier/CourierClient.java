package net.paf.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import net.paf.Main;

public class CourierClient extends Main {

    @Step("Create new courier {courier}")
    public ValidatableResponse create(Courier courier) {
        return getBaseSpec()
                .body(courier)
                .when()
                .post(getCourierPath())
                .then();
    }

    @Step("Login with courier {courier}")
    public ValidatableResponse login(CourierCredentials credentials) {
        return getBaseSpec()
                .body(credentials)
                .when()
                .post(getCourierPath() + "/login")
                .then();
    }

    @Step("Delete courier {courier}")
    public ValidatableResponse delete(int courierID) {
        return getBaseSpec()
                .body(courierID)
                .when()
                .delete(getCourierPath() + "/" + courierID)
                .then();
    }

}
