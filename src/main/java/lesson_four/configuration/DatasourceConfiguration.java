package lesson_four.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lesson_four.configuration.properties.DatasourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YStepanov
 */
@Configuration
public class DatasourceConfiguration {
    @Bean
    public HikariDataSource getDataSource (DatasourceProperties datasourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(datasourceProperties.getUrl());
        config.addDataSourceProperty("url", datasourceProperties.getUrl());
        config.setUsername(datasourceProperties.getUsername());
        config.setPassword(datasourceProperties.getPassword());
        config.setDriverClassName(datasourceProperties.getDriverClassName());
        return new HikariDataSource(config);
    }
}
