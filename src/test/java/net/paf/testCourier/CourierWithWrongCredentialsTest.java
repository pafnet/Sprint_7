package net.paf.testCourier;

import io.restassured.response.ValidatableResponse;
import net.paf.courier.Courier;
import net.paf.courier.CourierClient;
import net.paf.courier.CourierCredentials;
import net.paf.courier.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.Assert.assertEquals;

public class CourierWithWrongCredentialsTest {
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
    public void courierLogsInWithoutLoginTest() {
        courier.setLogin(null);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals(message,"Недостаточно данных для входа");
    }

    @Test
    public void courierLogsInWithoutPasswordTest() {
        courier.setPassword(null);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals(message,"Недостаточно данных для входа");
    }

    @Test
    public void courierWithWrongLoginTest() {
        courier.setLogin("WrongLogin");
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_NOT_FOUND, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals(message,"Учетная запись не найдена");
    }

    @Test
    public void courierWithWrongPasswordTest() {
        courier.setPassword("WrongPassword");
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_NOT_FOUND, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals(message,"Учетная запись не найдена");
    }
}

