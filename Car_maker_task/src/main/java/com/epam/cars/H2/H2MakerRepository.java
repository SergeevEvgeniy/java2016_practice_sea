package com.epam.cars.h2;

import com.epam.cars.MakerRepository;
import com.epam.cars.model.Maker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2MakerRepository implements MakerRepository {
    
    private final Maker nullMaker = new Maker("name", "adress", 0);
    private long lastMakerId = 0;
    
    private final ConnectionProvider connect = new ConnectionProvider();
    private static PreparedStatement pstmt;
    
    public static H2MakerRepository instance;
    
    private static final Logger log = LoggerFactory.getLogger(H2MakerRepository.class.getName());
    
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
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("Insert into MAKER Values(?,?,?,?");
            pstmt.setString(2, maker.getName());
            pstmt.setString(3, maker.getAdress());
            pstmt.setInt(4, maker.getFoundYear());
            pstmt.setLong(1, ++lastMakerId);
            pstmt.executeQuery();
            
        } catch (SQLException ex) {
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
            log.error("error connection or query ", sqlEx); // sqlEx.printStackTrace();
        }
        return nullMaker;
    }
    
    @Override
    public void updateMaker(Long id, Maker maker) {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("Update MAKER set NAME=?,ADRESS=?,"
                    + "FOUND_YEAR=? WHERE ID_MAKER=?");
            pstmt.setString(1, maker.getName());
            pstmt.setString(2, maker.getAdress());
            pstmt.setInt(3, maker.getFoundYear());
            pstmt.setLong(4, id);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            log.error("Update Maker error", ex);
        }
    }
}
