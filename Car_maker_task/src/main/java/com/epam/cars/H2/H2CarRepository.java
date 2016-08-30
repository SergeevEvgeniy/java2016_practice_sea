package com.epam.cars.h2;

import com.epam.cars.CarRepository;
import com.epam.cars.MakerRepository;
import com.epam.cars.model.Car;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class H2CarRepository implements CarRepository {

    public static H2CarRepository instance;

    @Autowired
    private MakerRepository makerRep;

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
        return (Car) jdbcTemplate.query("select * from CAR WHERE ID_CAR=?",
                new Object[]{id}, new CarMapper());
    }

    @Override
    public List<Car> getCarsByModel(String search) {
        return jdbcTemplate.query("select * from CAR WHERE MODEL=?",
                new Object[]{search}, new CarMapper());
    }

    @Override
    public List<Car> getCars() {
        return jdbcTemplate.query("select * from Public.CAR", new CarMapper());
    }

    private class CarMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(final ResultSet rs, int rowNum) throws SQLException {
            Car car = new Car(makerRep.getMaker(rs.getLong("ID_MAKER")),
                    rs.getString("MODEL"), rs.getInt("YEAR"), rs.getString("COLOR"));
            car.setId(rs.getLong("ID_CAR"));
            return car;
        }
    }

    @Override
    public void saveCar(Car car) {
        lastCarId = this.jdbcTemplate.queryForObject("select max(id_car) from Public.CAR",
                Long.class);

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
            String SQL = "Update CAR set MODEL=:model, COLOR=:color, YEAR=:year, ID_MAKER=:id_maker WHERE ID_CAR=id";
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
