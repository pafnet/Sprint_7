package net.paf.testCourier;

import io.restassured.response.ValidatableResponse;
import net.paf.courier.Courier;
import net.paf.courier.CourierClient;
import net.paf.courier.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierWithTwoRequiredFieldTest {
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
        public void courierWithoutPasswordTest() {
        courier.setPassword(null);
        ValidatableResponse responseOne = courierClient.create(courier);

        int statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        String message = responseOne.extract().path("message");
        assertEquals(message,"Недостаточно данных для создания учетной записи");
    }

    @Test
    public void courierWithoutLoginTest() {
        courier.setLogin(null);
        ValidatableResponse responseOne = courierClient.create(courier);

        int statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        String message = responseOne.extract().path("message");
        assertEquals(message,"Недостаточно данных для создания учетной записи");
    }

    @Test
    public void courierWithoutNameTest() {
        courier.setFirstName(null);
        ValidatableResponse responseOne = courierClient.create(courier);

        int statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        boolean isCreated = responseOne.extract().path("ok");
        assertTrue("Courier is not created", isCreated);
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }
}

