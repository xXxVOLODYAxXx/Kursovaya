package ru.sfedu.Kursovaya.utils.OtherUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCH2Utils {
    private static final Logger log = LogManager.getLogger(JDBCH2Utils.class);

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ConfigurationUtil.getConfigurationEntry(Constants.JDBC_URL),
                    ConfigurationUtil.getConfigurationEntry(Constants.JDBC_USERNAME),
                    ConfigurationUtil.getConfigurationEntry(Constants.JDBC_PASSWORD));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
