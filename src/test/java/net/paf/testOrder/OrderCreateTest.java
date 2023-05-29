package net.paf.testOrder;

import io.restassured.response.ValidatableResponse;
import net.paf.order.OrderClient;
import net.paf.order.OrderGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class OrderCreateTest {
    private Object[][] order;
    private OrderClient orderClient;

    @Before
    public void setUp() {
        order = OrderGenerator.getRandomOrder();
        orderClient = new OrderClient();
    }

    @After
    public void tearDown() {
        orderClient.cancel(order);
    }

    @Test
    public void orderIsCreatedTest() {
        ValidatableResponse response = orderClient.create(order);

        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        response.assertThat().body("track", notNullValue());
    }
}
