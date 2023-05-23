package net.paf.order;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class OrderGeneratorTest {

    @Parameterized.Parameters(name = "{index}:{0},{1}")
    public static Object[][] getRandomOrder() {
        Collection<Object[]> orders = Arrays.asList(new Object[][]{
                {RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), 111, "84955277525", 1, "2023-06-28", "don't call, baby is sleeping", new Object[]{"GREY"}},
                {RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), 111, "84955277525", 1, "2023-06-28", "don't call, baby is sleeping", new Object[]{"BLACK"}},
                {RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), 111, "84955277525", 1, "2023-06-28", "don't call, baby is sleeping", new Object[]{"BLACK", "GREY"}},
                {RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), RandomStringUtils.randomAlphanumeric(11), 111, "84955277525", 1, "2023-06-28", "don't call, baby is sleeping", new Object[]{}},
        });

        List<Object[]> orderList = new ArrayList<>(orders);
        Object[][] orderArray = new Object[orderList.size()][];
        for (int i = 0; i < orderList.size(); i++) {
            orderArray[i] = orderList.get(i);
        }

        return orderArray;
    }
}

