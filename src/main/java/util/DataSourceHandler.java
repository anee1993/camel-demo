package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceHandler {
    private final String url;
    private final String username;
    private final String password;
    private final String driver;


    public DataSourceHandler(String url, String username, String password, String driver) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
    }

    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(5);
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName("springHikariCP");

        return new HikariDataSource(config);
    }
}
