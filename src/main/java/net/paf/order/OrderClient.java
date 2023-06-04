package net.paf.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import net.paf.Main;

public class OrderClient extends Main {

    @Step("Create new order {order}")
    public ValidatableResponse create(Object[][] order) {
        return getBaseSpec()
                .body(order)
                .when()
                .post(getOrderPath())
                .then();
    }

    @Step("Cancel new order {order}")
    public ValidatableResponse cancel(Object[][] order) {
        return getBaseSpec()
                .body(order)
                .when()
                .put(getOrderPath() + "/cancel")
                .then();
    }

    @Step("Get list of all orders")
    public ValidatableResponse getAllOrders() {
        return getBaseSpec()
                .when()
                .get(getOrderPath())
                .then();
    }
}

