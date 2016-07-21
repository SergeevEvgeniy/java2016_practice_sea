package com.epam.cars;

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

    private final Map<Long, Maker> makers = new HashMap<Long, Maker>();

    private static final String url = "jdbc:h2:~/test";
    private static final String user = "root";
    private static final String password = "root";
    private static final String MAKER_Q = "select * from Public.MAKER";

    private static Connection con;
    private static Statement stmt;
    private static PreparedStatement pstmt;
    private static ResultSet rs;

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
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(MAKER_Q);

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                String name = "";
                int found_year = 0;
                String adress = "";
                long id = 0;

                for (int i = 0; i < meta.getColumnCount(); i++) {
                    //System.out.println(meta.getColumnLabel(i + 1) + ": "+ rs.getString(i + 1));
                    switch (meta.getColumnLabel(i + 1)) {
                        case "ID_MAKER":
                            id = Long.parseLong(rs.getString(i + 1));
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
                            found_year = Integer.parseInt(rs.getString(i + 1));
                            break;
                    }
                }
                Maker maker = new Maker(name, adress, found_year);
                maker.setId(id);
                makers.put(id, maker);
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

        return new ArrayList(makers.values());
    }

    @Override
    public void saveMaker(Maker maker) {
        try {
            con = DriverManager.getConnection(url, user, password);
            //makers.put(id, maker);
            pstmt = con.prepareStatement("Insert into MAKER Values(?,?,?,?");
            pstmt.setString(2, maker.getName());
            pstmt.setString(3, maker.getAdress());
            pstmt.setInt(4, maker.getFoundYear());
            pstmt.setLong(1, ++lastMakerId);
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
    public Maker getMaker(Long id) {
        return makers.get(id);
    }

    @Override
    public void updateMaker(Long id, Maker maker) {
        try {
            con = DriverManager.getConnection(url, user, password);
            //makers.put(id, maker);
            pstmt = con.prepareStatement("Update MAKER set NAME=? ADRESS=? FOUND_YEAR=? WHERE ID_MAKER=?");
            pstmt.setString(1, maker.getName());
            pstmt.setString(2, maker.getAdress());
            pstmt.setInt(3, maker.getFoundYear());
            pstmt.setLong(4, id);
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
