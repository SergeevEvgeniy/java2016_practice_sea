package com.epam.cars;

import com.epam.cars.model.Car;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class CarDB implements CarRepository {

    private static final String url = "jdbc:h2:~/test";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @Override
    public Car getCar(String id) {
        String query = "select * from Public.CAR";
        Car car = null;//new Car();

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                for (int i = 0; i < meta.getColumnCount(); i++) {
                    System.out.println(meta.getColumnLabel(i + 1) + ": "
                            + rs.getString(i + 1));
                }
                System.out.println();
                return car;
            }
            rs.close();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return car;
    }

    @Override
    public Map<String, Car> getCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCar(String id, Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
