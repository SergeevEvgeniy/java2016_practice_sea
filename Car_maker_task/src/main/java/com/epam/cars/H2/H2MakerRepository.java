package com.epam.cars.h2;

import com.epam.cars.MakerRepository;
import com.epam.cars.model.Maker;
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

public class H2MakerRepository implements MakerRepository {

    private final Map<Long, Maker> makers = new HashMap<>();

    private static final String MAKER_Q = "select * from Public.MAKER";

    private ConnectionProvider connect;

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

    @Override
    public List<Maker> getMakers() {
        try (Connection con = DriverManager.getConnection(connect.getUrl(),
                connect.getUser(), connect.getPassword());
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(MAKER_Q)) {

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                String name = "";
                int found_year = 0;
                String adress = "";
                long id = 0;

                for (int i = 0; i < meta.getColumnCount(); i++) {
                    switch (meta.getColumnLabel(i + 1)) {
                        case "ID_MAKER":
                            id = rs.getLong(i + 1);
                            if (id > lastMakerId) {
                                lastMakerId = id;
                            }
                            break;
                        case "NAME":
                            name = rs.getString(i + 1);
                            break;
                        case "ADRESS":
                            adress = rs.getString(i + 1);
                            break;
                        case "FOUND_YEAR":
                            found_year = rs.getInt(i + 1);
                            break;
                    }
                }
                Maker maker = new Maker(name, adress, found_year);
                maker.setId(id);
                makers.put(id, maker);
            }

        } catch (SQLException sqlEx) {
            // sqlEx.printStackTrace();
        }

        return new ArrayList(makers.values());
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
        return makers.get(id);
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
