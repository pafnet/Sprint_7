package net.paf.order;

import org.apache.commons.lang3.RandomStringUtils;

public class OrderGenerator {

    public static Object[][] getRandomOrder() {
        String orderNumber = RandomStringUtils.randomAlphanumeric(10);
        String customerName = RandomStringUtils.randomAlphabetic(10);
        String address = RandomStringUtils.randomAlphanumeric(20);
        String phoneNumber = RandomStringUtils.randomNumeric(10);
        String email = RandomStringUtils.randomAlphabetic(10) + "@paf.net";
        String[] products = getRandomProducts();
        String[] colors = getRandomColors();
        int quantity = getRandomQuantity();

        return new Object[][]{{orderNumber, customerName, address, phoneNumber, email, products, colors, quantity}};
    }

    private static String[] getRandomProducts() {
        return new String[]{RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5)};
    }

    private static String[] getRandomColors() {
        return new String[]{RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5)};
    }

    private static int getRandomQuantity() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(1));
    }
}

