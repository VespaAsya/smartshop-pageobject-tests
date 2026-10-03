package config;

public final class TestConfig {

    public static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");
    public static final String ADMIN_LOGIN = System.getProperty("adminLogin", "admin");
    public static final String ADMIN_PASSWORD = System.getProperty("adminPassword", "secret123");

    private TestConfig() {
    }
}
