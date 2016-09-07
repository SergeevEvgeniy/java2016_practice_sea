package com.epam.cars.h2;

import com.epam.cars.MakerRepository;
import com.epam.cars.model.Maker;
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
public class H2MakerRepository implements MakerRepository {

    private long lastMakerId = 0;

    //private final Maker nullMaker = new Maker("name", "adress", 0);

    private static final Logger log = LoggerFactory.getLogger(H2MakerRepository.class.getName());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private H2MakerRepository() {
    }

    @Override
    public void saveMaker(Maker maker) {
        lastMakerId = this.jdbcTemplate.queryForObject("select max(id_maker) from Public.MAKER", Long.class);
        try {
            String SQL = "Insert into MAKER Values(:id, :name, :adress, :year)";
            Map namedParameters = new HashMap();
            namedParameters.put("id", ++lastMakerId);
            namedParameters.put("name", maker.getName());
            namedParameters.put("adress", maker.getAdress());
            namedParameters.put("year", maker.getFoundYear());
            namedParameterJdbcTemplate.update(SQL, namedParameters);
        } catch (DataAccessException ex) {
            log.error("error query saveMaker ", ex);
        }
    }

    @Override
    public void updateMaker(Long id, Maker maker
    ) {
        try {
            String SQL = "Update MAKER set NAME=:name, ADRESS=:adress, FOUND_YEAR=:year WHERE ID_MAKER=:id";
            Map namedParameters = new HashMap();
            namedParameters.put("id", id);
            namedParameters.put("name", maker.getName());
            namedParameters.put("adress", maker.getAdress());
            namedParameters.put("year", maker.getFoundYear());
            namedParameterJdbcTemplate.update(SQL, namedParameters);

        } catch (DataAccessException ex) {
            log.error("Update Maker error ", ex);
        }
    }

    @Override
    public Maker getMaker(Long id) {
        List<Maker> readingList = jdbcTemplate.query("select * from MAKER WHERE ID_MAKER=?",
                new Object[]{id}, new MakerMapper());
        
        //if (readingList.size() == 1) { 
            return readingList.get(0);
        
    }

    @Override
    public List<Maker> getMakers() {
        return jdbcTemplate.query("select * from Public.MAKER", new MakerMapper());
    }

    private class MakerMapper implements RowMapper<Maker> {

        @Override
        public Maker mapRow(ResultSet rs, int rowNum) throws SQLException {
            Maker maker = new Maker(rs.getString("NAME"), rs.getString("ADRESS"),
                    rs.getInt("FOUND_YEAR"));
            maker.setId(rs.getLong("ID_MAKER"));
            return maker;
        }
    }
}
