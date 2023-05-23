package net.paf.testCourier;

import io.restassured.response.ValidatableResponse;
import net.paf.courier.Courier;
import net.paf.courier.CourierClient;
import net.paf.courier.CourierCredentials;
import net.paf.courier.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class CourierCreateTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierID;

    @Before
    public void setUp() {
        courier = CourierGenerator.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierID);
    }

    @Test
    public void courierIsCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);

        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_OK, loginStatusCode);
        courierID = loginResponse.extract().path("id");
        assertNotNull("Courier was not created or id is incorrect", courierID);
    }
}
