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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2CarRepository implements CarRepository {

    private final DatabaseConfig connect = new DatabaseConfig();
    private static PreparedStatement pstmt;

    public static H2CarRepository instance;

    private final MakerRepository makerRep = H2MakerRepository.getInstance();

    private final Maker nullMaker = new Maker("name", "adress", 0);
    private final Car nullCar = new Car(nullMaker, "model", 0, " color");

    private static final Logger LOG = LoggerFactory.getLogger(H2MakerRepository.class.getName());

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
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            return toCar(rs);

        } catch (SQLException sqlEx) {
            LOG.error("error query getCar ", sqlEx);
        }
        return nullCar;
    }

    @Override
    public List<Car> getCars(String search) {
        List<Car> cars = new LinkedList<>();
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword());
                Statement stmt = con.createStatement()) {

            ResultSet rs;

            if ("".equals(search)) {
                rs = stmt.executeQuery("select * from Public.CAR");
            } else {
                pstmt = con.prepareStatement("select * from CAR WHERE MODEL=?");
                pstmt.setString(1, search);
                rs = pstmt.executeQuery();
            }

            while (rs.next()) {
                Car car = toCar(rs);
                cars.add(car);
            }
        } catch (SQLException sqlEx) {
            LOG.error("error query getCars ", sqlEx);
        }
        return cars;
    }

    private Car toCar(final ResultSet rs) throws SQLException {
        Car car = new Car(makerRep.getMaker(rs.getLong("ID_MAKER")),
                rs.getString("MODEL"), rs.getInt("YEAR"), rs.getString("COLOR"));
        car.setId(rs.getLong("ID_CAR"));
        return car;
    }

    @Override
    public void saveCar(Car car) {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("Insert into CAR Values(?,?,?,?)");
            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getColor());
            pstmt.setInt(3, car.getYear());
            pstmt.setLong(4, car.getMaker().getId());
            pstmt.execute();
        } catch (SQLException ex) {
            LOG.error("error connection or query ", ex);
        }
    }

    @Override
    public void updateCar(Car car) {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("Update CAR set MODEL=?,COLOR=?,YEAR=?,ID_MAKER=?"
                    + "WHERE ID_CAR=?");
            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getColor());
            pstmt.setInt(3, car.getYear());
            pstmt.setLong(5, car.getId());
            pstmt.setLong(4, car.getMaker().getId());
            pstmt.executeUpdate();

            // makerRep.updateMaker(car.getMaker().getId(), car.getMaker()); 
            //updates must be different 
        } catch (SQLException ex) {
            LOG.error("Update Car error", ex);
        }
    }
}
