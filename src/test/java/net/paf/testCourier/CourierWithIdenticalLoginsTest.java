package net.paf.testCourier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.paf.courier.Courier;
import net.paf.courier.CourierClient;
import net.paf.courier.CourierGenerator;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class CourierWithIdenticalLoginsTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierID;
    private int statusCode;
    private boolean isCreated;

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
    @DisplayName("Creating several same couriers (shouldn't work, status code = 409)")
    public void CouriersCreatedIdenticalImpossibleTest() {

        ValidatableResponse responseOne = courierClient.create(courier);

        statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        isCreated = responseOne.extract().path("ok");
        assertTrue("Courier is not created", isCreated);

        ValidatableResponse responseTwo = courierClient.create(courier);

        statusCode = responseTwo.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CONFLICT, statusCode);
        String message = responseTwo.extract().path("message");
        assertEquals(message,"Этот логин уже используется. Попробуйте другой.");
    }
}

