package net.paf.courier;

import org.apache.commons.lang3.RandomStringUtils;
import net.paf.courier.Courier;

public class CourierGenerator {
        public static Courier getRandomCourier() {
        return new Courier(RandomStringUtils.randomAlphanumeric(10), "practicum.yandex.ru", "Pavel");
    }
}

