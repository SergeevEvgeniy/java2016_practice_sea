package com.epam.cars.h2;

import com.epam.cars.MakerRepository;
import com.epam.cars.model.Maker;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2MakerRepository implements MakerRepository {

    private final Maker nullMaker = new Maker("name", "adress", 0);

    private ConnectionProvider connect = new ConnectionProvider();

    private static PreparedStatement pstmt;

    public static H2MakerRepository instance;

    private long lastMakerId = 0;

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
        System.out.println("in getMaker(id) "+id);
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {

            pstmt = con.prepareStatement("select * from MAKER WHERE ID_MAKER=?");
            pstmt.setLong(1, id);

            System.out.println("Find id"+ id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs.getString("NAME"));
            
            return toMaker(rs);

        } catch (SQLException sqlEx) {
            System.out.println("getMaker crash "+sqlEx); // sqlEx.printStackTrace();
        }
        return nullMaker;
    }

    @Override
    public void updateMaker(Long id, Maker maker) {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword())) {
            pstmt = con.prepareStatement("Update MAKER set NAME=? ADRESS=? "
                    + "FOUND_YEAR=? WHERE ID_MAKER=?");
            pstmt.setString(1, maker.getName());
            pstmt.setString(2, maker.getAdress());
            pstmt.setInt(3, maker.getFoundYear());
            pstmt.setLong(4, id);
            pstmt.executeQuery();

        } catch (SQLException ex) {
        }
    }
}
