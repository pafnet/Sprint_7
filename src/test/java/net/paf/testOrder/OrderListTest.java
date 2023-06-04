package net.paf.testOrder;

import io.restassured.response.ValidatableResponse;
import net.paf.order.OrderClient;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class OrderListTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void getAllOrdersTest() {
        ValidatableResponse response = orderClient.getAllOrders();

        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_OK, statusCode);

    }
}

