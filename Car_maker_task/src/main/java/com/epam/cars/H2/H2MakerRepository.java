package com.epam.cars.h2;

import com.epam.cars.MakerRepository;
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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class H2MakerRepository implements MakerRepository {

    private final Maker nullMaker = new Maker("name", "adress", 0);
    private long lastMakerId = 0;

    private final DatabaseConfig connect = new DatabaseConfig();
    private static PreparedStatement pstmt;

    public static H2MakerRepository instance;

    private static final Logger log = LoggerFactory.getLogger(H2MakerRepository.class.getName());

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(connect.getDataSource());

    public static synchronized H2MakerRepository getInstance() {
        if (instance == null) {
            instance = new H2MakerRepository();
        }
        return instance;
    }

    private H2MakerRepository() {
    }

    private Maker toMaker(final ResultSet rs) throws SQLException {
        Maker maker = new Maker(rs.getString("NAME"), rs.getString("ADRESS"),
                rs.getInt("FOUND_YEAR"));
        maker.setId(rs.getLong("ID_MAKER"));
        return maker;
    }

    @Override
    public void saveMaker(Maker maker) {
        lastMakerId = this.jdbcTemplate.queryForObject("select max(id_maker) from Public.MAKER", Long.class);
        try {
            jdbcTemplate.update("Insert into MAKER Values(?,?,?,?)",
                    ++lastMakerId, maker.getName(), maker.getAdress(), maker.getFoundYear());
        } catch (DataAccessException ex) {
            log.error("error query saveMaker ", ex);
        }
    }

    @Override
    public Maker getMaker(Long id) {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {

            pstmt = con.prepareStatement("select * from MAKER WHERE ID_MAKER=?");
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            return toMaker(rs);
        } catch (SQLException sqlEx) {
            log.error("error query getMaker ", sqlEx); // sqlEx.printStackTrace();
        }
        return nullMaker;
    }

    @Override
    public void updateMaker(Long id, Maker maker
    ) {
        try {
            jdbcTemplate.update("Update MAKER set NAME=?,ADRESS=?,FOUND_YEAR=? WHERE ID_MAKER=?",
                    maker.getName(), maker.getAdress(), maker.getFoundYear(), id);

        } catch (DataAccessException ex) {
            log.error("Update Maker error ", ex);
        }
    }

    @Override
    public List<Maker> getMakers() {
        List<Maker> makers = new LinkedList<>();
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword());
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from Public.MAKER")) {

            while (rs.next()) {
                Maker maker = toMaker(rs);
                makers.add(maker);
            }
        } catch (SQLException sqlEx) {
            log.error("error query getMakers ", sqlEx);
        }
        return makers;
    }
}