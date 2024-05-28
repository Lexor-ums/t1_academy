package productmodule.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productmodule.configuration.properties.DatasourceProperties;

/**
 * @author YStepanov
 */
@Configuration
public class DatasourceConfiguration {
    @Bean
    public HikariDataSource getDataSource (DatasourceProperties datasourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(datasourceProperties.url());
        config.addDataSourceProperty("url", datasourceProperties.url());
        config.setUsername(datasourceProperties.username());
        config.setPassword(datasourceProperties.password());
        config.setDriverClassName(datasourceProperties.driverClassName());
        return new HikariDataSource(config);
    }
}
