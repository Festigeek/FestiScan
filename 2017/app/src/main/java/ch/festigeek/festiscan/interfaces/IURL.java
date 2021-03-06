package ch.festigeek.festiscan.interfaces;

public interface IURL {
    String BASE_URL = "https://api.festigeek.ch/v1/";

    String LOGIN = "users/login";

    String QR_DECRYPT = "qrcode/decrypt";
    String ALL_USERS = "orders";
    String USER_BY_ID = "users/";

    String USER_ORDERS = "orders?userId=";

    String CHECK_IN = "abstract_products/"; // inscription/{id} PATCH

    String ORDERS = "orders/";
    String PRODUCTS = "/products/";
    String CONSUME = "/productConsumption";

    String PRODUCT = "";
}
