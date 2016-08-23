package com.epam.cars.h2;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class DatabaseConfig {

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

    public DataSource getDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername(user);
        dataSource.setUrl(url);
        dataSource.setPassword(password);

        return dataSource;
    }
}
