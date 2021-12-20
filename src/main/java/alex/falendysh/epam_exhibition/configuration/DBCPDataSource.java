package alex.falendysh.epam_exhibition.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPDataSource {
    private static Logger logger = LoggerFactory.getLogger(DBCPDataSource.class);
    private static String dbProperties = "/dao.properties";

    private static BasicDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try (InputStream inputStream = DBCPDataSource.class
                    .getResourceAsStream(dbProperties)) {
                Properties properties = new Properties();
                properties.load(inputStream);

                dataSource = new BasicDataSource();
                dataSource.setUrl(properties.getProperty("jdbc.url"));
                dataSource.setUsername(properties.getProperty("jdbc.user"));
                dataSource.setPassword(properties.getProperty("jdbc.password"));
                dataSource.setDriverClassName(properties.getProperty("jdbc.driver.class.name"));
                dataSource.setMinIdle(5);
                dataSource.setMaxIdle(10);
                dataSource.setMaxWaitMillis(5000);
                dataSource.setMaxOpenPreparedStatements(100);
            } catch (IOException e) {
                logger.error("Exception when trying to get property file ",e);
            }
        }
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DBCPDataSource() {
    }
}
