package com.epam.cars;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.LoggerFactory;

public class DBServletContextListener
        implements ServletContextListener {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DBServletContextListener.class.getName());

    private static final String CREATETABLES = "create_tables.sql";
    private static final String INITTABLES = "insert_def_values.sql";

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("enter with create table");
        tablesInit(CREATETABLES);
        System.out.println("enter with inserting");
        tablesInit(INITTABLES);
    }

    private void tablesInit(String filename) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = con.createStatement()) {

            byte[] b = null;
            System.class.getResourceAsStream(filename).read(b);

            String query = new String(b);
            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            LOG.error("error connection in ContextListener", sqlEx);
        } catch (IOException ex) {
            LOG.error("error of reading sql-query ", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
