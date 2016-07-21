/*<listener>
        <listener-class> com.epam.cars.DBServletContextListener</listener-class>
    </listener>*/


package com.epam.cars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBServletContextListener
        implements ServletContextListener {

    private static final String url = "jdbc:h2:~/test";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");

        String query = "CREATE TABLE Car\n"
                + "(\n"
                + "	id_car                CHAR(18)  NOT NULL ,\n"
                + "	Model                 VARCHAR(20) ,\n"
                + "	Color                 VARCHAR(20) ,\n"
                + "	Year                  VARCHAR(20) ,\n"
                + "	id_maker              CHAR(18)  NOT NULL \n"
                + ");\n"
                + "\n"
                + "\n"
                + "\n"
                + "ALTER TABLE Car\n"
                + "	ADD CONSTRAINT XPKCar  PRIMARY KEY (id_car);\n"
                + "\n"
                + "\n"
                + "\n"
                + "CREATE TABLE Maker\n"
                + "(\n"
                + "	id_maker              CHAR(18)  NOT NULL ,\n"
                + "	Name                  VARCHAR(20) ,\n"
                + "	Adress                VARCHAR(20) ,\n"
                + "	Found_Year            VARCHAR(20) \n"
                + ");\n"
                + "\n"
                + "\n"
                + "\n"
                + "ALTER TABLE Maker\n"
                + "	ADD CONSTRAINT XPKMaker  PRIMARY KEY (id_maker);\n"
                + "\n"
                + "\n"
                + "\n"
                + "ALTER TABLE Car\n"
                + "	ADD CONSTRAINT R_1  FOREIGN KEY (id_maker) REFERENCES Maker (id_maker)\n"
                + "		ON DELETE NO ACTION\n"
                + "		ON UPDATE NO ACTION;";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
           // sqlEx.printStackTrace();
            System.out.println("Table alredy exist");

        } finally {
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) {
            }
//            try {
//                rs.close();
//            } catch (SQLException se) {
//            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }
}
