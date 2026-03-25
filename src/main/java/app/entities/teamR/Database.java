package app.entities.teamR;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class Database {
    private static HikariDataSource ds;

    public static DataSource getDataSource() {
        if (ds == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/lifehack");
            config.setUsername("postgres"); // skift til dit DB-brugernavn
            config.setPassword("password"); // skift til din adgangskode
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            ds = new HikariDataSource(config);
        }
        return ds;
    }
}