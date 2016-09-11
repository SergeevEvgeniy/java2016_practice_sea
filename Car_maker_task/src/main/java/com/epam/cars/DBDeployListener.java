package com.epam.cars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DBDeployListener
        implements ServletContextListener {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(DBDeployListener.class);

    private static final String CREATETABLES = "create_tables.sql";
    private static final String INITTABLES = "insert_def_values.sql";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.h2.Driver");
            injectSpringDependencies(sce);
            if (executeScript(CREATETABLES)) {
                if (executeScript(INITTABLES)) {
                    LOG.info("db creation scripts was successfully executed");
                } else {
                    LOG.info("tables created whithout any data");
                }
            } else {
                LOG.info("db already exist");
            }
        } catch (ClassNotFoundException ex) {
            LOG.error("Can't load db driver", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     * execute sql-script from resurce file
     *
     * @param filename resurce filename with sql-script
     * @return true if script was successfully executed, false otherwise
     */
    private boolean executeScript(String filename) {
        try {
            jdbcTemplate.execute(readFileAsText(filename));
            return true;
        } catch (DataAccessException sqlEx) {
            LOG.error("error executing script");
        } catch (IOException ex) {
            LOG.error("error of reading sql-query ", ex);
        }
        return false;
    }

    private String readFileAsText(String filename) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String readString;
        String query = "";

        while ((readString = br.readLine()) != null) {
            query += readString;
        }
        return query;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void injectSpringDependencies(ServletContextEvent sce) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
    }
}
