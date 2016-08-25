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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class H2CarRepository implements CarRepository {

    private final DatabaseConfig connect = new DatabaseConfig();
    private static PreparedStatement pstmt;

    public static H2CarRepository instance;

    private final MakerRepository makerRep = H2MakerRepository.getInstance();

    private final Maker nullMaker = new Maker("name", "adress", 0);
    private final Car nullCar = new Car(nullMaker, "model", 0, " color");

    private static final Logger LOG = LoggerFactory.getLogger(H2MakerRepository.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private long lastCarId = 0;

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
        lastCarId = this.jdbcTemplate.queryForObject("select max(id_car) from Public.CAR", Long.class);
        try {
            String SQL = "Insert into CAR Values(:id, :model, :color, :year, :id_maker)";
            Map namedParameters = new HashMap();
            namedParameters.put("model", car.getModel());
            namedParameters.put("color", car.getColor());
            namedParameters.put("year", car.getYear());
            namedParameters.put("id_maker", car.getMaker().getId());
            namedParameters.put("id", ++lastCarId);
            namedParameterJdbcTemplate.update(SQL, namedParameters);

        } catch (DataAccessException ex) {
            LOG.error("error connection or query ", ex);
        }
    }

    @Override
    public void updateCar(Car car) {
        try {
            String SQL = "Update CAR set MODEL=:model, COLOR=:color, YEAR=:year,ID_MAKER=:id_maker WHERE ID_CAR=id";
            Map namedParameters = new HashMap();
            namedParameters.put("model", car.getModel());
            namedParameters.put("color", car.getColor());
            namedParameters.put("year", car.getYear());
            namedParameters.put("id_maker", car.getMaker().getId());
            namedParameters.put("id", car.getId());
            namedParameterJdbcTemplate.update(SQL, namedParameters);

            // makerRep.updateMaker(car.getMaker().getId(), car.getMaker()); 
            //updates must be different 
        } catch (DataAccessException ex) {
            LOG.error("Update Car error", ex);
        }
    }
}
