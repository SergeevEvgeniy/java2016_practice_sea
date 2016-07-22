package com.epam.cars.h2;

public class ConnectionProvider {

    private static final String url = "jdbc:h2:~/test";
    private static final String user = "root";
    private static final String password = "root";

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
