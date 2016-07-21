package com.epam.cars;

import com.epam.cars.model.Car;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class H2CarRepository implements CarRepository {

    private final Map<Long, Car> cars = new HashMap<Long, Car>();

    private static final String url = "jdbc:h2:~/test";
    private static final String user = "root";
    private static final String password = "root";
    private static final String CAR_Q = "select * from Public.CAR";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement pstmt;

    public static H2CarRepository instance;

    private long lastCarId = 0;
    MakerRepository makerRep = H2MakerRepository.getInstance();

    public static synchronized H2CarRepository getInstance() {
        if (instance == null) {
            instance = new H2CarRepository();
        }
        return instance;
    }

    private H2CarRepository() {

    }

    @Override
    public Car getCar(Long id) {
        return cars.get(id);
    }

    @Override
    public List<Car> getCars() {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(CAR_Q);

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                String model = "";
                int year = 0;
                String color = "";
                long id_car = 0;
                long id_maker = 0;

                for (int i = 0; i < meta.getColumnCount(); i++) {
                    //System.out.println(meta.getColumnLabel(i + 1) + ": "+ rs.getString(i + 1));
                    switch (meta.getColumnLabel(i + 1)) {
                        case "ID_MAKER":
                            id_maker = Long.parseLong(rs.getString(i + 1));
                            break;
                        case "MODEL":
                            model = rs.getString(i + 1);
                            break;
                        case "COLOR":
                            color = rs.getString(i + 1);
                            break;
                        case "YEAR":
                            year = Integer.parseInt(rs.getString(i + 1));
                            break;
                        case "ID_CAR":
                            id_car = Long.parseLong(rs.getString(i + 1));
                            if (id_car > lastCarId) {
                                lastCarId = id_car;
                                break;
                            }
                    }
                }

                Car car = new Car(makerRep.getMaker(id_maker), model, year, color);
                car.setId(id_car);
                cars.put(id_car, car);
            }
            rs.close();

        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                stmt.close();
            } catch (SQLException se) {
            }
            try {
                rs.close();
            } catch (SQLException se) {
            }
        }

        return new ArrayList(cars.values());
    }

    @Override
    public void saveCar(Car car) {
        try {
            con = DriverManager.getConnection(url, user, password);
            pstmt = con.prepareStatement("Insert into CAR Values(?,?,?,?,?");
            pstmt.setLong(1, ++lastCarId);
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getColor());
            pstmt.setInt(4, car.getYear());
            pstmt.setLong(5, car.getMaker().getId());
            pstmt.executeQuery();

        } catch (SQLException ex) {
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
            }
        }
    }

    @Override
    public void updateCar(Long id, Car car
    ) {
        try {
            con = DriverManager.getConnection(url, user, password);
            pstmt = con.prepareStatement("Update CAR set MODEL=? COLOR=? YEAR=? ID_MAKER=? WHERE ID_CAR=?");
            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getColor());
            pstmt.setInt(3, car.getYear());
            pstmt.setLong(4, car.getMaker().getId());
            pstmt.setLong(5, id);
            pstmt.executeQuery();

        } catch (SQLException ex) {
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
            }
        }
    }
}
