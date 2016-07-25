package com.epam.cars.h2;

import com.epam.cars.CarRepository;
import com.epam.cars.MakerRepository;
import com.epam.cars.model.Car;
import com.epam.cars.model.Maker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class H2CarRepository implements CarRepository {

    private final List<Car> cars = new LinkedList<>();

    private final ConnectionProvider connect = new ConnectionProvider();

    private static PreparedStatement pstmt;

    public static H2CarRepository instance;

    private long lastCarId = 0;
    private final MakerRepository makerRep = H2MakerRepository.getInstance();

    private final Maker nullMaker = new Maker("name", "adress", 0);
    private final Car nullCar = new Car(nullMaker, "model", 0, " color");

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
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("select * from CAR WHERE ID_CAR=?");
            pstmt.setLong(1, id);

            return toCar(pstmt.executeQuery());

        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
        }
        return nullCar;
    }

    @Override
    public List<Car> getCars() {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword());
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from Public.CAR")) {

            while (rs.next()) {
                Car car = toCar(rs);
                cars.add(car);
            }
        } catch (SQLException sqlEx) {
            System.out.println("Wrong connection in getCars " + sqlEx);// sqlEx.printStackTrace();
        }
        return new LinkedList(cars);
    }

    private Car toCar(final ResultSet rs) throws SQLException {
        Car car = new Car(makerRep.getMaker(rs.getLong("ID_MAKER")),
                rs.getString("MODEL"), rs.getInt("YEAR"), rs.getString("COLOR"));
        car.setId(rs.getLong("ID_CAR"));
        return car;
    }

    @Override
    public void saveCar(Car car
    ) {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("Insert into CAR Values(?,?,?,?,?");
            pstmt.setLong(1, ++lastCarId);
            pstmt.setString(2, car.getModel());
            pstmt.setString(3, car.getColor());
            pstmt.setInt(4, car.getYear());
            pstmt.setLong(5, car.getMaker().getId());
            pstmt.executeQuery();

        } catch (SQLException ex) {
        }
    }

    @Override
    public void updateCar(Long id, Car car
    ) {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("Update CAR set MODEL=? COLOR=? YEAR=? "
                    + "ID_MAKER=? WHERE ID_CAR=?");
            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getColor());
            pstmt.setInt(3, car.getYear());
            pstmt.setLong(4, car.getMaker().getId());
            pstmt.setLong(5, id);
            pstmt.executeQuery();

        } catch (SQLException ex) {
        }
    }
}
