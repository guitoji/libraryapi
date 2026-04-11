package com.guitoji.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driverClassName}")
    String driver;

    /**
     * config do Hikari:
     * https://github.com/brettwooldridge/HikariCP
     * @return
     */
    @Bean
    public DataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setPassword(password);
        config.setUsername(username);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); // Máximo de conexões liberadas
        config.setMinimumIdle(1); // Tamanho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); // Tempo em mil ms (10 min)
        config.setConnectionTimeout(100000); // Timeout para conseguir uma conexão
        config.setConnectionTestQuery("select 1"); //query para teste de conexão

        return new HikariDataSource(config);
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource driverManager = new DriverManagerDataSource();
//        driverManager.setUrl(url);
//        driverManager.setPassword(password);
//        driverManager.setUsername(username);
//        driverManager.setDriverClassName(driver);
//        return driverManager;
//    }
}
