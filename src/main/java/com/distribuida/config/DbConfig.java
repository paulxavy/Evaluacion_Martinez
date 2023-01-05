package com.distribuida.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;


@ApplicationScoped
public class DbConfig {
    @Inject
    @ConfigProperty(name = "db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name = "db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name = "db.url")
    String dbUrl;

    @Produces
    @ApplicationScoped
    public DataSource createDatasource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

}
